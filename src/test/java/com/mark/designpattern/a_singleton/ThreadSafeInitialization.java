package com.mark.designpattern.a_singleton;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ThreadSafeInitialization {

    private static ThreadSafeInitialization instance;

    // synchronized keyword
    public static synchronized ThreadSafeInitialization getInstance() {
        if (instance == null) {
            instance = new ThreadSafeInitialization();
        }

        return instance;
    }
}
