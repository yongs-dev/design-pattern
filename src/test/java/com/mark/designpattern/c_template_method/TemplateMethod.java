package com.mark.designpattern.c_template_method;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TemplateMethod {

    static abstract class AbstractTemplate {

        /**
         * 템플릿 메서드 : final keyword를 사용해 오버라이딩 불가능<br>
         * 자식 클래스에서 상위 템플릿을 오버라이딩해서 자기 마음대로 바꾸도록 하는 행위를 원천 봉쇄
         */
        public final void templateMethod() {
            // 상속하여 구현되면 실행될 메서드
            step1();

            // hook method : 템플릿 메서드의 영향이나 순서를 제어하고 싶을 때 사용되는 메서드 형태
            if (hook()) {
                step2();
            }

            step3();
        }

        boolean hook() {
            return true;
        }

        protected abstract void step1();
        protected abstract void step2();
        protected abstract void step3();
    }

    static class ConcreteA extends AbstractTemplate {
        @Override
        protected void step1() {
            log.info("ConcreteA step1");
        }

        @Override
        protected void step2() {
            log.info("ConcreteA step2");
        }

        @Override
        protected void step3() {
            log.info("ConcreteA step3");
        }
    }

    static class ConcreteB extends AbstractTemplate {
        @Override
        protected void step1() {
            log.info("ConcreteB step1");
        }

        @Override
        protected void step2() {
            log.info("ConcreteB step2");
        }

        @Override
        protected void step3() {
            log.info("ConcreteB step3");
        }

        /**
         * hook method 오버라이드하여 템플릿에서 로직이 실행되지 않도록 제어
         */
        @Override
        boolean hook() {
            return false;
        }
    }
}
