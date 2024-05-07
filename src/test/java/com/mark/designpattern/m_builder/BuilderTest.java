package com.mark.designpattern.m_builder;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * <b>빌더 패턴 (Builder Pattern)</b><br>
 * 복잡한 객체의 생성 과정과 표현 방법을 분리하여 다양한 구성의 인스턴스를 만드는 생성 패턴이다.<br>
 * 별도의 Builder 클래스를 만들어 메서드를 통해 step-by-step으로 값을 입력받은 후 최종적으로 build() 메서드로 하나의 인스턴스를 생성하여 리턴하는 패턴이다.<br>
 * 빌더 패턴을 이용하면 더이상 생성자 오버로딩 열거를 하지 않아도 되며 데이터의 순서에 상관없이 객체를 만들어내 생성자 인자 순서를 파악할 필요도 없고 잘못된 값을 넣는 실수도 하지 않게 된다.<br>
 * <br><hr><br>
 * <b>패턴 장점</b><br>
 * 1. 객체 생성 과정을 일관된 프로세스로 표현<br>
 * - 직관적으로 어떤 데이터에 어떤 값이 설정되는지 한눈에 파악할 수 있게 된다.<br>
 * 2. 디폴트 매개변수 생략을 간접적으로 지원<br>
 * - 파이썬이나 자바스크립트와 달리 자바 언어에선 기본적으로 메서드에 대한 디폴트 매개변수를 지원하지 않는다. 다만 빌더라는 객체 생성 전용 클래스를 경유하여 이용함으로써 디폴트 매개변수가 설정된 필드를 설정하는 메서드를 호출하지 않는 방식으로 간접 구현이 가능하다.<br>
 * 3. 필수 멤버와 선택적 멤버를 분리 가능<br>
 * - 빌더 클래스를 통해 초기화가 필수인 멤버는 빌더의 생성자로 받게 하여 필수 멤버를 설정해주어야 빌더 객체가 생성되도록 유도하고 선택적인 멤버는 빌더의 메서드로 받도록 하면, 사용자로 하여금 필수 멤버와 선택 멤버를 구분하여 객체 생성을 유도할 수 있다.<br>
 * 4. 객체 생성 단계를 지연할 수 있음<br>
 * - 빌더를 재사용 함으로써 객체 생성을 주도적으로 지연할 수 있다.<br>
 * 5. 초기화 검증을 멤버 별로 분리<br>
 * - 생성될 객체의 멤버 변수의 초기화와 검증을 각각의 멤버 별로 분리해서 작성할 수 있다. 빌더의 각각 멤버 설정 메서드에서 검증 과정을 분담함으로써 유지보수를 용이하게 한다.<br>
 * 6. 멤버에 대한 변경 가능성 최소화를 추구 (불변성 보장)<br>
 * - 불변 객체는 Thread-Safe 하여 동기화를 고려하지 않아도 된다.<br>
 * - 가변 객체를 통해 작업을 하는 도중 예외(Exception) 발생하면 개당 객체가 불안정한 상태에 빠질 수 있어 또 다른 에러를 유발할 수 있는 위험성이 있다.<br>
 * - 불변 객체로 구성하면 다른 사람이 개발한 함수를 위험없이 이용을 보장할 수 있어 협업에도 유지보수에도 유용하다.<br>
 * <br><hr><br>
 * <b>패턴 단점</b><br>
 * 1. 코드 복잡성 증가<br>
 * - N개의 클래스에 대해 N개의 새로운 빌더 클래스를 만들어야 해서 클래스 수가 급격히 늘어나 관리 포인트가 많아지고 구조가 복잡해질 수 있다.<br>
 * 2. 생성자 보다 성능은 떨어진다.<br>
 * - 매번 메서드를 호출하여 빌더를 거쳐 인스턴스화 하기 때문에 생성 비용이 생성자 방식에 비해 높다.<br>
 * 3. 지나친 빌더 남용은 금지<br>
 * - 클래스 필드의 개수가 4개 보다 적고 필드의 변경 가능성이 없는 경우라면 차라리 생성자나 정적 팩토리 메서드를 이용하는 것이 더 좋을 수 있다.<br>
 */
@Slf4j
public class BuilderTest {

    /**
     * <b>심플 빌더 패턴 (Simple Builder Pattern)</b><br>
     * 생성자가 많을 경우 또는 변경 불가능한 불변 객체가 필요한 경우 코드의 가독성과 일관성, 불변성을 유지하는 것에 중점을 둔다.<br>
     * 빌더 클래스가 구현할 클래스의 정적 내부 클래스로 구현된다.<br>
     * 1. 하나의 빌더 클래스는 하나의 대상 객체 생성만을 위해 사용된다. 그래서 두 클래스를 물리적으로 그룹핑함으로써 두 클래스간의 관계에 대한 파악을 쉽게 할 수 있다.<br>
     * 2. 생성자를 외부에 노출시키면 안되기 때문에 생성자를 private 하고 내부 빌더 클래스에서 private 생성자를 호출함으로써 오로지 빌더 객체에 의해 초기화 되도록 설계할 수 있다.<br>
     * 3. 정적 내부 클래스는 외부 클래스의 인스턴스 없이도 생성할 수 있는데 만일 일반 내부 클래스로 구성한다면 내부 클래스를 생성하기도 전에 외부 클래스를 인스턴스화 해야하는 모순이 발생한다.<br>
     * 4. 메모리 누수 문제 때문에 static으로 내부 클래스를 정의해야한다.<br>
     *
     */
    @Test
    public void simpleBuilderPattern() {
        Person person = new Person
                .Builder("홍길동", "26")
                .gender("man")
                .job("Warrior")
                .birthDay("1800.10.10")
                .address("조선")
                .build();

        log.info(person.toString());
    }

    /**
     * <b>디렉터 빌더 패턴 (Director Builder Pattern. GOF)</b><br>
     * GOF에서 정의하고 있는 디자인 패턴은 복잡한 객체의 생성 알고리즘과 조립 방법을 분리하여 빌드 공정을 구축하는 것이 목적이다. 빌더를 받아 조립 방법을 정의한 클래스를 Director 라고 부른다.<br>
     * Simple Builder Pattern은 하나의 대상 객체에 대한 생성만을 목적에 두지만, Director Builder Pattern은 여러 가지의 빌드 형식을 유연하게 처리하는 것에 목적을 둔다.
     */
    @Test
    public void directorBuilderPattern() {
        // 1. 포맷할 자바 데이터 생성
        Data data = new Data("홍길동", 44);

        // 2. 일반 텍스트로 포맷하여 출력하기
        Builder plainTextBuilder = new PlainTextBuilder(data);
        Director plainTextDirector = new Director(plainTextBuilder);
        String plainTextResult = plainTextDirector.build();
        log.info(plainTextResult);

        // 3. JSON 형식으로 포맷하여 출력하기
        Builder jsonBuilder = new JSONBuilder(data);
        Director jsonDirector = new Director(jsonBuilder);
        String jsonResult = jsonDirector.build();
        log.info(jsonResult);

        // 4. XML 형식으로 포맷하여 출력하기
        XMLBuilder xmlBuilder = new XMLBuilder(data);
        Director xmlDirector = new Director(xmlBuilder);
        String xmlResult = xmlDirector.build();
        log.info(xmlResult);
    }

    @Test
    public void lombokBuilder() {
        Member member = Member.builder("홍길동", "26") // 필수 파라미터
                .gender("man")
                .job("Warrior")
                .birthDay("1800.10.10")
                .address("조선")
                .build();

        log.info(member.toString());
    }
}
