package com.mark.designpattern.a_singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Lazy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 싱글톤 패턴이란 단 하나의 유일한 객체를 만들기 위한 코드 패턴
 * 메모리 절약을 위해 인스턴스가 필요할 때 똑같은 인스턴스를 새로 만들지 않고 기존의 인스턴스를 가져와 활용하는 기법
 * 리소스를 많이 차지하는 역할을 하는 무거운 클래스일 때 적용하기 적합하다.
 * 대표적으로 데이터베이스 연결 모듈을 들 수 있는데 I/O 바운드 그 자체로 무거운 작업에 속하며 한 번만 객체를 생성하고 돌려쓰면 되기 때문
 * 이 밖에도 디스크 연결, 네트워크 통신, DBCP 커넥션 풀, 스레드 풀, 캐시, 로그 기록 객체 등에 이용된다.
 */
public class SingletonTest {
    /**
     * 싱글톤 패턴 구현 기법
     * 1. Eager Initialization
     * 2. Static block initialization
     * 3. Lazy initialization
     * 4. Thread safe initialization
     * 5. Double-Checked Locking
     * ***6. Bill Pugh Solution
     * ***7. Enum 이용
     */

    /**
     * 한 번만 미리 만들어두는 가장 직관적이고 심플한 기법
     * static final 사용해 멀티 스레드 환경에서도 안전함
     * 그러나 static 멤버는 당장 객체를 사용하지 않더라도 메모리에 적재하기 때문에 만일 리소스가 큰 객체일 경우 공간 자원 낭비가 발생
     * 예외 처리를 할 수 없는 단점이 있음
     * 만일 싱글톤 객체가 그리 크지 않은 객체라면 이 기법으로 적용해도 무리는 없다
     */
    @Test
    public void eagerInitializationClient() {
        EagerInitialization instance1 = EagerInitialization.getInstance();
        EagerInitialization instance2 = EagerInitialization.getInstance();
        assertThat(instance1).isSameAs(instance2);
    }

    /**
     * static block 이용해 예외 핸들링 가능
     * 그러나 여전히 static의 특성으로 사용하지 않는데도 메모리 공간을 사용함
     */
    @Test
    public void staticBlockInitializationTest() {
        StaticBlockInitialization instance1 = StaticBlockInitialization.getInstance();
        StaticBlockInitialization instance2 = StaticBlockInitialization.getInstance();
        assertThat(instance1).isSameAs(instance2);
    }

    /**
     * 객체 생성에 대한 관리를 내부적으로 처리
     * 메서드를 호출했을 때 인스턴스 변수의 null 유무에 따라 초기화 하거나 기존 인스턴스를 반환
     * 미사용 고정 메모리 차지의 한계점을 극복
     * 그러나 Thread Safe 하지 않는 치명적인 단점을 가지고 있음
     */
    @Test
    public void lazyInitializationTest() {
        LazyInitialization instance1 = LazyInitialization.getInstance();
        LazyInitialization instance2 = LazyInitialization.getInstance();
        assertThat(instance1).isSameAs(instance2);
    }

    /**
     * synchronized 키워드를 통해 메서드에 쓰레드들을 하나씩 접근하도록 동기화
     * 여러 개의 모듈들이 매번 객체를 가져올 때 동기화 처리 작업에 overhead 발생해 성능 하락이 발생한다.
     */
    @Test
    public void threadSafeInitializationTest() {
        ThreadSafeInitialization instance1 = ThreadSafeInitialization.getInstance();
        ThreadSafeInitialization instance2 = ThreadSafeInitialization.getInstance();
        assertThat(instance1).isSameAs(instance2);
    }

    /**
     * 최초 초기화 할 때만 synchronized 적용하고 이미 만들어진 인스턴스를 반환할때는 사용하지 않도록 하는 기법
     * 이때 인스턴스 필드에 volatile 키워드를 사용해야 I/O 불일치 문제 해결 가능
     * JVM에 따라 스레드 세이프 하지 않는 경우가 발생하기 때문에 사용 지양
     */
    @Test
    public void doubleCheckedLockingTest() {
        DoubleCheckedLocking instance1 = DoubleCheckedLocking.getInstance();
        DoubleCheckedLocking instance2 = DoubleCheckedLocking.getInstance();
        assertThat(instance1).isSameAs(instance2);
    }

    /**
     * 멀티스레드 환경에서 안전하고 Lazy Loading(지연 초기화)도 가능한 완벽한 싱글톤 기법
     * 클래스 안에 내부 클래스(Holder)를 사용해 JVM의 클래스로더 매커니즘과 클래스가 로드되는 시점을 이용한 기법(Thread-Safe)
     * 다만 클라이언트가 임의로 싱글톤을 파괴할 수 있다는 단점을 지님(Reflection API, 직렬화/역직렬화 등)
     */
    @Test
    public void billPughSolutionTest() {
        BillPughSolution instance1 = BillPughSolution.getInstance();
        BillPughSolution instance2 = BillPughSolution.getInstance();
        assertThat(instance1).isSameAs(instance2);
    }

    /**
     * private 멤버로 만들고 합 번만 초기화하는 enum 특성을 활용해 Thread-Safe
     * 클라이언트의 Reflection 공격에도 안전
     * 클래스 상속이 필요한 경우 enum 외의 클래스 상속은 불가
     */
    @Test
    public void enumTest() {
        EnumSingleton instance = EnumSingleton.getInstance();
        assertThat(instance.getClient()).isInstanceOf(ConcurrentHashMap.class);
    }
}
