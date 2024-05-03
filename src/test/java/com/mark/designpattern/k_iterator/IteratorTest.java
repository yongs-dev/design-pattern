package com.mark.designpattern.k_iterator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Iterator;

/**
 * <b>반복자 패턴 (Iterator Pattern)</b><br>
 * 일련의 데이터 집합에 대하여 순차적인 접근(순회)을 지원하는 패턴이다.<br>
 * 복잡하게 얽혀있는 자료 컬렉션들을 순회하는 알고리즘 전략을 정의하는 것을 Iterator Pattern이라고 한다.<br>
 * 컬렉션 객체 안에 들어있는 모든 원소들에 대한 접근 방식이 공통화 되어 있다면 어떤 종류의 컬렉션에서도 이더레이터만 뽑아내면 여러 전략으로 순회가 가능해 보다 다형적인 코드를 설계할 수 있다.<br>
 * <br><hr><br>
 * <b>패턴 사용 시기</b><br>
 * 1. 컬렉션에 상관 없이 객체 접근 순회 방식을 통일하고자 할 때<br>
 * 2. 컬렉션을 순회하는 다양한 방법을 지원하고 싶을 때<br>
 * 3. 컬렉션의 복잡한 내부 구조를 클라이언트로 부터 숨기고 싶은 경우(편의 + 보안)<br>
 * 4. 데이터 저장 컬렉션 종류가 변경 가능성이 있을 때<br>
 * - 클라이언트가 집합 객체 내부 표현 방식을 알고 있다면, 표현 방식이 달라지면 클라이언트 코드도 변경되어야 하는 문제가 발생한다.<br>
 * <br><hr><br>
 * <b>패턴 장점</b><br>
 * 1. 일관된 이더레이터 인터페이스를 사용해 여러 형태의 컬렉션에 대해 동일한 순회 방법을 제공한다.<br>
 * 2. 집합체의 구현과 접근하는 처리 부분을 반복자 객체로 분리해 결합도를 줄일 수 있다.<br>
 * 3. 순회 알고리즘을 별도의 반복자 객체에 추출하여 각 클래스의 책임을 분리해 단일 책임 원칙(SRP) 준수한다.<br>
 * 4. 데이터 저장 컬렉션 종류가 변경되어도 클라이언트는 구현 코드는 손상되지 않아 수정에는 닫혀 있어 개방 폐쇄 원칙(OCP) 준수한다.<br>
 * <br><hr><br>
 * <b>패턴 단점</b><br>
 * 1. 클래스가 늘어나고 복잡도가 증가한다.<br>
 * - 만일 앱이 간단한 컬렉션에서만 작동하는 경우 패턴을 적용하는 것은 복잡도만 증가할 수 있다.<br>
 * 2. 구현 방법에 따라 캡슐화를 위배할 수 있다.<br>
 */
@Slf4j
public class IteratorTest {

    @Test
    public void iteratorTest() {
        // 1. 집합체 생성
        ConcreteAggregate aggregate = new ConcreteAggregate(5);
        aggregate.add(1);
        aggregate.add(2);
        aggregate.add(3);
        aggregate.add(4);
        aggregate.add(5);

        // 2. 집합체에서 이더레이터 객체 반환
        IIterator iter = aggregate.iterator();

        while (iter.hasNext()) {
            log.info("{}", iter.next());
        }
    }

    @Test
    public void postTest() {
        // 1. 게시판 생성
        Board board = new Board();

        // 2. 게시판에 게시글을 포스팅
        board.addPost("디자인 패턴 공부", LocalDate.of(2020, 8, 30));
        board.addPost("Redis 공부", LocalDate.of(2022, 4, 5));
        board.addPost("Elastic Search 공부", LocalDate.of(2021, 1, 12));
        board.addPost("Reactor-Netty 공부", LocalDate.of(2024, 7, 22));
        board.addPost("AWS Cloud 공부", LocalDate.of(2023, 12, 1));

        Iterator<Post> listIter = board.getListPostIterator();
        while (listIter.hasNext()) {
            log.info(listIter.next().toString());
        }

        log.info("================================================================================");

        Iterator<Post> dateIter = board.getDatePostIterator();
        while (dateIter.hasNext()) {
            log.info(dateIter.next().toString());
        }
    }
}
