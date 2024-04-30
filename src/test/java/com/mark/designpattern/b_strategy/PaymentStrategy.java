package com.mark.designpattern.b_strategy;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PaymentStrategy {

    // 전략 - 추상화된 알고리즘
    interface PaymentIStrategy {
        void pay(int amount);
    }

    @AllArgsConstructor
    static class KakaoCardStrategy implements PaymentIStrategy {
        private String name;
        private String cardNumber;
        private String cvv;
        private String dateOfExpiry;

        @Override
        public void pay(int amount) {
            log.info("{}원 paid using Kakao Card", amount);
        }
    }

    @AllArgsConstructor
    static class NaverCardStrategy implements PaymentIStrategy {
        private String emailId;
        private String password;

        @Override
        public void pay(int amount) {
            log.info("{}원 paid using Naver Card", amount);
        }
    }

    // 컨텍스트 - 전략을 등록하고 실행
    static class ShoppingCart {
        List<Item> items;

        public ShoppingCart() {
            this.items = new ArrayList<>();
        }

        public void addItem(Item item) {
            this.items.add(item);
        }

        public void pay(PaymentIStrategy paymentMethod) {
            int amount = 0;

            for (Item item : items) {
                amount += item.price;
            }

            paymentMethod.pay(amount);
        }
    }

    @AllArgsConstructor
    static class Item {
        public String name;
        public int price;
    }
}
