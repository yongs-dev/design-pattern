package com.mark.designpattern.a_singleton;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StaticBlockInitialization {

    @Getter
    private static StaticBlockInitialization instance;

    // static 블록을 이용해 Exception Handling
    static {
        try {
            instance = new StaticBlockInitialization();
        } catch (Exception e) {
            throw new RuntimeException("StaticBlockInitialization occurred error");
        }
    }
}
