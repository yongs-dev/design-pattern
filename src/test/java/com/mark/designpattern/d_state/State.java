package com.mark.designpattern.d_state;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class State {

    interface PowerState {
        void powerButtonPush(LaptopContext ctx);
        void typeButtonPush();
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class OnState implements PowerState {
        private static class Holder {
            private static final OnState INSTANCE = new OnState();
        }

        public static OnState getInstance() {
            return Holder.INSTANCE;
        }

        @Override
        public void powerButtonPush(LaptopContext cxt) {
            log.info("노트북 전원 OFF");
            cxt.changeState(OffState.getInstance());
        }

        @Override
        public void typeButtonPush() {
            log.info("키 입력");
        }

        @Override
        public String toString() {
            return "노트북이 전원 ON 상태 입니다.";
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class OffState implements PowerState {

        private static class HOLDER {
            private static final OffState INSTANCE = new OffState();
        }

        public static OffState getInstance() {
            return HOLDER.INSTANCE;
        }

        @Override
        public void powerButtonPush(LaptopContext cxt) {
            log.info("노트북 전원 ON");
            cxt.changeState(OnState.getInstance());
        }

        @Override
        public void typeButtonPush() {
            throw new IllegalStateException("노트북이 전원 OFF 상태");
        }

        @Override
        public String toString() {
            return "노트북이 전원 OFF 상태 입니다.";
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static class SavingState implements PowerState {
        private static class HOLDER {
            private static final SavingState INSTANCE = new SavingState();
        }

        public static SavingState getInstance() {
            return HOLDER.INSTANCE;
        }

        @Override
        public void powerButtonPush(LaptopContext cxt) {
            log.info("노트북 전원 ON");
            cxt.changeState(OnState.getInstance());
        }

        @Override
        public void typeButtonPush() {
            throw new IllegalStateException("노트북이 절전 모드인 상태");
        }

        @Override
        public String toString() {
            return "노트북이 절전 모드 상태 입니다.";
        }
    }

    static class LaptopContext {
        PowerState powerState;

        LaptopContext() {
            this.powerState = OffState.getInstance();
        }

        void changeState(PowerState state) {
            this.powerState = state;
        }

        void setSavingState() {
            log.info("노트북 절전 모드");
            changeState(SavingState.getInstance());
        }

        void powerButtonPush() {
            powerState.powerButtonPush(this);
        }

        void typeButtonPush() {
            powerState.typeButtonPush();
        }

        void currentStatePrint() {
            log.info(powerState.toString());
        }
    }
}
