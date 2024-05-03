package com.mark.designpattern.i_decorator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * <b>데코레이터 패턴 (Decorator Pattern)</b><br>
 * 대상 객체에 대한 기능 확장이나 변경이 필요할 때 객체의 결합을 통해 서브 클래싱 대신 쓸 수 있는 유연한 대안 구조 패턴이다.<br>
 * 데코레이터 패턴을 이용하면 필요한 추가 기능의 조합을 런타임에서 동적으로 생성할 수 있다.<br>
 * 데코레이터 할 대상 객체를 새로운 행동들을 포함한 특수 장식자 객체에 넣어서 행동들을 해당 장식자 객체마다 연결시켜 서브 클래스로 구성할 때 보다 훨씬 유연하게 기능을 확장 할 수 있다.<br>
 * <br><hr><br>
 * <b>패턴 사용 시기</b><br>
 * 1. 객체의 책임과 행동이 상황에 따라 동적으로 기능 추가/삭제되는 경우<br>
 * 2. 객체의 결합을 통해 기능이 생성될 수 있는 경우<br>
 * 3. 객체를 사용하는 코드를 손상시키지 않고 런타임에 객체에 추가 동작을 할당할 수 있어야 하는 경우<br>
 * 4. 상속을 통해 서브클래싱으로 객체의 동작을 확장하는 것이 어색하거나 불가능 할 때<br>
 * <br><hr><br>
 * <b>패턴 장점</b><br>
 * 1. 데코레이터를 사용하면 서브클래스를 만들 때보다 훨씬 더 유연하게 기능을 확장할 수 있다.<br>
 * 2. 객체를 여러 데코레이터로 래핑하여 여러 동작을 결합할 수 있다.<br>
 * 3. 컴파일 타임이 아닌 런타임에 동적으로 기능을 변경할 수 있다.<br>
 * 4. 각 장식자 클래스마다 고유의 책임을 가져 단일 책임 원칙(SRP)을 준수한다.<br>
 * 5. 클라이언트 코드 수정 없이 기능 확장이 필요하면 장식자 클래스를 추가하면 되니 개방 폐쇄 원칙(OCP)를 준수한다.<br>
 * 6. 구현체가 아닌 인터페이스를 바라봄으로써 의존 역전 원칙(DIP)을 준수한다.<br>
 * <br><hr><br>
 * <b>패턴 단점</b><br>
 * 1. 장식자 일부를 제거하고 싶다면 Wrapper 스택에서 일정 Warpper를 제거하는 것은 어렵다.<br>
 * 2. 데코레이터를 조합하는 초기 생성 코드가 보기 안좋을 수 있다.(new A(new B(new C(new D()))))<br>
 * 3. 어느 장식자를 먼저 데코레이팅 하느냐에 따라 데코레이터 스택 순서가 결정지게 되는데 순서에 의존하지 않는 방식으로 데코레이터를 구현하기는 어렵다.<br>
 */
@Slf4j
public class DecoratorTest {

    @Test
    public void decoratorTest() {
        // 1. 원본 객체 생성
        IComponent obj = new ConcreteComponent();

        // 2. 장식 1 하기
        IComponent decoratorA = new ComponentDecoratorA(obj);
        decoratorA.operation(); // 장식된 객체의 장식된 기능 실행

        // 3. 장식 2 하기
        IComponent decoratorB = new ComponentDecoratorB(obj);
        decoratorB.operation(); // 장식된 객체의 장식된 기능 실행

        // 4. 장식 1 + 2 하기
        IComponent decoratorAB = new ComponentDecoratorA(new ComponentDecoratorB(obj));
        decoratorAB.operation();
    }

    @Test
    public void weaponTest() {
        // 1. 유탄발사기가 달린 총
        Weapon generade_rifle = new Generade(new BaseWeapon());
        generade_rifle.aim_and_fire();

        log.info("==============================================================");

        // 2. 개머리판을 장착하고 스코프를 달은 촘
        Weapon buttstock_scoped_rifle = new Buttstock(new Scoped(new BaseWeapon()));
        buttstock_scoped_rifle.aim_and_fire();

        log.info("==============================================================");

        // 3. 유탄발사기 + 개머리판 + 스코프가 달린 총
        Weapon buttstock_scoped_generade_rifle = new Buttstock(new Scoped(new Generade(new BaseWeapon())));
        buttstock_scoped_generade_rifle.aim_and_fire();
    }

    @Test
    public void dataTest() {
        // 동시성이 필요 없을 때
        IData data = new MyData();
        data.setData(0);
        log.info(String.valueOf(data.getData()));

        log.info("==============================================================");

        // 동시성이 필요할 때
        IData dataSync = new SynchronizedDecorator(data);
        dataSync.setData(1);
        log.info(String.valueOf(dataSync.getData()));

        log.info("==============================================================");

        IData dataTimer1 = new TimerMeasureDecorator(data);
        dataTimer1.setData(2);

        log.info("==============================================================");

        // 동시성이 적용된 로직 안의 코드를 시간 측정하고 싶을 때
        IData dataTimer2 = new SynchronizedDecorator(new TimerMeasureDecorator(data));
        dataTimer2.setData(3);

        log.info("==============================================================");

        // 동시성이 적용된 코드를 시간 측정하고 싶을 때
        IData dataTimer3 = new TimerMeasureDecorator(new SynchronizedDecorator(data));
        dataTimer3.setData(4);
    }
}
