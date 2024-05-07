package com.mark.designpattern.m_builder;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class DirectorBuilderPattern {}

@Getter
@AllArgsConstructor
class Data {
    private String name;
    private int age;
}

@AllArgsConstructor
abstract class Builder {

    // 상속한 자식 클래스에서 사용하도록 protected 접근제어자 지정
    protected Data data;

    // Data 객체의 데이터들을 원하는 형태의 문자열 포맷을 해주는 메서드들 (머리 - 중간 - 끝 형식)
    public abstract String head();
    public abstract String body();
    public abstract String foot();
}

class PlainTextBuilder extends Builder {
    public PlainTextBuilder(Data data) {
        super(data);
    }

    @Override
    public String head() {
        return "";
    }

    @Override
    public String body() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ");
        sb.append(data.getName());
        sb.append(", Age: ");
        sb.append(data.getAge());
        return sb.toString();
    }

    @Override
    public String foot() {
        return "";
    }
}

class JSONBuilder extends Builder {
    public JSONBuilder(Data data) {
        super(data);
    }

    @Override
    public String head() {
        return "{\n";
    }

    @Override
    public String body() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\"Name\" : ");
        sb.append("\"").append(data.getName()).append("\", \n");
        sb.append("\t\"Age\" : ");
        sb.append("\"").append(data.getAge()).append("\"");
        return sb.toString();
    }

    @Override
    public String foot() {
        return "\n}";
    }
}

class XMLBuilder extends Builder {
    public XMLBuilder(Data data) {
        super(data);
    }

    @Override
    public String head() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
        sb.append("<DATA>\n");
        return sb.toString();
    }

    @Override
    public String body() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t<NAME>");
        sb.append(data.getName());
        sb.append("</NAME>");
        sb.append("\n\t<AGE>");
        sb.append(data.getAge());
        sb.append("</AGE>");
        return sb.toString();
    }

    @Override
    public String foot() {
        return "\n</DATA>";
    }
}

/**
 * 각 문자열 포맷 빌드 과정을 템플릿화 시킨 디렉터
 */
class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    /**
     * 일종의 빌드 템플릿 메서드라 보면 된다.
     */
    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append(builder.head());
        sb.append(builder.body());
        sb.append(builder.foot());
        return sb.toString();
    }
}