package com.mark.designpattern.i_decorator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

public class Decorator {}

/**
 * 원본 객체와 장식된 객체 모두를 묶는 인터페이스
 */
interface IComponent {
    void operation();
}

/**
 * 장식될 원본 객체
 */
@Slf4j
class ConcreteComponent implements IComponent {
    @Override
    public void operation() {
        log.info("Origin Object operation(): {}", this.getClass().getName());
    }
}

/**
 * 장식자 추상 클래스
 */
@AllArgsConstructor
abstract class AbstractDecorator implements IComponent {
    IComponent wrappee; // 원본 객체를 Composition

    public void operation() {
        wrappee.operation();
    }
}

/**
 * 장식자 클래스
 */
@Slf4j
class ComponentDecoratorA extends AbstractDecorator {
    ComponentDecoratorA(IComponent component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();  // 원본 객체를 상위 클래스의 위임을 통해 실행하고
        extraOperation();   // 장식 클래스만의 메서드를 실행한다.
    }

    void extraOperation() {
        log.info("ComponentDecoratorA extraOperation(): {}", this.getClass().getName());
    }
}

@Slf4j
class ComponentDecoratorB extends AbstractDecorator {
    ComponentDecoratorB(IComponent component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();  // 원본 객체를 상위 클래스의 위임을 통해 실행하고
        extraOperation();   // 장식 클래스만의 메서드를 실행한다.
    }

    void extraOperation() {
        log.info("ComponentDecoratorA extraOperation(): {}", this.getClass().getName());
    }
}