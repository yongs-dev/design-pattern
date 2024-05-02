package com.mark.designpattern.e_factory_method;

/**
 * 공장 객체 추상화<br>
 * 추상 클래스 -> 인터페이스 (default Method 활용) 가능
 */
abstract class AbstractFactory {

    /**
     * 객체 생성 전/후처리 메서드
     */
    final IProduct someOperation() {
        IProduct product = createProduct(); // 서브 클래스에서 구체화한 팩토리 메서드 실행
        product.setting();                  // 이밖의 객체 생성에 가미할 로직 실행
        return product;                     // 제품 객체를 생성 및 추가 설정 후 완성된 제품 반환
    }

    /**
     * 팩토리 메서드 : 구체적인 객체 생성 종류는 각 서브 클래스에 위임
     * protected 이기 때문에 외부에 노출이 안 됨
     */
    abstract protected IProduct createProduct();
}

class ConcreteFactoryA extends AbstractFactory {

    @Override
    public IProduct createProduct() {
        return new ConcreteProductA();
    }
}

class ConcreteFactoryB extends AbstractFactory {
    @Override
    public IProduct createProduct() {
        return new ConcreteProductB();
    }
}
