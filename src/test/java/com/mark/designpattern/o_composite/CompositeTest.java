package com.mark.designpattern.o_composite;

import org.junit.jupiter.api.Test;

/**
 * <b>복합체 패턴 (Composite Pattern)</b><br>
 * 복합 객체(Composite)와 단일 객체(Leaf)를 동일한 컴포넌트로 취급하여 클라이언트에게 이 둘을 구분하지 않고 동일한 인터페이스를 사용하도록 하는 구조 패턴이다.<br>
 * 복합체 패턴은 전체-부분의 관계를 갖는 객체들 사이의 관계를 트리 계층 구조로 정의해야 할 때 유용하다. 윈도우나 리눅스의 파일 시스템 구조를 떠올려보면 쉽게 이해할 수 있다.<br>
 * 폴더(디렉토리) 안에는 파일이 들어 있을 수도 있고 파일을 담은 또 다른 폴더도 들어있을 수 있다. 이를 복합적으로 담을 수 있다 해서 Composite 객체라고 불리운다.<br>
 * 반면 파일은 단일 객체이기 때문에 이를 Leaf 객체라고 불리운다. 즉 Leaf는 자식이 없다.<br>
 * 복합체 패턴은 바로 이 폴더와 파일을 동일한 타입으로 취급하여 구현을 단순화 시키는 것이 목적이다. 계층 구조를 구현하다 보면 자칫 복잡해 질 수도 있는 복합 객체를 재귀 동작을 통해 하위 객체들에게 작업을 위임한다.<br>
 * 정리하자면 Composite 패턴은 그릇과 내용물을 동일시해서 재귀적인 구조를 만들기 위한 디자인 패턴이다.<br>
 * <br><hr><br>
 * <b>패턴 사용 시기</b><br>
 * 1. 데이터를 계층적 트리 구조로 다뤄야 할 때<br>
 * 2. 복잡하고 난해한 단일/복합 객체 관계를 간편히 단순화하여 균일하게 처리하고 싶을 때<br>
 * <br><hr><br>
 * <b>패턴 장점</b><br>
 * 1. 단일체와 복합체를 동일하게 여기기 때문에 묶어서 연산하거나 관리할 때 편리하다.<br>
 * 2. 다형성 재귀를 통해 복잡한 트리 구조를 보다 편리하게 구성할 수 있다.<br>
 * 3. 수평적, 수직적 모든 방향으로 객체를 확장할 수 있다.<br>
 * 4. 새로운 Leaf 클래스를 추가하더라도 클라이언트는 추상화된 인터페이스만을 바라보기 때문에 개방 폐쇄 원칙(OCP)을 준수한다.(단일 부분의 확장 용이)<br>
 * <br><hr><br>
 * <b>패턴 단점</b><br>
 * 1. 재귀 호출 특성 상 트리의 깊이(Depth)가 깊어질 수록 디버깅에 어려움이 생긴다.<br>
 * 2. 설계가 지나치게 범용성을 갖기 때문에 새로운 요소를 추가할 때 복합 객체에서 구성 요소에 제약을 갖기 힘들다.<br>
 * 3. 예를 들어, 계층형 구조에서 Leaf 객체와 Composite 객체들을 모두 동일한 인터페이스로 다루어야하는데 이 공통 인터페이스 설계가 까다로울 수 있다.<br>
 * - 복합 객체가 가지는 부분 객체의 종류를 제한할 필요가 있을 때<br>
 * - 수평적 방향으로만 확장이 가능하도록 Leaf를 제한하는 Composite을 만들 때<br>
 */
public class CompositeTest {
    
    @Test
    public void compositeTest() {
        // 1. 최상위 복합체 생성
        Composite mainComposite = new Composite();

        // 2. 최상위 복합체에 저장할 Leaf와 또 다른 서브 복합체 생성
        Leaf leaf_1 = new Leaf();
        Composite subComposite = new Composite();

        // 3. 최상위 복합체에 개체 등록
        mainComposite.add(leaf_1);
        mainComposite.add(subComposite);

        // 4. 서브 복합체에 저장할 Leaf 생성
        Leaf leaf_2 = new Leaf();
        Leaf leaf_3 = new Leaf();
        Leaf leaf_4 = new Leaf();

        // 5. 서브 복합체에 개체 등록
        subComposite.add(leaf_2);
        subComposite.add(leaf_3);
        subComposite.add(leaf_4);

        // 6. 최상위 복합체의 모든 자식 노드 출력
        mainComposite.operation();
    }

    @Test
    public void fileSystemTest() {
        Folder root = new Folder("root");

        File file1 = new File("file1", 10);
        Folder sub1 = new Folder("sub1");
        Folder sub2 = new Folder("sub2");

        root.add(sub1);
        root.add(file1);
        root.add(sub2);

        File file11 = new File("file11", 10);
        File file12 = new File("file12", 10);

        sub1.add(file11);
        sub1.add(file12);

        File file21 = new File("file21", 10);
        sub2.add(file21);

        root.print();
    }
}
