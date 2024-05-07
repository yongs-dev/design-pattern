package com.mark.designpattern.p_chain_of_responsibility;

import lombok.extern.slf4j.Slf4j;

public class ChainOfResponsibility {}

/**
 * 구체적ㅇ니 핸들러를 묶는 인터페이스 (추상클래스)
 */
abstract class Handler {
    // 다음 체인으로 연결될 핸들러
    protected Handler nextHandler = null;

    public Handler setNext(Handler handler) {
        this.nextHandler = handler;
        return handler; // 메서드 체이닝 구성을 위해 인자를 그대로 반환
    }

    // 자식 핸들러에서 구체화 하는 추상 메서드
    protected abstract void process(String url);

    public void run(String url) {
        process(url);

        // 만일 핸들러가 연결된 게 있다면 다음 핸들러로 책임을 떠넘긴다.
        if (nextHandler != null) {
            nextHandler.run(url);
        }
    }
}

@Slf4j
class ProtocolHandler extends Handler {

    @Override
    protected void process(String url) {
        int index = url.indexOf("://");
        if (index != -1) {
            log.info("PROTOCOL : {}", url.substring(0, index));
        } else {
            log.info("NO PROTOCOL");
        }
    }
}

@Slf4j
class DomainHandler extends Handler {

    @Override
    protected void process(String url) {
        String domain;
        int startIndex = url.indexOf("://");
        int lastIndex = url.lastIndexOf(":");

        if (startIndex == -1) {
            if (lastIndex == -1) {
                domain = url;
            } else {
                domain = url.substring(0, lastIndex);
            }
        } else if (startIndex != lastIndex) {
            domain = url.substring(startIndex + 3, lastIndex);
        } else {
            domain = url.substring(startIndex + 3);
        }

        log.info("DOMAIN : {}", domain);
    }
}

@Slf4j
class PortHandler extends Handler {

    @Override
    protected void process(String url) {
        int index = url.lastIndexOf(":");
        if (index != -1) {
            try {
                log.info("PORT : {}", Integer.parseInt(url.substring(index + 1)));
            } catch (Exception ignored) {}
        }
    }
}