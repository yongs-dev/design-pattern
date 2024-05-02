package com.mark.designpattern.f_abstract_factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Component {
    void render();
}

abstract class Button implements Component {}
abstract class CheckBox implements Component {}

@Slf4j
class WindowButton extends Button {
    @Override
    public void render() {
        log.info("윈도우 버튼 생성 완료");
    }
}

@Slf4j
class MacButton extends Button {
    @Override
    public void render() {
        log.info("맥 버튼 생성 완료");
    }
}

@Slf4j
class WindowCheckBox extends CheckBox {
    @Override
    public void render() {
        log.info("윈도우 체크박스 생성 완료");
    }
}

@Slf4j
class MacCheckBox extends CheckBox {
    @Override
    public void render() {
        log.info("맥 체크박스 생성 완료");
    }
}

// =============================================================================== //

/**
 * <b>1. Factory Method Pattern</b><br>
 * 기능 확장이 필요한 경우 각 메서드마다 있는 분기문 로직을 일일이 수정이 필요하다 (OCP 원칙 위배)
 */
interface ComponentFactoryMethod {

    // Template
    Component createOperation(String type);

    // Factory Method
    Component createComponent(String type);
}

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ButtonFactory implements ComponentFactoryMethod {
    private static class HOLDER {
        private static final ButtonFactory INSTANCE = new ButtonFactory();
    }

    public static ButtonFactory getInstance() {
        return HOLDER.INSTANCE;
    }

    @Override
    public Button createOperation(String type) {
        Button button = createComponent(type);
//        button.추가설정();
        return button;
    }

    @Override
    public Button createComponent(String type) {
        return switch (type.toLowerCase()) {
            case "window" -> new WindowButton();
            case "mac" -> new MacButton();
            default -> null;
        };
    }
}

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class CheckBoxFactory implements ComponentFactoryMethod {
    private static class HOLDER {
        private static final CheckBoxFactory INSTANCE = new CheckBoxFactory();
    }

    public static CheckBoxFactory getInstance() {
        return HOLDER.INSTANCE;
    }

    @Override
    public CheckBox createOperation(String type) {
        CheckBox checkBox = createComponent(type);
//        button.추가설정();
        return checkBox;
    }

    @Override
    public CheckBox createComponent(String type) {
        return switch (type.toLowerCase()) {
            case "window" -> new WindowCheckBox();
            case "mac" -> new MacCheckBox();
            default -> null;
        };
    }
}

// =============================================================================== //

/**
 * <b>2. Abstract Factory Pattern</b><br>
 * Linux OS 환경이 추가된다 하더라도 리눅스 컴포넌트 구현체와 리눅스 팩토리 클래스만 추가하면 확장이 완료된다.(OCP 원칙 준수)<br>
 * 그러나 새로운 OS가 아닌 새로운 컴포넌트인 툴팁이 추가된다고 생각하면 모든 서브 팩토리 클래스마다 툴팁 객체를 생성하는 createToolTip() 메서드를 추가해야 하는 문제점이 발생한다.<br>
 */

interface ComponentAbstractFactory {
    Button createButton();
    CheckBox createCheckBox();
}

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class WindowFactory implements ComponentAbstractFactory {
    private static class HOLDER {
        private static final WindowFactory INSTANCE = new WindowFactory();
    }

    public static WindowFactory getInstance() {
        return HOLDER.INSTANCE;
    }

    @Override
    public Button createButton() {
        return new WindowButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new WindowCheckBox();
    }
}

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class MacFactory implements ComponentAbstractFactory {
    private static class HOLDER {
        private static final MacFactory INSTANCE = new MacFactory();
    }

    public static MacFactory getInstance() {
        return HOLDER.INSTANCE;
    }

    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new MacCheckBox();
    }
}

// =============================================================================== //

/**
 * <b>Abstract Factory + Factory Method 패턴 조합</b><br>
 * 팩토리 메서드는 추상 메서드를 통한 다른 제품 구현과 더불어 객체 생성에 관한 전/후처리를 해주는 로직이 핵심이며 추상 팩토리는 여러 타입의 객체 군을 생성할 수 있는 것이 핵심이다.<br>
 * OS군 별로 추상 팩토리를 구성하며, 각 객체 생성 메서드에 대해서 팩토리 메서드로 구성한 예제이다.<br>
 */
interface ComponentAbstractFactoryMethod {
    // 서브 클래스에서 구현할 팩토리 메서드
    Button createButton();
    CheckBox createCheckBox();

    // 팩토리 템플릿
    default List<Component> createOperation() {
        Button button = createButton();
        CheckBox checkBox = createCheckBox();
//        button.추가설정();
//        checkBox.추가설정();

        return new ArrayList<>(Arrays.asList(button, checkBox));
    }
}

/**
 * 추상 팩토리
 */
class WindowFactoryMethod implements ComponentAbstractFactoryMethod {
    @Override
    public Button createButton() {
        return new WindowButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new WindowCheckBox();
    }
}

/**
 * 추상 팩토리
 */
class MacFactoryMethod implements ComponentAbstractFactoryMethod {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public CheckBox createCheckBox() {
        return new MacCheckBox();
    }
}