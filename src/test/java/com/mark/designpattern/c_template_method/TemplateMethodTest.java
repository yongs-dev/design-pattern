package com.mark.designpattern.c_template_method;

import org.junit.jupiter.api.Test;

import static com.mark.designpattern.c_template_method.TemplateMethod.*;

/**
 * 템플릿 메서드 패턴은 여러 클래스에서 공통으로 사용하는 메서드를 템플릿화 하여 상위 클래스에서 정의하고,
 * 하위 클래스마다 세부 동작 사항을 다르게 구현하는 패턴이다.<br>
 * 즉 변하지 않는 기능(템플릿)은 상위 클래스에 만들어두고 자주 변경되며 확장할 기능은 하위 클래스에서 만들도록 하여,
 * 상위의 메서드 실행 동작 순서는 고정하면서 세부 실행 내용은 다양화 될 수 있는 경우에 사용된다.<br>
 * 상속이라는 기술을 극대화하여 알고리즘의 뼈대를 맞추는 것에 초점을 둔다.
 * <br><br><hr><br>
 * 템플릿 메서드 패턴 사용 시기<br>
 * 1. 클라이언트가 알고리즘의 특정 단계만 확장하고, 전체 알고리즘이나 해당 구조는 확장하지 않도록 할 때<br>
 * 2. 동일한 기능은 상위 클래스에서 정의하면서 확장, 변화가 필요한 부분만 하위 클래스에서 구현할 때
 * <br><br>
 * 패턴 장점<br>
 * 1. 클라이언트가 대규모 알고리즘의 특정 부분만 재정의하도록 하여 알고리즘의 다른 부분에 발생하는 변경 사항의 영향을 덜 받도록 한다.<br>
 * 2. 상위 추상클래스로 로직을 공통화 하여 코드의 중복을 줄일 수 있다.<br>
 * 3. 서브 클래스의 역할을 줄이고, 핵심 로직을 상위 클래스에서 관리함으로서 관리가 용이해진다.<br>
 * - 할리우드 원칙(Hollywood Principle) : 고수준 모듈(인터페이스, 추상클래스)에 의존하고 고수준 모듈에서 연락(메서드 실행) 하라는 원칙이다.
 * <br><br>
 * 패턴 단점<br>
 * 1. 알고리즘의 제공된 골격에 의해 유연성 제한<br>
 * 2. 알고리즘 구조가 복잡할수록 템플릿 로직 형태 유지가 어려워진다.<br>
 * 3. 로직에 변화가 생겨 상위 클래스를 수정할 때 모든 서브 클래스의 수정이 필요할 수 있다.<br>
 * 4. 하위 클래스를 통해 기본 단계 구현을 억제하여 리스코프 치환 법칙을 위반할 여지가 있다.
 *
 */
public class TemplateMethodTest {

    @Test
    public void templateMethodTest() {
        AbstractTemplate templateA = new ConcreteA();
        templateA.templateMethod();

        AbstractTemplate templateB = new ConcreteB();
        templateB.templateMethod();
    }
}
