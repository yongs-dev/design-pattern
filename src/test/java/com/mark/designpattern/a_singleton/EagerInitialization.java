package com.mark.designpattern.a_singleton;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

// 생성자를 private로 선언(외부에서 인스턴스 생성 방지)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EagerInitialization {

    private static final EagerInitialization INSTANCE = new EagerInitialization();

    public static EagerInitialization getInstance() {
        return INSTANCE;
    }
}
