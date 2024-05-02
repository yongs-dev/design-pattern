package com.mark.designpattern.d_state;

import org.junit.jupiter.api.Test;

import static com.mark.designpattern.d_state.State.*;

/**
 * <b>상태 패턴 (State Pattern)</b><br>
 * 객체가 특정 상태에 따라 행위를 달리하는 상황에서 상태를 객체화 하여 상태가 행동을 할 수 있도록 위임하는 패턴<br>
 * 상태를 클래스로 표현하면 클래스를 교체해서 '상태의 변화'를 표현할 수 있고, 객체 내부 상태 변경에 따라 객체의 행동을 상태에 특화된 행동들로 분리해 낼 수 있으며 새로운 행동을 추가하더라도 다른 행동에 영향을 주지 않는다.<br>
 * 전략 패턴(Strategy-Pattern)이 '전략 알고리즘'을 클래스로 표현한 패턴이라면, 상태 패턴(State-Pattern)은 '객체 상태'를 클래스로 표현한 패턴이다.<br>
 * <br><hr><br>
 * <b>패턴 사용 시기</b><br>
 * 1. 객체의 행동(메서드)가 상태(state)에 따라 각기 다른 동작을 할 때<br>
 * 2. 상태 및 전환에 걸쳐 대규모 조건 분기 코드와 중복 코드가 많을 경우<br>
 * 3. 조건문의 각 분기를 별도의 클래스에 넣는 것이 상태 패턴의 핵심<br>
 * 4. 런타임단에서 객체의 상태를 유동적으로 변경해야 할 때<br>
 * <br><hr><br>
 * <b>패턴 장점</b><br>
 * 1. 상태에 따른 동작을 개별 클래스로 옮겨서 관리 할 수 있다.<br>
 * 2. 상태와 관련된 모든 동작을 각각의 상태 클래스에 분산시킴으로써 코드 복잡도를 줄일 수 있다.<br>
 * 3. 단일 책임 원칙을 준수할 수 있다.(특정 상태와 관련된 코드를 별도의 클래스로 구성)<br>
 * 4. 개방 폐쇄 원칙을 준수할 수 있다.(기존 State 클래스나 컨텍스트를 변경하지 않고 새 State를 도입할 수 있다.)<br>
 * <br><hr><br>
 * <b>패턴 단점</b><br>
 * 1. 상태 별로 클래스를 생성하므로 관리해야할 클래스 수 증가<br>
 * 2. 상태 클래스 갯수가 많고 상태 규칙이 자주 변경된다면 컨텍스트의 상태 변경 코드가 복잡해지게 될 수 있다.<br>
 * 3. 객체에 적용할 상태가 몇 가지 밖에 없거나 거의 상태 변경이 이루어지지 않는 경우 패턴을 적용하는 것이 과도할 수 있다.<br>
 */
public class StateTest {

    @Test
    public void stateTest() {
        LaptopContext laptop = new LaptopContext();
        laptop.currentStatePrint();

        // 노트북 켜기 : OffState -> OnState;
        laptop.powerButtonPush();
        laptop.currentStatePrint();
        laptop.typeButtonPush();

        // 노트북 절전 모드 : OnState -> SavingState
        laptop.setSavingState();
        laptop.currentStatePrint();

        // 노트북 다시 켜기 : SavingState -> OnState
        laptop.powerButtonPush();
        laptop.currentStatePrint();

        // 노트북 끄기 : OnState -> OffState
        laptop.powerButtonPush();
        laptop.currentStatePrint();
    }
}
