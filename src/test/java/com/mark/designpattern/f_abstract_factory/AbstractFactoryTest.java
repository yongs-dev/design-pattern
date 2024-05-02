package com.mark.designpattern.f_abstract_factory;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <b>추상 팩토리 패턴 (Abstract Factory Pattern)</b><br>
 * 연관성이 있는 객체군이 여러 개 있을 경우 이들을 묶어 추상화하고 어떤 구체적인 상황이 주어지면 팩토리 객체에서 집합으로 묶은 객체군을 구현화하는 생성 패턴이다.<br>
 * 클라이언트에서 특정 객체를 사용할 때 팩토리 클래스만을 참조하여 특정 객체에 대한 구현부를 감추고 분리시킬 수 있다.<br>
 * <br><hr><br>
 * <b>Abstract Factory VS Factory Method</b><br>
 * 두 패턴 모두 팩토리 객체를 통해 구체적인 타입을 감추고 객체 생성에 관여하는 패턴임에는 동일하며 공장 클래스와 제품 클래스를 각각 나누어 느슨한 결합 구조를 구성하는 모습 역시 동일하다.<br>
 * 그러나 팩토리 메서드 패턴은 객체 생성 이후 해야 할 일의 공통점을 정의하는 데 초점을 맞추는 반면, 추상 팩토리 패턴은 생성해야 할 객체 집합군의 공통점에 초점을 맞춘다.<br>
 * <br><hr><br>
 * <b>패턴 사용 시기</b><br>
 * 1. 관련 제품의 다양한 제품군과 함께 작동해야 할 때, 해당 제품의 구체적인 클래스에 의존하고 싶지 않은 경우.<br>
 * 2. 여러 제품군 중 하나를 선택해서 시스템을 설정해야하고 한 번 구성한 제품을 다른 것으로 대체할 수도 있는 경유.<br>
 * 3. 제품에 대한 클래스 라이브러리를 제공하고 그등의 구현이 아닌 인터페이스를 노출시키고 싶을 때<br>
 * <br><hr><br>
 * <b>패턴 장점</b><br>
 * 1. 객체를 생성하는 코드를 분리하여 클라이언트 코드와 결합도를 낮출 수 있다.<br>
 * 2. 제품 군을 쉽게 대체 할 수 있다.<br>
 * 3. 단일 책임 원칙 준수(SRP) + 개방/폐쇄 원칙 준수(OCP)<br>
 * <br><hr><br>
 * <b>패턴 단점</b><br>
 * 1. 각 구현체마다 팩토리 객체를 모두 구현해주어야 하기 떄문에 객체가 늘어날 때 마다 클래스가 증가(팩토리 패턴의 공통적인 문제점)<br>
 * 2. 기존 추상 팩토리의 세부 사항이 변경되면 모든 팩토리에 대한 수정이 필요하다. 이는 추상 팩토리와 모든 서브 클래스의 수정을 가져온다.<br>
 * 3. 새로운 종류의 제품을 지원하는 것이 어렵다. 새로운 제품이 추가되면 팩토리 구현 로직 자체를 변경해야 한다.<br>
 */
@Slf4j
public class AbstractFactoryTest {
    
    @Test
    public void abstractFactoryTest() {
        AbstractFactory factory = null;

        // 1. 공장군 1을 가동
        factory = new ConcreteFactory1();

        // 2. 공장군 1을 통해 제품군 A1을 생성한다. (클라이언트는 구체적인 구현은 모르고 인터페이스에 의존)
        AbstractProductA product_A1 = factory.createProductA();
        assertThat(product_A1).isInstanceOf(ConcreteProductA1.class);

        // 3. 공장군 2를 가동
        factory = new ConcreteFactory2();

        // 2. 공장군 2를 통해 제품군 A2을 생성한다. (클라이언트는 구체적인 구현은 모르고 인터페이스에 의존)
        AbstractProductA product_A2 = factory.createProductA();
        assertThat(product_A2).isInstanceOf(ConcreteProductA2.class);
    }

    @Test
    public void factoryMethodComponentTest() {
        ComponentFactoryMethod factory;
        Button button;
        CheckBox checkBox;

        factory = ButtonFactory.getInstance();
        button = (Button) factory.createOperation("Window");
        button.render();
        assertThat(button).isInstanceOf(WindowButton.class);

        button = (Button) factory.createOperation("mac");
        button.render();
        assertThat(button).isInstanceOf(MacButton.class);

        factory = CheckBoxFactory.getInstance();
        checkBox = (CheckBox) factory.createOperation("Window");
        checkBox.render();
        assertThat(checkBox).isInstanceOf(WindowCheckBox.class);

        checkBox = (CheckBox) factory.createOperation("mac");
        checkBox.render();
        assertThat(checkBox).isInstanceOf(MacCheckBox.class);
    }

    @Test
    public void abstractFactoryComponentTest() {
        ComponentAbstractFactory factory;
        Button button;
        CheckBox checkBox;

        factory = WindowFactory.getInstance();
        button = factory.createButton();
        button.render();
        assertThat(button).isInstanceOf(WindowButton.class);

        checkBox = factory.createCheckBox();
        checkBox.render();
        assertThat(checkBox).isInstanceOf(WindowCheckBox.class);

        factory = MacFactory.getInstance();
        button = factory.createButton();
        button.render();
        assertThat(button).isInstanceOf(MacButton.class);

        checkBox = factory.createCheckBox();
        checkBox.render();
        assertThat(checkBox).isInstanceOf(MacCheckBox.class);
    }

    @Test
    public void abstractFactoryMethodComponentTest() {
        ComponentAbstractFactoryMethod factory;

        // 윈도우 컴포넌트 생성
        factory = new WindowFactoryMethod();
        for (Component c : factory.createOperation()) {
            log.info(c.toString());
            c.render();
        }

        // 맥 컴포넌트 생성
        factory = new MacFactoryMethod();
        for (Component c : factory.createOperation()) {
            log.info(c.toString());
            c.render();
        }
    }
}
