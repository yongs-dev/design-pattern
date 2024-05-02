package com.mark.designpattern.e_factory_method;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
public class Ship {
    String name, color, capacity;

    @Override
    public String toString() {
        return String.format("Ship { name: '%s', color: '%s', logo: '%s' }\n", name, color, capacity);
    }
}

class ContainerShip extends Ship {
    ContainerShip(String name, String color, String capacity) {
        super(name, color, capacity);
    }
}

class OilTankerShip extends Ship {
    OilTankerShip(String name, String color, String capacity) {
        super(name, color, capacity);
    }
}

@Slf4j
abstract class ShipFactory {

    /**
     * 객체 생성 전/후처리 메서드
     */
    final Ship orderShip(String email) {
        validate(email);
        Ship ship = createShip();
        sendEmailTo(email, ship);
        return ship;
    }

    private void validate(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Please enter your email.");
        }
    }

    private void sendEmailTo(String email, Ship ship) {
        log.info("{} 다 만들었다고 {}로 메일을 보냈습니다.", ship, email);
    }

    abstract Ship createShip();
}

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ContainerShipFactory extends ShipFactory {
    private static class HOLDER {
        private static final ContainerShipFactory INSTANCE = new ContainerShipFactory();
    }

    public static ContainerShipFactory getInstance() {
        return HOLDER.INSTANCE;
    }

    @Override
    Ship createShip() {
        return new ContainerShip("ContainerShip","20T", "Green");
    }
}

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class OilTankerShipFactory extends ShipFactory {
    private static class HOLDER {
        private static final OilTankerShipFactory INSTANCE = new OilTankerShipFactory();
    }

    public static OilTankerShipFactory getInstance() {
        return HOLDER.INSTANCE;
    }

    @Override
    Ship createShip() {
        return new OilTankerShip("OilTankerShip","15T", "Blue");
    }
}