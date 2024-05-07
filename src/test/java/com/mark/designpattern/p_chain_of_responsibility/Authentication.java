package com.mark.designpattern.p_chain_of_responsibility;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

public class Authentication {}

class Server {
    private Map<String, String> users = new HashMap<>();

    // 서버에 유저 등록
    public void register(String email, String password) {
        users.put(email, password);
    }

    public boolean hasEmail(String email) {
        return users.containsKey(email);
    }

    public boolean isValidPassword(String email, String password) {
        return users.get(email).equals(password);
    }
}

/**
 * Handler
 */
abstract class Middleware {
    protected Middleware nextMiddleware = null;

    public Middleware setNext(Middleware middleware) {
        this.nextMiddleware = middleware;
        return middleware;
    }

    /**
     * flag : 클라이언트 실행부에서 while 문을 빠져나가기 위한 조건 값으로 사용된다.
     * -2 : throw Exception
     * -1 : break (루프문 종료)
     * 0 : continue 동작 (처음부터 콘솔 입력 받기)
     * 1 : 그대로 처리 진행
     */
    public short check(String email, String password) {
        short flag = 1;

        if (nextMiddleware != null) {
            flag = nextMiddleware.check(email, password);
        }

        return flag;
    }
}

@Slf4j
class LimitLoginAttemptMiddleware extends Middleware {
    private int limit = 3;
    private int count = 0;

    @Override
    public short check(String email, String password) {
        short flag = 1;

        if (count > limit) {
            log.info("로그인 요청 횟수 제한");
            flag = -2;
        } else {
            flag = super.check(email, password);
        }

        count++;

        return flag;
    }
}

@Slf4j
@AllArgsConstructor
class AuthorizeMiddleware extends Middleware {
    private Server server;

    @Override
    public short check(String email, String password) {
        short flag = 1;

        if (!server.hasEmail(email)) {
            log.info("{} 이메일은 등록되지 않았습니다.", email);
            flag = 0;
        } else if (!server.isValidPassword(email, password)) {
            log.info("{} 패스워드가 잘못 입력됐습니다.", password);
            flag = 0;
        } else {
            flag = super.check(email, password);
        }

        return flag;
    }
}

@Slf4j
class AuthenticationMiddleware extends Middleware {

    @Override
    public short check(String email, String password) {
        short flag = 1;

        if (email.equals("admin@gmail.com")) {
            log.info("Hello, Admin!");
            flag = -1;
        } else {
            log.info("Hello, User!");
            flag = super.check(email, password);
        }

        return flag;
    }
}

@Slf4j
class LoggingMiddleware extends Middleware {

    @Override
    public short check(String email, String password) {
        log.info("요청을 로깅합니다.");
        return -1;
    }
}