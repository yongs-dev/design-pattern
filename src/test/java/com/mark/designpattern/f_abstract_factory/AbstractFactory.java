package com.mark.designpattern.f_abstract_factory;

interface AbstractFactory {
    AbstractProductA createProductA();
    AbstractProductB createProductB();
}

class ConcreteFactory1 implements AbstractFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ConcreteProductA1();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ConcreteProductB1();
    }
}

class ConcreteFactory2 implements AbstractFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ConcreteProductA2();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ConcreteProductB2();
    }
}

interface AbstractProductA {}

class ConcreteProductA1 implements AbstractProductA {}

class ConcreteProductA2 implements AbstractProductA {}

interface AbstractProductB {}

class ConcreteProductB1 implements AbstractProductB {}

class ConcreteProductB2 implements AbstractProductB {}

