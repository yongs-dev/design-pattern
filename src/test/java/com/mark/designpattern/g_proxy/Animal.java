package com.mark.designpattern.g_proxy;

import lombok.extern.slf4j.Slf4j;

public interface Animal {
    void eat();
}

@Slf4j
class Tiger implements Animal {
    @Override
    public void eat() {
        log.info("호랑이가 음식을 먹습니다.");
    }
}
