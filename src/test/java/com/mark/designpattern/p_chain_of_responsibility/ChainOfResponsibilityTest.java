package com.mark.designpattern.p_chain_of_responsibility;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * <b>책임 연쇄 패턴 (Chain Of Responsibility Pattern)</b><br>
 * 클라이언트 요청에 대한 세세한 처리를 하나의 객체가 몽땅 하는 것이 아닌 여러 개의 처리 객체들로 나누고 이들을 사슬(Chain) 처럼 연결해 집합 안에서 연쇄적으로 처리하는 행동 패턴이다.<br>
 * 이러한 처리 객체들을 핸들러(Handler)라고 부르는데 요청을 받으면 각 핸들러는 요청을 처리할 수 있는지 확인한 후 없으면 체인의 다음 핸들러로 처리에 대한 책임을 전가한다.<br>
 * 각 객체를 부품으로 독립시키고 결합도를 느슨하게 만들며 상황에 따라서 요청을 처리할 객체가 변하는 프로그램에도 유연하게 대응할 수 있다.<br>
 * <br><hr><br>
 * <b>패턴 사용 시기</b><br>
 * 1. 특정 요청을 2개 이상의 여러 객체에서 판별하고 처리해야 할 때<br>
 * 2. 특정 순서로 여러 핸들러를 실행해야 하는 경우<br>
 * 3. 프로그램이 다양한 방식과 종류의 요청을 처리할 것으로 예상되지만 정확한 요청 유형과 순서를 미리 알 수 없는 경우<br>
 * 4. 요청을 처리할 수 있는 객체 집합이 동적으로 정의되어야 할 때(체인 연결을 런타임에서 동적으로 설정)<br>
 * <br><hr><br>
 * <b>패턴 장점</b><br>
 * 1. 클라이언튼느 처리 객체의 체인 집합 내부의 구조를 알 필요가 없다.<br>
 * 2. 각각의 체인은 자신이 해야하는 일만 하기 때문에 새로운 요청에 대한 처리 객체 생성이 편리해진다.<br>
 * 3. 클라이언트 코드를 변경하지 않고 핸들러를 체인에 동적으로 추가하거나 처리 순서를 변경하거나 삭제할 수 있어 유연해진다.<br>
 * 4. 요청의 호출자(Invoker)와 수신자(Receiver)를 분리시킬 수 있다.<br>
 * - 요청을 하는 쪽과 요청을 처리하는 쪽을 디커플링 시켜 결합도를 낮춘다.<br>
 * - 요청을 처리하는 방법이 바뀌더라도 호출자 코드는 변경되지 않는다.<br>
 * <br><hr><br>
 * <b>패턴 단점</b><br>
 * 1. 실행 시에 코드의 흐름이 많아 과정을 살펴보거나 디버깅 및 테스트가 쉽지 않다.<br>
 * 2. 집합 내부에서 무한 사이클이 발생할 수 있다.<br>
 * 3. 요청이 반드시 수행된다는 보장이 없다.(체인 끝까지 갔는데도 처리되지 않을 수 있다.)<br>
 * 4. 책임 연쇄로 인한 처리 지연 문제가 발생할 수 있다.<br>
 * - 트레이드 오프로서 요청과 처리에 대한 관계가 고정적이고 속도가 중요하면 책임 연쇄 패턴 사용을 유의하여야 한다.<br>
 */
@Slf4j
public class ChainOfResponsibilityTest {

    @Test
    public void chainOfResponsibilityTest() {
        // 1. 핸들러 생성
        Handler protocolHandler = new ProtocolHandler();
        Handler domainHandler = new DomainHandler();
        Handler portHandler = new PortHandler();

        // 2. 연들러 연결 설정
        protocolHandler.setNext(domainHandler).setNext(portHandler);

        // 3. 요청에 대한 처리 연쇄 실행
        String youtube = "https://www.youtube.com:80";
        log.info("INPUT : {}", youtube);
        protocolHandler.run(youtube);

        String naver = "https://www.naver.com:80";
        log.info("INPUT : {}", naver);
        protocolHandler.run(naver);

        String localhost = "http://localhost:8088";
        log.info("INPUT : {}", localhost);
        protocolHandler.run(localhost);
    }

    @Test
    public void authenticationTest() {
        // 1. 서버 생성 및 등록
        Server server = new Server();
        server.register("user1@naver.com", "123123");
        server.register("admin@gmail.com", "123123");
        server.register("user2@kakao.com", "123123");

        // 2. 인증 로직을 처리하는 핸들러 생성
        LimitLoginAttemptMiddleware limitLoginAttemptMiddleware = new LimitLoginAttemptMiddleware();
        AuthorizeMiddleware authorizeMiddleware = new AuthorizeMiddleware(server);
        AuthenticationMiddleware authenticationMiddleware = new AuthenticationMiddleware();
        LoggingMiddleware loggingMiddleware = new LoggingMiddleware();

        // 3. 핸들러 체인
        limitLoginAttemptMiddleware
                .setNext(authorizeMiddleware)
                .setNext(authenticationMiddleware)
                .setNext(loggingMiddleware);

        // 4. 클라이언트로부터 로그인 시도
        do {
            String email = "admin@gmail.com";
            String password = "123123";

            short result = limitLoginAttemptMiddleware.check(email, password);

            if (result == -2) {
                throw new RuntimeException("로그인 시도 횟수 초과로 프로그램을 종료합니다.");
            } else if (result == -1) {
                break;
            } else if (result == 0) {
                continue;
            }
        } while (true);
    }
}
