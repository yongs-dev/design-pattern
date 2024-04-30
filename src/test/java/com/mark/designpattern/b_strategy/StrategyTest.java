package com.mark.designpattern.b_strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.mark.designpattern.b_strategy.PaymentStrategy.*;
import static com.mark.designpattern.b_strategy.Strategy.ConcreteStrategyA;
import static com.mark.designpattern.b_strategy.Strategy.ConcreteStrategyB;

/**
 * 전략 패턴은 실행(런타임) 중에 알고리즘 전략을 선택하여 객체 동작을 실시간으로 바꾸도록 할 수 있는 행위 디자인 패턴으로
 * '전략'이란 일종의 알고리즘이 될 수도 있으며 기능이나 동작이 될 수도 있는 특정한 목표를 수행하기 위한 행동 계획을 말한다.
 * 즉 어떤 일을 수행하는 알고리즘이 여러 가지 일 때 동작들을 미리 전략으로 정의함으로써 손쉽게 전략을 교체할 수 있는, 알고리즘 변형이 빈번하게 필요한 경우에 적합한 패턴이다.
 * <br><br>
 * GOF 디자인 패턴에서는 전략 패턴을 다음과 같이 정의한다.<br>
 * 1. 동일 계열의 알고리즘군을 정의하고 -> 전략 구현체로 정의<br>
 * 2. 각각의 알고리즘을 캡슐화하여 -> 인터페이스로 추상화<br>
 * 3. 이들을 상호 교환이 가능하도록 만든다. -> 합성(Composition)으로 구성<br>
 * 4. 알고리즘을 사용하는 클라이언트와 상관없이 독립적으로 -> Context 수정 없이<br>
 * 5. 알고리즘을 다양하게 변경할 수 있게 한다. -> 메서드를 통해 전략 객체를 실시간으로 변경함으로써 전략을 변경
 * <br><br><hr><br>
 * Strategy VS Template Method<br>
 * 패턴 유사점<br>
 * 1. 알고리즘을 때에 따라 적용한다는 컨셉<br>
 * 2. 개방 폐쇄 원칙(OCP)을 충족하고 코드를 변경하지 않고 소프트웨어 모듈을 쉽게 확장할 수 있도록 하는 데 사용할 수 있다.
 * <br><br>
 * 패턴 차이점<br>
 * 1. 전략 패턴은 합성(Composition)을 통해 해결책을 강구하며, 템플릿 메서드 패턴은 상속(Inheritance)을 통해 해결책을 제시한다.<br>
 * 2. 전략 패턴은 클라이언트와 객체 간의 결합이 느슨한 반면, 템플릿 메서드 패턴에서는 두 모듈이 더 밀접하게 결합된다.<br>
 * 3. 전략 패턴에서는 대부분 인터페이스를 사용하지만, 템플릿 메서드 패턴에서는 추상 클래스나 구상 클래스를 사용한다.<br>
 * 4. 전략 패턴에서는 전체 전략 알고리즘을 변경할 수 있지만, 템플릿 메서드 패턴에서는 알고리즘의 일부만 변경되고 나머지는 변경되지 않은 상태로 유지된다.(템플릿에 종속)<br>
 * 5. 따라서 단일 상속만이 가능한 자바에서 상속 제한이 있는 템플릿 메서드 패턴보다는 다양하게 많은 전략을 implements 할 수 있는 전략 패턴이 많이 사용되는 편이다.
 *
 */
@Slf4j
public class StrategyTest {

    /**
     * 클라이언트 - 전략 교체/전략 실행한 결과를 얻음
     */
    @Test
    public void strategyClient() {
        // 1. Context 생성
        StrategyContext context = new StrategyContext();

        // 2. 전략 설정
        context.setStrategy(new ConcreteStrategyA());

        // 3. 전략 실행
        context.doSomething();

        // 4. 다른 전략 설정
        context.setStrategy(new ConcreteStrategyB());

        // 5. 다른 전략 실행
        context.doSomething();
    }

    /**
     * 클라이언트 - 전략 제공/설정
     */
    @Test
    public void paymentClient_USER() {
        // 쇼핑카트 전략 컨텍스트 등록
        ShoppingCart cart = new ShoppingCart();

        // 쇼핑 물품
        Item A = new Item("맥북 프로", 3500000);
        Item B = new Item("플레이스테이션", 400000);
        cart.addItem(A);
        cart.addItem(B);

        // Kakao Card 결제 전략 실행
        cart.pay(new KakaoCardStrategy("Ethan", "0123456789", "999", "12/01"));

        // Naver Card 결제 전략 실행
        cart.pay(new NaverCardStrategy("yongseok993@gmail.com", "QE$#GWEFKN!@$%"));
    }

    /**
     * Java Strategy Pattern 예제
     */
    @Test
    public void comparatorTest() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(2);
        numbers.add(3);
        numbers.add(1);
        numbers.add(4);
        numbers.add(5);

        /**
         * Sort 메서드의 매개변수로 익명 클래스 Comparator 객체를 인스턴스화하여
         * 그 안의 compare 메서드 동작 로직(ConcreteStrategy) 직접 구현/할당
         */
        Collections.sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        log.info(numbers.toString());
    }
}
