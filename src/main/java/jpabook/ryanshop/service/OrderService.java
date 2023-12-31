package jpabook.ryanshop.service;

import jpabook.ryanshop.domain.*;
import jpabook.ryanshop.domain.item.Item;
import jpabook.ryanshop.repository.ItemRepository;
import jpabook.ryanshop.repository.MemberRepository;
import jpabook.ryanshop.repository.OrderRepository;
import jpabook.ryanshop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * Cancel order
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        //checking order entity
        Order order = orderRepository.findOne(orderId);
        //cancel order
        order.cancel();
    }

    //searching
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByString(orderSearch);
    }
}
