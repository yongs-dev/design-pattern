package com.mark.designpattern.o_composite;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

interface Component {
    void operation();
}

@Slf4j
class Leaf implements Component {

    @Override
    public void operation() {
        log.info("{} 호출", this);
    }
}

@Slf4j
class Composite implements Component {

    // Leaf와 Composite 객체 모두를 저장하여 관리하는 내부 리스트
    List<Component> components = new ArrayList<>();

    public void add(Component c) {
        components.add(c);
    }

    public void remove(Component c) {
        components.remove(c);
    }

    @Override
    public void operation() {
        log.info("{} 호출", this);

        // 내부 리스트를 순회하여 단일 Leaf이면 값을 출력하고 또다른 서브 복합 객체이면 다시 그 내부를 순회하는 재귀 함수 동작이 된다.
        for (Component c : components) {
            c.operation();
        }
    }

    public List<Component> getChild() {
        return components;
    }
}
