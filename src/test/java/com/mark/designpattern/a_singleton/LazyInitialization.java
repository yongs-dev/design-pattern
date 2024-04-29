package com.mark.designpattern.a_singleton;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LazyInitialization {

    private static LazyInitialization instance;

    /**
     * 지연 초기화(LazyInitialization)
     * 외부에서 정적 메서드를 호출하면 그때 초기화 진행
     */
    public static LazyInitialization getInstance() {
        if (instance == null) {
            instance = new LazyInitialization();
        }

        return instance;
    }
}
