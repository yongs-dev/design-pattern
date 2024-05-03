package com.mark.designpattern.h_adapter;

import lombok.AllArgsConstructor;
import lombok.Setter;

/**
 * Object Adapter Pattern 사용
 */
public class Engine {}

interface ISortEngine {
    void setList();             // 정렬할 리스트
    void sort();                // 정렬 알고리즘
    void reverseSort();         // 역순 정렬 알고리즘
    void printSortListPretty(); // 정렬된 리스트를 예쁘게 출력
}

class A_SortEngine implements ISortEngine {

    @Override
    public void setList() {}

    @Override
    public void sort() {}

    @Override
    public void reverseSort() {}

    @Override
    public void printSortListPretty() {}
}

class B_SortEngine {
    public void setList() {}
    public void sorting(boolean isReverse) {}
}

@AllArgsConstructor
class SortEngineAdapter implements ISortEngine {

    // 두 엔진을 Composition 하여 이용
    A_SortEngine engine_A;
    B_SortEngine engine_B;

    @Override
    public void setList() {
        engine_B.setList();
    }

    @Override
    public void sort() {
        engine_B.sorting(false);    // 메서드 시그니처가 달라도 위임을 통해 호환 작업
    }

    @Override
    public void reverseSort() {
        engine_B.sorting(true);     // 메서드 시그니처가 달라도 위임을 통해 호환 작업
    }

    @Override
    public void printSortListPretty() {
        engine_A.printSortListPretty();      // B 엔진에 없는 기능을 A 엔진으로 실행
    }
}

/**
 * Client 역할을 하는 클래스 : Sort 엔진 객체를 받아 실행
 */
@Setter
class SortingMachine {
    ISortEngine engine;

    void sortingRun() {
        engine.setList();

        engine.sort();
        engine.printSortListPretty();

        engine.reverseSort();
        engine.printSortListPretty();
    }
}