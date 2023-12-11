## What is Ryan store❓ 
- **Ryan - Store**  is a Back-end application built personally with  Spring Boot and HTML,CSS that allows users to add books users want to buy and purchase.

## Features
- I used for ManyToMany in this project. But this is not good at Real Project. That is for practice.
- User can add the book.
- User can make the order with the book the user added.

## Core Codes that I used in this project
  1. Creat Order
     ```java
       public static OrderItem createOrderItem(Item item, int orderPrice, int
          count) {
          OrderItem orderItem = new OrderItem();
          orderItem.setItem(item);
          orderItem.setOrderPrice(orderPrice);
          orderItem.setCount(count);
          item.removeStock(count);
          return orderItem;
     }  
     ```

  

  2. Cancel Order
     ```java
        public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("You cannot cancel the item after it starts delivery.");
        }

        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }
     ```

    

  3. Checking Order Status

     ```java
     if (orderSearch.getOrderStatus() != null) {
    if (isFirstCondition) {
    jpql += " where";
    isFirstCondition = false;
    } else {
    jpql += " and";
    }
    jpql += " o.status = :status";
    }
     ```

 
## Image 
|Feature|Description|
|--|--|
|Feature0|<img width="840" alt="Screenshot 2023-12-11 at 1 47 53 PM" src="https://github.com/devRyanChoi/Ryan-Store/assets/120599634/70a9e2dc-dfc5-46a4-be7f-f4d11ad21844" width="400"<br>Drwaing|
|Feature1|<img width="750" alt="Screenshot 2023-12-11 at 12 46 37 PM" src="https://github.com/devRyanChoi/Ryan-Store/assets/120599634/3219d8c0-b30b-4f3c-887b-49245416f49a" width="400"><br>Main page|
|Feature2|<img width="733" alt="Screenshot 2023-12-11 at 12 47 54 PM" src="https://github.com/devRyanChoi/Ryan-Store/assets/120599634/e84763f0-6181-4b77-95dd-ae77ff34b292" width="400"><br>Sign Up page|
|Feature3|<img width="720" alt="Screenshot 2023-12-11 at 12 50 28 PM" src="https://github.com/devRyanChoi/Ryan-Store/assets/120599634/1d4365fa-91bb-41d7-a7db-687afc135763" width="400"><br>Register Item page|
|Featrue4|<img width="737" alt="Screenshot 2023-12-11 at 12 50 01 PM" src="https://github.com/devRyanChoi/Ryan-Store/assets/120599634/144f7ce1-3a36-43e7-9e3e-841d3e78a4d0" width="400"><br>item List page|
|Feature5|<img width="723" alt="Screenshot 2023-12-11 at 12 50 57 PM" src="https://github.com/devRyanChoi/Ryan-Store/assets/120599634/8c620556-debf-4ae2-a68c-1596910e5e2f" width="400"><br>Order page|
|Feature6|<img width="918" alt="Screenshot 2023-12-11 at 12 51 07 PM" src="https://github.com/devRyanChoi/Ryan-Store/assets/120599634/482181d2-1b27-4668-b251-7f23092fedb1" width="400"><br>Order List page|

## Main Library 
- **Spring MVC**
- **Spring ORM**
- **JPA, Hibernate**
- **Spring Data JPA**

## Test Library 
- **junit**: 
- **mockito** 
- **assertj**
- **spring-test**
