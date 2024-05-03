package com.mark.designpattern.j_observer;

import org.junit.jupiter.api.Test;

/**
 * <b>옵저버 패턴 (Observer Pattern)</b><br>
 * 옵저버(관찰자)들이 관찰하고 있는 대상자의 상태가 변화가 있을 때마다 대상자는 직접 목록의 각 관찰자들에게 통지하고 관찰자들은 알림을 받아 조치를 취하는 행동 패턴이다.<br>
 * 옵저버 패턴은 여타 다른 디자인 패턴들과 다르개 일대다(one-to-many) 의존성을 가지는데, 주로 분산 이벤트 핸들링 시스템을 구현하는 데 사용된다.<br>
 * 예를 들어 유튜브 채널은 발행자(Subject)가 되고 구독자들은 관찰자(Observer)가 되는 구조로 보면 된다.<br>
 * 옵저버 패턴은 사실 '관찰'하기 보단 갱신을 위한 힌트 정보를 '전달' 받길 기다린다고 보는 것이 적절하다. 능동적으로 대상을 관챃하는 것처럼 느껴지지만 사실 대상 객체로부터 수동적으로 전달 받기를 기다리고 있기 때문이다.<br>
 * 여타 다른 디자인 패턴과 똑같이 상호작용할 객체를 합성(Composition) 하고 메서드 위임을 통해 구성하는 코드 패턴임은 동일하지만, 핵심은 합성한 객체를 리스트로 관리하고 리스트에 있는 관찰자 객체들에게 모두 메서드 위임을 통한 전파 행위를 한다는 점을 기억하면 된다.<br>
 * <br><hr><br>
 * <b>옵저버 패턴 흐름</b><br>
 * 1. 한개의 관찰 대상자(Subject)와 여러 개의 관찰자(Observer A, B, C)로 일 대 다 관계로 구성<br>
 * 2. 관찰 대상 Subject의 상태가 바뀌면 변경 사항을 옵저버에게 통보한다.(notifyObserver)<br>
 * 3. 대상자로부터 통보 받은 Observer는 값을 바꿀 수도 있고 삭제하는 등 적절히 대응한다.(Update)<br>
 * 4. 또한 Observer들은 언제든 Subject의 그룹에서 추가/삭제 될 수 있다.<br>
 * <br><hr><br>
 * <b>패턴 사용 시기</b><br>
 * 1. 앱이 한정된 시간, 특정한 케이스에만 다른 객체를 관찰해야 하는 경우<br>
 * 2. 대상 객체의 상태가 변경될 때마다 다른 객체의 동작을 트리거해야 할 때<br>
 * 3. 한 객체의 상태가 변경되면 다른 객체도 변경해야 할 때. 그런데 어떤 객체들이 변경되어야 하는지 몰라도 될 때<br>
 * 4. MVC 패턴에서도 사용 됨(Model, View, Controller)<br>
 * - Model과 View의 관계는 Observer 패턴의 Subject 역할과 Observer 역할의 관계에 대응된다.<br>
 * - 하나의 Model에 복수의 View가 대응한다.<br>
 * <br><hr><br>
 * <b>패턴 장점</b><br>
 * 1. Subject의 상태 변경을 주기적으로 조회하지 않고 자동으로 감지할 수 있다.<br>
 * 2. 발행자의 코드를 변경하지 않고도 새 구독자 클래스를 도입할 수 있어 개방 폐쇄 원칙(OCP) 준수한다.<br>
 * 3. 런타임 시점에서 발행자와 구독 알림 관계를 맺을 수 있다.<br>
 * 4. 상태를 변경하는 객체(Subject)와 변경을 감지하는 객체(Observer)의 관계를 느슨하게 유지할 수 있다.(느슨한 결합)<br>
 * <br><hr><br>
 * <b>패턴 단점</b><br>
 * 1. 구독자는 알림 순서를 제어할 수 없고 무작위 순서로 알림을 받음<br>
 * - 하드 코딩으로 구현할 수는 있겠지만 복잡성과 결합성만 높아지기 때문에 추천되지는 않는 방법이다.<br>
 * 2. 옵저버 패턴을 자주 구성하면 구조와 동작을 알아보기 힘들어져 코드 복잡도가 증가한다.<br>
 * 3. 다수의 옵저버 객체를 등록 이후 해지하지 않는다면 메모리 누수가 발생할 수도 있다.<br>
 */
public class ObserverTest {

    @Test
    public void observerTest() {
        // 발행자 등록
        ISubject publisher = new ConcreteSubject();

        ObserverA oa = new ObserverA();
        ObserverB ob = new ObserverB();

        // 발행자를 구독할 관차자들 리스트로 등록
        publisher.registerObserver(oa);
        publisher.registerObserver(ob);

        // 관찰자에게 이벤트 전파
        publisher.notifyObserver();

        // ObserverB 구독 취소
        publisher.removeObserver(ob);

        // ObserverA 한테만 이벤트 전파
        publisher.notifyObserver();
    }

    @Test
    public void weatherTest() {
        WeatherAPI api = new WeatherAPI();

        api.registerObserver(new KoreanUser("둘리"));
        api.registerObserver(new KoreanUser("또치"));
        api.registerObserver(new KoreanUser("도우너"));

        api.measurementsChanged();
    }
}
