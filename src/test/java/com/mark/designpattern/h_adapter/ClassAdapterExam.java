package com.mark.designpattern.h_adapter;

import lombok.extern.slf4j.Slf4j;

public class ClassAdapterExam {}

/**
 * Adaptee : 클라이언트에서 사용하고 싶은 기존의 서비스. 하지만 호환이 안되서 바로 사용 불가능
 */
@Slf4j
class ClassAdapteeService {
    void specificMethod(int specialData) {
        log.info("기존 서비스 기능 호출 {}", specialData);
    }
}

/**
 * Client Interface : 클라이언트가 접근해서 사용할 고수준의 어댑터 모듈
 */
interface ClassTarget {
    void method(int data);
}

/**
 * Adapter : Adaptee 서비스를 클라이언트에서 사용하게 할 수 있도록 호환 처리 해주는 어댑터
 */
class ClassAdapter extends ClassAdapteeService implements ClassTarget {

    /**
     * 어댑터의 메서드가 호출되면 부모 클래스 Adaptee 메서드 호출
     */
    @Override
    public void method(int data) {
        specificMethod(data);
    }
}