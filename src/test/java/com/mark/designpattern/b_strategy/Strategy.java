package com.mark.designpattern.b_strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Strategy {

    // 전략 - 추상화된 알고리즘
    interface IStrategy {
        void doSomething();
    }

    // 전략 알고리즘 A
    static class ConcreteStrategyA implements IStrategy {
        public void doSomething() {
            log.info("ConcreteStrategyA doSomething");
        }
    }

    // 전략 알고리즘 B
    static class ConcreteStrategyB implements IStrategy {
        public void doSomething() {
            log.info("ConcreteStrategyB doSomething");
        }
    }
}
