package com.mark.designpattern.g_proxy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <b>프록시 패턴 (Proxy Pattern)</b><br>
 * 대상 원본 객체를 대리하여 대신 처리하게 함으로써 로직의 흐름을 제어하는 행동 패턴으로 클라이언트가 대상 객체를 직접 쓰는 게 아니라 중간에 Proxy(대리인) 거쳐서 쓰는 코드 패턴이다.<br>
 * 대상 객체(Subject)의 메소드를 직접 실행하는 것이 아닌 대상 객체에 접근하기 전에 프록시(Proxy) 객체의 메서드를 접근한 후 추가적인 로직을 처리한 뒤 접근하게 된다.<br>
 * <br><hr><br>
 * <b>프록시를 사용하는 이유</b><br>
 * 1. 보안(Security) : 프록시는 클라이언트가 작업을 수행할 수 있는 권한이 있는지 확인하고 검사 결과가 긍정적인 경우에만 요청을 대상으로 전달한다.<br>
 * 2. 캐싱(Caching) : 프록시가 내부 캐시를 유지하여 데이터가 캐시에 아직 존재하지 않는 경우에만 대상에서 작업이 실행되도록 한다.<br>
 * 3. 데아터 유효성 검사(Data Validation) : 프록시가 입력을 대상으로 전달하기 전에 유효성을 검사한다.<br>
 * 4. 지연 초기화(Lazy Initialization) : 대상의 생성 비용이 비싸다면 프록시는 그것을 필요로 할 때까지 연기한다.<br>
 * 5. 로깅(Logging) : 프록시는 메서드 호출과 상대 매개 변수를 인터셉트하고 이를 기록한다.<br>
 * 6. 원격 객체(Remote Objects) : 프록시는 원격 위치에 있는 객체를 가져와서 로컬처럼 보이게 할 수 있다.<br>
 * <br><hr><br>
 * <b>프록시 패턴 구조</b><br>
 * 프록시는 다른 객체에 대한 접근을 제어하는 개체이다. 여기서 다른 객체를 대상(Subject)라고 부른다. 프록시와 대상은 동일한 인터페이스를 가지고 있으며 이를 통해 다른 인터페이스와 완전히 호환되도록 바꿀 수 있다.<br>
 * 1. Subject : Proxy와 RealSubject를 하나로 묶는 인터페이스(다형성)<br>
 * - 대상 객체와 프록시 역할을 동일하게 하는 추상 메서드를 정의한다.<br>
 * - 인터페이스가 있기 때문에 클라이언트는 Proxy 역할과 Real Subject 역할의 차이를 의식할 필요가 없다.<br>
 * 2. RealSubject : 원본 대상 객체<br>
 * 3. Proxy : 대상 객체(RealSubject)를 중계할 대리자 역할<br>
 * - 프록시는 대상 객체를 합성(Composition)한다.<br>
 * - 프록시는 대상 객체와 같은 이름의 메서드를 호출하며 별도의 로직을 수행할 수 있다.(인터페이스 구현 메서드)<br>
 * - 프록시는 흐름 제어만 할 뿐 결과값을 조작하거나 변경시키면 안 된다.<br>
 * 4. Client : Subject 인터페이스를 이용하여 프록시 객체를 생성해 이용<br>
 * - 클라이언트는 프록시를 중간에 두고 프록시를 통해서 RealSubject와 데이터를 주고 받는다.<br>
 * <br><hr><br>
 * <b>패턴 사용 시기</b><br>
 * 접근을 제어하거나 기능을 추가하고 싶은데 기존의 특정 객체를 수정할 수 없는 상황일 때<br>
 * 초기화 지연, 접근 제어, 로깅, 캐싱 등 기존 객체 동작에 수정 없이 가미하고 싶을 때<br>
 * <br><hr><br>
 * <b>패턴 장점</b><br>
 * 개방 폐쇄 원칙(OCP) 준수 : 기존 대상 객체의 코드를 변경하지 않고 새로운 기능을 추가할 수 있다.<br>
 * 단일 책임 원칙(SRP) 준수 : 대상 객체는 자신의 기능에만 집중하고 그 이외 부가 기능을 제공하는 역할을 프록시 객체에 위임하여 다중 책임을 회피할 수 있다.<br>
 * 클라이언트는 객체를 신경쓰지 않고 서비스 객체를 제어하거나 생명 주기를 관리할 수 있다.<br>
 * <br><hr><br>
 * <b>패턴 단점</b><br>
 * 많은 프록시 클래스를 도입해야 하므로 코드의 복잡도가 증가한다.<br>
 * - 여러 클래스에 로깅 기능을 가미시키고 싶다면 동일한 코드를 적용함에도 각각의 클래스에 해당되는 프록시 클래스를 만들어서 적용해야하기 때문에 코드량이 많아지고 중복이 발생한다.<br>
 * - 자바에서는 리플렉션에서 제공하는 동적 프록시(Dynamic Proxy) 기법을 이용해 해결할 수 있다.<br>
 * 프록시 클래스 자체에 들어가는 자원이 많다면 서비스로부터의 응답이 늦어질 수 있다.<br>
 */
@Slf4j
public class ProxyTest {

    /**
     * 프록시 객체 내에서 경로 데이터를 지니고 있다가 사용자가 showImage를 호출하면 그때서야 대상 객체를 로드(lazyload)하여 이미지를 메모리에 적재하고 대상 객체의 showImage() 메서드를 위임 호출함으로써<br>
     * 실제 메서드를 호출하는 시점에 메모리 적재가 이루어지기 때문에 불필요한 자원 낭비가 발생하지 않게 된다.
     */
    @Test
    public void virtualProxyTest() {
        IImage highResolutionImage1 = new ImageProxy("./img/고해상도이미지_1");
        IImage highResolutionImage2 = new ImageProxy("./img/고해상도이미지_2");
        IImage highResolutionImage3 = new ImageProxy("./img/고해상도이미지_3");

        highResolutionImage3.showImage();
    }

    @Test
    public void protectionProxyTest() {
        // 직원별 개인 객체 생성
        Employee CTO = new Employee("Dragon Seok", RESPONSIBILITY.DIRECTOR);
        Employee devManager = new Employee("Cats Chang", RESPONSIBILITY.MANAGER);
        Employee financeManager = new Employee("Dell Choi", RESPONSIBILITY.MANAGER);
        Employee devStaff = new Employee("Dark Kim", RESPONSIBILITY.STAFF);
        Employee financeStaff = new Employee("Pal Yoo", RESPONSIBILITY.STAFF);

        // 직원들을 리스트로 가공
        List<Employee> employees = Arrays.asList(CTO, devManager, financeManager, devStaff, financeStaff);

        // 기존에 등록된 리스트를 수정할 수 없으니, 동적으로 기존의 Employee 객체를 프록시 객체 ProtectedEmployee로 Wrap하는 작업을 실행한다.
        List<IEmployee> protectedEmployees = new ArrayList<>();
        for (Employee e : employees) {
            protectedEmployees.add(new ProtectedEmployee(e));
        }

        /*-----------------------------------------------------------------------------------------*/

        // 나 : 일개 사원 직책
        Employee newbie = new Employee("newbie", RESPONSIBILITY.STAFF);

        System.out.println("\n================================================================");
        System.out.println("시나리오1. 사원이 회사 인원 인사 정보 조회");
        System.out.println("================================================================");
        PrintEmployeeInfo view = new PrintEmployeeInfo(newbie);
        view.printAllInfo(protectedEmployees);

        System.out.println("\n================================================================");
        System.out.println("시나리오2. 과장이 회사 인원 인사 정보 조회");
        System.out.println("================================================================");
        PrintEmployeeInfo view2 = new PrintEmployeeInfo(devManager);
        view2.printAllInfo(protectedEmployees);

        System.out.println("\n================================================================");
        System.out.println("시나리오3. 상무가 회사 인원 인사 정보 조회");
        System.out.println("================================================================");
        PrintEmployeeInfo view3 = new PrintEmployeeInfo(CTO);
        view3.printAllInfo(protectedEmployees);
    }

    /**
     * <b>Dynamic Proxy</b><br>
     * 애플리케이션 실행 도중 Reflection API를 이용하여 동적으로 프록시 인스턴스를 만들어 등록하는 방법<br>
     * 별도 프록시 클래스 정의없이 런타임으로 프록시 객체를 동적으로 생성해 이용할 수 있는 장점이 있다.
     */
    @Test
    public void dynamicProxyTest() {
        Animal tigerProxy = (Animal) Proxy.newProxyInstance(
                Animal.class.getClassLoader(),
                new Class[]{Animal.class},
                (proxy, method, args) -> {
                    Object target = new Tiger();
                    log.info("Before invoke");
                    Object result = method.invoke(target, args);
                    log.info("After invoke");
                    return result;
                }
        );

        tigerProxy.eat();
    }
}
