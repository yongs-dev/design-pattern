package com.mark.designpattern.i_decorator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 원본 객체와 장식된 객체 모두를 묶는 인터페이스
 */
public interface Weapon {
    void aim_and_fire();
}

/**
 * 장식될 원본 객체
 */
@Slf4j
class BaseWeapon implements Weapon {
    @Override
    public void aim_and_fire() {
        log.info("총알 발사");
    }
}

/**
 * 장식자 추상 클래스
 */
@AllArgsConstructor
abstract class WeaponAccessory implements Weapon {
    private Weapon rifle;

    @Override
    public void aim_and_fire() {
        rifle.aim_and_fire();   // 위임
    }
}

/**
 * 장식자 클래스 (유탄발사기)
 */
@Slf4j
class Generade extends WeaponAccessory {

    public Generade(Weapon rifle) {
        super(rifle);
    }

    @Override
    public void aim_and_fire() {
        super.aim_and_fire();   // 부모 메서드를 호출함으로써 자신을 감싸고 있는 장식자의 메서드 호출
        generade_fire();
    }

    public void generade_fire() {
        log.info("유탄 발사");
    }
}

/**
 * 장식자 클래스 (조준경)
 */
@Slf4j
class Scoped extends WeaponAccessory {
    public Scoped(Weapon rifle) {
        super(rifle);
    }

    @Override
    public void aim_and_fire() {
        aiming();
        super.aim_and_fire();   // 부모 메서드를 호출함으로써 자신을 감싸고 있는 장식자의 메서드 호출
    }

    public void aiming() {
        log.info("조준 중...");
    }
}

/**
 * 장식자 클래스 (개머리판)
 */
@Slf4j
class Buttstock extends WeaponAccessory {
    public Buttstock(Weapon rifle) {
        super(rifle);
    }

    @Override
    public void aim_and_fire() {
        holding();
        super.aim_and_fire();   // 부모 메서드를 호출함으로써 자신을 감싸고 있는 장식자의 메서드 호출
    }

    public void holding() {
        log.info("견착 완료");
    }
}