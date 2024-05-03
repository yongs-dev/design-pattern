package com.mark.designpattern.h_adapter;

import org.junit.jupiter.api.Test;

/**
 * <b>어댑터 패턴 (Adapter Pattern)</b><br>
 * 호환성이 없는 인터페이스 때문에 함께 동작할 수 없는 클래스들을 함께 작동해주도록 변환 역할을 해주는 행동 패턴이다.<br>
 * 기존에 있는 시스템에 새로운 써드파티 라이브러리를 추가하고 싶거나, Legacy 인터페이스를 새로운 인터페이스로 교체하는 경우에 어댑터 패턴을 사용하면 코드의 재사용성을 높일 수 있다.<br>
 * 즉 어댑터란 서로 간의 인터페으스를 어댑터로 일치시켜줌으로써 호환성 및 신규 기능 확장을 할 수 있다고 보면 된다.<br>
 * 어댑터가 Legacy 인터페이스를 감싸서 새로운 인터페이스로 변환하기 떄문에 Wrapper패턴이라고도 불리운다.<br>
 * <br><hr><br>
 * <b>객체 어댑터 (Object Adapter)</b><br>
 * 합성(Composition)된 멤버에게 위임을 이용한 어댑터 패턴(추천)<br>
 * 자기가 해야 할 일을 클래스 멤버 객체의 메소드에게 다시 시킴으로써 목적을 당성하는 것을 위임이라고 한다.<br>
 * 합성을 활용했기 때문에 런타임 중에 Adaptee(Service)가 결정되어 유연하다.<br>
 * 그러나 Adaptee(Service) 객체를 필드 변수로 저장해야 되기 때문에 공간 차지 비용이 든다.<br>
 * <br><hr><br>
 * <b>클래스 어댑터 (Class Adapter)</b><br>
 * 클래스 상속(Inheritance)을 이용한 어댑터 패턴<br>
 * Adaptee(Service)를 상속했기 때문에 따로 객체 구현 없이 바로 코드 재사용이 가능하다.<br>
 * 상속은 대표적으로 기존에 구현된 코드를 재사용하는 방식이지만 자바에서는 다중 상속 불가 문제 때문에 전반적으로 권장하지 않는 방법이다.<br>
 * <br><hr><br>
 * <b>패턴 사용 시기</b><br>
 * 1. 레거시 코드를 사용하고 싶지만 새로운 인터페이스가 레거시 코드와 호환되지 않을 때<br>
 * 2. 이미 만든 것을 재사용하고자 하나 이 재사용 가능한 라이브러리를 수정할 수 없을 때<br>
 * 3. 이미 만들어진 클래스를 새로운 인터페이스(API)에 맞게 개조할 때<br>
 * 4. 소프트웨어의 구 버전과 신 버전을 공존시키고 싶을 때<br>
 * <br><hr><br>
 * <b>패턴 장점</b><br>
 * 1. 프로그램의 기본 비즈니스 로직에서 인터페이스 또는 데이터 변환 코드를 분리할 수 있기 때문에 단일 책임 원칙(SRP) 만족<br>
 * 2. 기존 클래스 코드를 건들지 않고 클라이언트 인터페이스를 통해 어댑터와 작동하기 때문에 개방 폐쇄 원칙(OCP) 만족<br>
 *  <br><hr><br>
 * <b>패턴 단점</b><br>
 * 1. 새로운 인터페이스와 어댑터 클래스 세트를 도입해야 하기 때문에 코드의 복잡성이 증가한다.<br>
 * 2. 때로는 직접 서비스(Adaptee) 클래스를 변경하는 것이 간단할 수 있는 경우가 있기 때문에 신중히 선택해야 한다.<br>
 */
public class AdapterTest {

    @Test
    public void objectAdapterTest() {
        // 1. 어댑터 생성 (기존 서비스를 인자로 받아 호환 작업 처리)
        ObjectTarget adapter = new ObjectAdapter(new ObjectAdapteeService());

        // 2. Client Interface 스펙에 따라 메서드를 실행하면 기존 서비스의 메서드가 실행된다.
        adapter.method(1);
    }

    @Test
    public void classAdapterTest() {
        // 1. 어댑터 생성
        ClassTarget adapter = new ClassAdapter();

        // 2. 인터페이스의 스펙에 따라 메서드르르 실행하면 기존 서비스의 메서드 실행
        adapter.method(1);
    }



    @Test
    public void engineTest() {
        // 클라이언트의 머신에 원본 엔진 대신 어댑터를 할당
        ISortEngine adapter = new SortEngineAdapter(new A_SortEngine(), new B_SortEngine());
        SortingMachine machine = new SortingMachine();
        machine.setEngine(adapter);

        machine.sortingRun();
    }
}
