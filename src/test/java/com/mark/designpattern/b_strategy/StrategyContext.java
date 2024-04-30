package com.mark.designpattern.b_strategy;

import static com.mark.designpattern.b_strategy.Strategy.*;

// Context - 전략 등록/실행
public class StrategyContext {

    // 전략 인터페이스 합성
    IStrategy strategy;

    // 전략 교체 메서드
    void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    // 전략 실행 메서드
    void doSomething() {
        this.strategy.doSomething();
    }
}
