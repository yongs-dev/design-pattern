package com.mark.designpattern.j_observer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Weather {}

interface IWeatherSubject {
    void registerObserver(IWeatherObserver o);
    void removeObserver(IWeatherObserver o);
    void notifyObservers();
}

class WeatherAPI implements IWeatherSubject {
    float temp;
    float humidity;
    float pressure;

    // 구독자들을 담아 관리하는 리스트
    List<IWeatherObserver> subscribers = new ArrayList<>();

    public void measurementsChanged() {
        // 현재의 온도, 습도, 기압 데이터를 랜덤 값으로 얻는 것으로 비유
        temp = ThreadLocalRandom.current().nextFloat() * 100;
        humidity = ThreadLocalRandom.current().nextFloat() * 100;
        pressure = ThreadLocalRandom.current().nextFloat() * 100;

        // 값이 변화하면 바로 옵저버에게 발행
        notifyObservers();
    }

    @Override
    public void registerObserver(IWeatherObserver o) {
        subscribers.add(o);
    }

    @Override
    public void removeObserver(IWeatherObserver o) {
        subscribers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (IWeatherObserver o : subscribers) {
            o.display(this);
        }
    }
}

interface IWeatherObserver {
    void display(WeatherAPI api);
}

@Slf4j
@AllArgsConstructor
class KoreanUser implements IWeatherObserver {
    String name;

    @Override
    public void display(WeatherAPI api) {
        log.info("{} 님이 현재 날씨 상태를 조회함. {}°C, {}g/m3, {}hPa", name, api.temp, api.humidity, api.pressure);
    }
}
