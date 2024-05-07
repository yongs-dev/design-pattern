package com.mark.designpattern.m_builder;

import lombok.ToString;

public class SimpleBuilderPattern {}

@ToString
class Person {
    // final keyword 불변 객체 필드로 생성
    private final String name;
    private final String age;
    private final String gender;
    private final String job;
    private final String birthDay;
    private final String address;

    public static class Builder {
        // 필수 파라미터
        private final String name;
        private final String age;

        // 선택 파라미터
        private String gender;
        private String job;
        private String birthDay;
        private String address;

        // 필수 파라미터는 빌더 생성자로 받게 한다.
        public Builder(String name, String age) {
            this.name = name;
            this.age = age;
        }

        // 선택 파라미터는 각 메서드를 통해 정의한다.
        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder job(String job) {
            this.job = job;
            return this;
        }

        public Builder birthDay(String birthDay) {
            this.birthDay = birthDay;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

    // private 생성자 - 생성자는 외부에서 호출되는 것이 아닌 빌더 클래스에서만 호출
    private Person(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.gender = builder.gender;
        this.job = builder.job;
        this.birthDay = builder.birthDay;
        this.address = builder.address;
    }
}
