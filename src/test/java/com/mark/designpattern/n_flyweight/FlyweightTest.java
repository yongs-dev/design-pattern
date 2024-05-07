package com.mark.designpattern.n_flyweight;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <b>플라이웨이트 패턴 (Flyweight Pattern)</b><br>
 * 재사용 가능한 객체 인스턴스를 공유시켜 메모리 사용량을 최소화하는 구조 패턴이다.<br>
 * 간단히 말하면 캐시(Cache) 개념을 코드로 패턴화 한 것으로 보면 되는데 자주 변하는 속성(Extrinsic)과 변하지 않는 속성(Intrinsic)을 분리하고 변하지 않는 속성을 캐시하여 재사용해 메모리 사용을 줄이는 방식이다.<br>
 * 그래서 동일하거나 유사한 객체들 사이에 가능한 많은 데이터를 서로 공유하여 사용하도록 하여 최적하를 노리는 경량 패턴이라고도 불린다.<br>
 * <br><hr><br>
 * <b>Intrinsic, Extrinsic 상태</b><br>
 * - Intrinsic: '고유한, 본질적인' 이라는 의미를 가진다. 본질적인 상태란 인스턴스가 어떠한 상황에서도 변하지 않는 정보를 말한다. 그래서 값이 고정되어 있기에 언제 어디서 공유해도 문제가 없게 된다.<br>
 * - Extrinsic: '외적인, 비본질적인' 이라는 의미를 가진다. 인스턴스를 두는 장소나 상황에 따라서 변화하는 정보를 말한다. 그래서 값이 언제 어디서 변화할지 모르기 때문에 이를 캐시해서 공유할 수 없다.<br>
 * <br><hr><br>
 * <b>패턴 사용 시기</b><br>
 * 1. 애플리케이션에 의해 생성되는 객체의 수가 많아 저장 비용이 높아질 때<br>
 * 2. 생성된 객체가 오래도록 메모리에 상주하며 사용되는 횟수가 많을 때<br>
 * 3. 공통적인 인스턴스를 많이 생성하는 로직이 포함된 경우<br>
 * 4. 임베디드와 같이 메모리를 최소한으로 사용해야 하는 경우<br>
 * <br><hr><br>
 * <b>패턴 장점</b><br>
 * 1. 애플리케이션에서 사용하는 메모리를 줄일 수 있다.<br>
 * 2. 프로그램 속도를 개선할 수 있다.<br>
 * - new 인스턴스화를 하면 데이터가 생성되고 메모리에 적재되는 미량의 시간이 걸리게 된다.<br>
 * - 객체를 공유하면 인스턴스를 가져오기만 하면 되기 때문에 메모리 뿐만 아니라 속도도 향상시킬 수 있게 된다.<br>
 * <br><hr><br>
 * <b>패턴 단점</b><br>
 * 코드의 복잡도가 증가한다.
 */
public class FlyweightTest {

    @Test
    public void flyweightTest() {
        // 지형 생성
        Terrain terrain = new Terrain();

        // 지형에 Oak 나무 5 그루 생성
        for (int i = 0; i < 5; i++) {
            terrain.render(
                    "Oak",
                    ThreadLocalRandom.current().nextDouble() * Terrain.CANVAS_SIZE,
                    ThreadLocalRandom.current().nextDouble() * Terrain.CANVAS_SIZE
            );
        }

        // 지형에 Acacia 나무 5 그루 생성
        for (int i = 0; i < 5; i++) {
            terrain.render(
                    "Acacia",
                    ThreadLocalRandom.current().nextDouble() * Terrain.CANVAS_SIZE,
                    ThreadLocalRandom.current().nextDouble() * Terrain.CANVAS_SIZE
            );
        }

        // 지형에 Jungle 나무 5 그루 생성
        for (int i = 0; i < 5; i++) {
            terrain.render(
                    "Jungle",
                    ThreadLocalRandom.current().nextDouble() * Terrain.CANVAS_SIZE,
                    ThreadLocalRandom.current().nextDouble() * Terrain.CANVAS_SIZE
            );
        }

        // 총 메모리 사용률 출력
        Memory.print();
    }
}
