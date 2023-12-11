package jpabook.ryanshop.repository;

import jpabook.ryanshop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {

    private String memberName; //member name
    private OrderStatus orderStatus; //order status [ORDER, CANCEL]
}
