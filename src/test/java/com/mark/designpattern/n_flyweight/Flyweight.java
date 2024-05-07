package com.mark.designpattern.n_flyweight;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

public class Flyweight {}

@Slf4j
class Memory {
    public static long size = 0;

    public static void print() {
        log.info("총 메모리 사용량 : {} MB", Memory.size);
    }
}

/**
 * ConcreteFlyweight : 플라이웨이트 객체는 불변성을 가져야한다. 변경되면 모든 것에 영향을 주기 떄문이다.
 */
final class TreeModel {
    long objSize = 90;  // 90MB
    String type;        // 나무 종류
    Object mesh;        // 메쉬
    Object texture;     // 나무 껍질 + 입사귀 텍스쳐

    public TreeModel(String type, Object mesh, Object texture) {
        this.type = type;
        this.mesh = mesh;
        this.texture = texture;

        // 나무 객체를 생성하여 메모리에 적재했으니 메모리 사용 크기 증가
        Memory.size += this.objSize;
    }
}

/**
 * UnsharedConcreteFlyweight<br>
 * Tree 클래스와 TreeModel 간의 관계를 맺어주어야 하는데 상속을 통해 해주어도 되고, 여기서는 합성(Composition)을 사용했다.
 */
class Tree {
    // 좌표 값과 나무 모델 참조 객체 크기를 합친 사이즈
    long objSize = 10;  // 10MB

    // 위치 변수
    double position_x;
    double position_y;

    // 나무 모델
    TreeModel model;

    public Tree(TreeModel model, double position_x, double position_y) {
        this.model = model;
        this.position_x = position_x;
        this.position_y = position_y;

        // 나무 객체를 생성하였으니 메모리 사용 크기 증가
    }
}

/**
 * FlyweightFactory
 */
@Slf4j
class TreeModelFactory {
    /**
     * Flyweight Pool - TreeModel 객체들을 Map으로 등록하여 캐싱
     */
    private static final Map<String, TreeModel> cache = new HashMap<>();    // static final 사용하여 Thread-Safe

    /**
     * static factory method
     */
    public static TreeModel getInstance(String key) {
        if (cache.containsKey(key)) {
            return cache.get(key);
        } else {
            TreeModel model = new TreeModel(key, new Object(), new Object());
            log.info("-- 나무 모델 객체 새로 생성 완료 --");
            cache.put(key, model);
            return model;
        }
    }
}

/**
 * Client
 */
@Slf4j
class Terrain {
    // 지형 타일 크기
    static final int CANVAS_SIZE = 10000;

    // 나무 렌더링
    public void render(String type, double position_x, double position_y) {
        // 1. 캐싱된 나무 모델 객체 가져오기
        TreeModel model = TreeModelFactory.getInstance(type);

        // 2. 재사용한 나무 모델 객체와 변화하는 속성인 좌표 값으로 나무 생성
        Tree tree = new Tree(model, position_x, position_y);
        log.info("X: {}, Y: {} 위치에 {} 나무 생성 완료.", position_x, position_y, type);
    }
}