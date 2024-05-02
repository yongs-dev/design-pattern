package com.mark.designpattern.e_factory_method;

import lombok.extern.slf4j.Slf4j;

/**
 * 제품 객체 추상화 (인터페이스)
 */
interface IProduct {
    void setting();
}

/**
 * 제품 구현체
 */
@Slf4j
class ConcreteProductA implements IProduct {
    @Override
    public void setting() {
        log.info("ConcreteProductA setting completed");
    }
}

/**
 * 제품 구현체
 */
@Slf4j
class ConcreteProductB implements IProduct {
    @Override
    public void setting() {
        log.info("ConcreteProductB setting completed");
    }
}
