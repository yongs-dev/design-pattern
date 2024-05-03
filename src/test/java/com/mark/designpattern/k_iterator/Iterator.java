package com.mark.designpattern.k_iterator;

public class Iterator {}

/**
 * 집합체 객체 (컬렉션)
 */
interface Aggregate {
    IIterator iterator();
}

class ConcreteAggregate implements Aggregate {
    Object[] arr;   // 데이터 집합 (컬렉션)
    int index = 0;

    public ConcreteAggregate(int size) {
        this.arr = new Object[size];
    }

    public void add(Object o) {
        if (index < arr.length) {
            arr[index] = o;
            index++;
        }
    }

    /**
     * 내부 컬렉션을 인자로 넣어 이더레티어 구현체를 클라이언트에 반환
     */
    @Override
    public IIterator iterator() {
        return new ConcreteIterator(arr);
    }
}

/**
 * 반복체 객체
 */
interface IIterator {
    boolean hasNext();
    Object next();
}

class ConcreteIterator implements IIterator {
    Object[] arr;
    private int nextIndex = 0;  // Cursor (for 문의 i 변수 역할)

    public ConcreteIterator(Object[] arr) {
        this.arr = arr;
    }

    @Override
    public boolean hasNext() {
        return nextIndex < arr.length;
    }

    @Override
    public Object next() {
        return arr[nextIndex++];
    }
}