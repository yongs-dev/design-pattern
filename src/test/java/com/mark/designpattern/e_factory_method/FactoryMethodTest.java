package com.mark.designpattern.e_factory_method;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>팩토리 메서드 패턴 (Factory Method Pattern)</b><br>
 * 객체 생성을 공장(Factory) 클래스로 캡슐화 처리하여 대신 생성하게 하는 생성 디자인 패턴이다.<br>
 * 클라이언트에서 직접 new 연산자를 통해 제품 객체를 생성하는 것이 아닌, 제품 객체들을 도맡아 생성하는 공장 클래스를 만들고<br>
 * 이를 상속하는 서브 공장 클래스의 메서드에서 여러 가지 제품 객체 생성을 각각 책임 지는 것이다.<br>
 * 또한 객체 생성에 필요한 과정을 템플릿 처럼 미리 구성해놓고, 객체 생성에 관한 전/후처리를 통해 생성 과정을 다양하고 유연하게 정할 수 있는 특징이 있다.<br>
 * <br><hr><br>
 * <b>패턴 사용 시기</b><br>
 * 1. 클래스 생성과 사용의 처리 로직을 분리하여 결합도를 낮추고자 할 때<br>
 * 2. 코드가 동작해야 하는 객체의 유형과 종속성을 캡슐화를 통해 정보 은닉 처리 할 경우<br>
 * 3. 라이브러리 혹은 프레임워크 사용자에게 구성 요소를 확장하는 방법을 제공하려는 경우<br>
 * 4. 기존 객체를 재구성하는 대신 기존 객체를 재사용하여 리소스를 절약하고자 하는 경우<br>
 * - 상황에 따라 적절한 객체를 생성하는 코드는 자주 중복된다. 그리고 객체 생성 방식의 변화는 해당하는 모든 코드 부분을 변경해야 하는 문제가 발생한다.<br>
 * - 따라서 객체의 생성 코드를 별도의 클래스 / 메서드로 분리함으로써 객체 생성 변화에 대비를 하기 위해 팩토리 메서드 패턴을 이용한다.<br>
 * - 특정 기능의 구현은 별개의 클래스로 제공되는 것이 바람직한 설계이기 때문이다.<br>
 * <br><hr><br>
 * <b>패턴 장점</b><br>
 * 1. 생성자(Creator)와 구현 객체(Concrete Product)의 강한 결합 방지<br>
 * 2. 팩토리 메서드를 통해 객체의 생성 후 공통으로 할 일을 수행하도록 지정할 수 있다.<br>
 * 3. 캡슐화, 추상화를 통해 생성되는 객체의 구체적인 타입을 감출 수 있다.<br>
 * 4. 단일 책임 원칙(SRP) : 객체 생성 코드를 한 곳(패키지, 클래스 등)으로 이동하여 코드를 유지보수하기 쉽게 할 수 있어 원칙 충족<br>
 * 5. 개방/폐쇄 원칙(OCP) : 기존 코드를 수정하지 않고 새로운 유형의 제품 인스턴스를 프로그램에 도입할 수 있어 원칙 충족(확장성 있는 전체 프로젝트 구성 가능)<br>
 * <br><hr><br>
 * <b>패턴 단점</b><br>
 * 1. 각 제품 구현체마다 팩토리 객체들을 모두 구현해주어야 하기 때문에 구현체가 늘어날 때 마다 팩트리 클래스가 증가하여 서브 클래스 수가 폭발한다.<br>
 * 2. 코드의 복잡성이 증가한다.
 */
@Slf4j
public class FactoryMethodTest {

    @Test
    public void factoryMethodTest() {
        // 공장 객체 생성
        AbstractFactory[] factories = {new ConcreteFactoryA(), new ConcreteFactoryB()};

        // 제품 A 생성
        IProduct productA = factories[0].someOperation();
        assertThat(productA).isInstanceOf(ConcreteProductA.class);

        // 제품 B 생성
        IProduct productB = factories[1].someOperation();
        assertThat(productA).isInstanceOf(ConcreteProductA.class);
    }

    @Test
    public void shipTest() {
        // 전용 선박 생산 공작 객체를 통해 선박을 생성
        Ship containerShip = ContainerShipFactory.getInstance().orderShip("yongseok993@gmail.com");
        log.info(containerShip.toString());
        assertThat(containerShip).isInstanceOf(ContainerShip.class);

        Ship oilTankerShip = OilTankerShipFactory.getInstance().orderShip("yongseok993@gmail.com");
        log.info(oilTankerShip.toString());
        assertThat(oilTankerShip).isInstanceOf(OilTankerShip.class);
    }
}
