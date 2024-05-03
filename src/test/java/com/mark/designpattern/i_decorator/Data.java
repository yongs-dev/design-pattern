package com.mark.designpattern.i_decorator;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

public class Data {}

/**
 * 원본 객체와 장식된 객체 모두를 묶는 인터페이스
 */
interface IData {
    void setData(int data);
    int getData();
}

/**
 * 장식될 원본 객체
 */
@Getter @Setter
class MyData implements IData {
    private int data;
}

/**
 * 장식자 추상 클래스
 */
abstract class MyDataDecorator implements IData {
    private IData myDataObj;

    MyDataDecorator(IData myDataObj) {
        this.myDataObj = myDataObj;
    }

    public void setData(int data) {
        this.myDataObj.setData(data);
    }

    public int getData() {
        return myDataObj.getData();
    }
}

/**
 * 장식자 클래스
 */
@Slf4j
class SynchronizedDecorator extends MyDataDecorator {
    SynchronizedDecorator(IData myDataObj) {
        super(myDataObj);
    }

    @Override
    public void setData(int data) {
        synchronized (this) {
            log.info("동기화된 data 처리를 시작합니다.");
            super.setData(data);    // 부모 메서드를 호출함으로써 자신을 감싸고 있는 장식자의 메서드를 호출
            log.info("동기화된 data 처리를 완료했습니다.");
        }
    }

    @Override
    public int getData() {
        synchronized (this) {
            log.info("동기화된 data 처리를 시작합니다.");
            int result = super.getData();
            log.info("동기화된 data 처리를 완료했습니다.");
            return result;
        }
    }
}

/**
 * 나중에 기능 추가 요구사항이 와도 코드 수정 없이 유연하게 클래스를 정의만 해주면 된다.
 */
@Slf4j
class TimerMeasureDecorator extends MyDataDecorator {
    private IData myDataObj;

    public TimerMeasureDecorator(IData myDataObj) {
        super(myDataObj);
    }

    @Override
    public void setData(int data) {
        long startTime = System.nanoTime();
        super.setData(data);
        long endTime = System.nanoTime();
        log.info("durationTimeSec: {}n/s", endTime - startTime);
    }

    @Override
    public int getData() {
        long startTime = System.nanoTime();
        int result = super.getData();
        long endTime = System.nanoTime();
        log.info("durationTimeSec: {}n/s", endTime - startTime);
        return result;
    }
}