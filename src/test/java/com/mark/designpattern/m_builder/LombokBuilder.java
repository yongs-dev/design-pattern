package com.mark.designpattern.m_builder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

public class LombokBuilder {}

@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Member {
    private final String name;
    private final String age;
    private final String gender;
    private final String job;
    private final String birthDay;
    private final String address;

    /**
     * @Builder 어노테이션으로 빌더 패턴을 구현하면 필수 파라미터 적용을 지정할 수 없다.<br>
     * 따라서 대상 객체 안에 별도의 builder() 정적 메서드를 구현함으로써 빌더 객체를 생성하기 전에 필수 파라미터를 설정하도록 유도할 수 있고 또한 파라미터 검증 로직도 추가할 수 있다.
     */
    public static MemberBuilder builder(String name, String age) {
        // 빌더 파라미터 검증
        if (name == null || age == null) {
            throw new IllegalArgumentException("필수 파라미터 누락");
        }

        // 필수 파라미터를 미리 빌드한 빌더 객체를 반환(지연 빌더 원리)
        return new MemberBuilder().name(name).age(age);
    }

}
