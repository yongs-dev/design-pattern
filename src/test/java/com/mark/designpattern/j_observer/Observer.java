package com.mark.designpattern.j_observer;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

public class Observer {}

/**
 * 관찰 대상자 / 발행자
 */
interface ISubject {
    void registerObserver(IObserver o);
    void removeObserver(IObserver o);
    void notifyObserver();
}

@Slf4j
class ConcreteSubject implements ISubject {
    // 관찰자들을 등록하여 담는 리스트
    List<IObserver> observers = new ArrayList<>();

    @Override
    public void registerObserver(IObserver o) {
        observers.add(o);
        log.info("{} 구독 완료", o);
    }

    @Override
    public void removeObserver(IObserver o) {
        observers.remove(o);
        log.info("{} 구독 취소", o);
    }

    @Override
    public void notifyObserver() {
        for (IObserver o : observers) {
            o.update(); // 위임
        }
    }
}

/**
 * 관찰자 / 구독자
 */
interface IObserver {
    void update();
}

@Slf4j
class ObserverA implements IObserver {
    @Override
    public void update() {
        log.info("ObserverA 이벤트 알림이 도착했습니다.");
    }

    @Override
    public String toString() {
        return "ObserverA";
    }
}

@Slf4j
class ObserverB implements IObserver {
    @Override
    public void update() {
        log.info("ObserverB 이벤트 알림이 도착했습니다.");
    }

    @Override
    public String toString() {
        return "ObserverB";
    }
}