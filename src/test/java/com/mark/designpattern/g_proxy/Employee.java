package com.mark.designpattern.g_proxy;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

interface IEmployee {
    String getName();
    RESPONSIBILITY getGrade();
    String getInfo(IEmployee viewer);
}

@AllArgsConstructor
class Employee implements IEmployee {
    private String name;
    private RESPONSIBILITY position;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public RESPONSIBILITY getGrade() {
        return position;
    }

    @Override
    public String getInfo(IEmployee viewer) {
        return "Display " + getGrade().name() + " '" + getName() + "' personal information";
    }
}

@Slf4j
@AllArgsConstructor
class PrintEmployeeInfo {
    IEmployee viewer;

    void printAllInfo(List<IEmployee> employees) {
        employees.stream()
                .map(e -> e.getInfo(viewer))
                .forEach(log::info);
    }
}

/**
 * 보호 프록시(Protection Proxy) : 인사 정보가 보호된 구성원(인사 정보 열람 권한 없으면 예외 발생)
 */
@AllArgsConstructor
class ProtectedEmployee implements IEmployee {
    private IEmployee employee;

    @Override
    public String getName() {
        return employee.getName();
    }

    @Override
    public RESPONSIBILITY getGrade() {
        return employee.getGrade();
    }

    @Override
    public String getInfo(IEmployee viewer) {
        RESPONSIBILITY position = this.getGrade();

        switch (viewer.getGrade()) {
            case DIRECTOR:
                return this.employee.getInfo(viewer);
            case MANAGER:
                if (position != RESPONSIBILITY.DIRECTOR) {
                    return this.employee.getInfo(viewer);
                }
            case STAFF:
                if (position != RESPONSIBILITY.DIRECTOR && position != RESPONSIBILITY.MANAGER) {
                    return this.employee.getInfo(viewer);
                }
            default: return "다른 사람의 인사 정보를 조회할 수 없습니다.";
        }
    }
}

enum RESPONSIBILITY {
    STAFF,
    MANAGER,
    DIRECTOR,
    ;
}