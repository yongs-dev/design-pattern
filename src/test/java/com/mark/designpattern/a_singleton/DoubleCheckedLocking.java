package com.mark.designpattern.a_singleton;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DoubleCheckedLocking {

    /**
     * Java에서는 스레드를 여러 개 사용할 경우 성능을 위해 각각의 스레드 변수를 메인 메모리(RAM)로부터 가져오는 것이 아니라 캐시(Cache) 메모리에서 가져오게 된다.
     * 문제는 비동기로 변수 값을 캐시에 저장하다가 각 스레드마다 할당되어있는 캐시 메모리의 변수 값이 일치 하지 않을 수 있다는 점이다.
     * 그래서 volatile 키워드를 통해 이 변수는 캐시에서 읽지 말고 메인 메모리에서 읽어오도록 지정해준다.
     */
    private static volatile DoubleCheckedLocking instance;

    public static DoubleCheckedLocking getInstance() {
        if (instance == null) {

            // 클래스 자체에 synchronized 키워드를 사용해 최초 동기화만 동기화 작업이 발생하도록 제어하여 리소스 낭비 최소화
            synchronized (DoubleCheckedLocking.class) {
                if (instance == null) {
                    instance = new DoubleCheckedLocking();
                }
            }
        }

        return instance;
    }
}
