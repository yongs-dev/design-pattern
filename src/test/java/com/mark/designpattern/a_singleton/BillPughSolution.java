package com.mark.designpattern.a_singleton;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BillPughSolution {

    /**
     * Inner class static 선언하여 싱글톤 클래스가 초기화되어도 Holder 내부는 메모리에 로드되지 않음
     * getInstance() 메서드를 호출할 때 Holder 내부 클래스의 static 멤버를 가져와 리턴하는데, 이때 내부 클래스가 한번만 초기화되면서 싱글톤 객체를 최초로 생성 및 리턴
     * final keyword 사용해 다시 값이 할당되지 않도록 방지
     */
    private static class BillPughSolutionHolder {
        private static final BillPughSolution INSTANCE = new BillPughSolution();
    }

    public static BillPughSolution getInstance() {
        return BillPughSolutionHolder.INSTANCE;
    }
}
