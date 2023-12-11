package jpabook.ryanshop.service;

import jpabook.ryanshop.domain.Address;
import jpabook.ryanshop.domain.Member;
import jpabook.ryanshop.domain.Order;
import jpabook.ryanshop.domain.OrderStatus;
import jpabook.ryanshop.domain.item.Book;
import jpabook.ryanshop.domain.item.Item;
import jpabook.ryanshop.exception.NotEnoughStockException;
import jpabook.ryanshop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void ItemOrder() throws Exception {
        //given
        Member member = createMember();

        Book book = createBook("Comic Book", 10000, 10);

        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("When it got order, order status has to be ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("The quantity has to be correct.", 1, getOrder.getOrderItems().size());
        assertEquals("Total Order Price is Price * Quantity.", 10000 * orderCount, getOrder.getTotalPrice());
        assertEquals("The stock has to be reduced.", 8, book.getStockQuantity());
    }

    @Test(expected = NotEnoughStockException.class)
    public void ExcessInventoryQuantity() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("Book1", 10000, 10);

        int orderCount = 11;

        //when
        orderService.order(member.getId(), item.getId(), orderCount);

        //then
        fail("It has to happen to excess of Inventory Quantity");
    }

    @Test
    public void CancelOrder() throws Exception {
        //given
        Member member = createMember();
        Book item = createBook("Book1", 10000, 10);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("주문 취소시 상태는 CANCEL 이다.", OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다.", 10, item.getStockQuantity());
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("Member1");
        member.setAddress(new Address("Toronto", "1 Byng 3", "123-123"));
        em.persist(member);
        return member;
    }


}