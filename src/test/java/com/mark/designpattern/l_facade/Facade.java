package com.mark.designpattern.l_facade;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class Facade {
    private DBMS dbms = new DBMS();
    private Cache cache = new Cache();

    private static class HOLDER {
        private static final Facade INSTANCE = new Facade();
    }

    public static Facade getInstance() {
        return HOLDER.INSTANCE;
    }

    public void insert() {
        dbms.put("홍길동", new Row("홍길동", "1890-02-14", "honggildong@naver.com"));
        dbms.put("임꺽정", new Row("임꺽정", "1820-11-02", "imgguckjong@naver.com"));
        dbms.put("주몽", new Row("주몽", "710-08-27", "jumong@naver.com"));
    }

    public void run(String name) {
        Row row = cache.get(name);

        // 1. 만약 캐시에 없다면
        if (row == null) {
            // 해당 데이터를 조회하여 row 저장
            row = dbms.query(name);

            if (row != null) {
                // 캐시 저장
                cache.put(row);
            }
        }

        // 2. dbms.query(name) 조회된 값이 있으면
        if (row != null) {
            Message message = new Message(row);

            log.info(message.getMessage());
        } else {
            log.info("{} 데이터베이스에 존재하지 않습니다.", name);
        }
    }
}

/**
 * DBMS에 저장된 데이터를 나타내는 클래스
 */
@Getter
@AllArgsConstructor
class Row {
    private String name;
    private String email;
    private String birthDay;
}

/**
 * 데이터베이스 역할일 하는 클래스
 */
class DBMS {
    private Map<String, Row> db = new HashMap<>();

    public void put(String name, Row row) {
        db.put(name, row);
    }

    /**
     * 데이터베이스에 쿼리를 날려 결과를 받아오는 메서드
     */
    public Row query(String name) {
        try {
            Thread.sleep(500);
        } catch (Exception ignored) {}

        return db.get(name.toLowerCase());
    }
}

/**
 * DBMS에서 조회된 데이터를 임시로 담아두는 캐싱 클래스
 */
class Cache {
    private Map<String, Row> cache = new HashMap<>();

    public void put(Row row) {
        cache.put(row.getName(), row);
    }

    public Row get(String name) {
        return cache.get(name);
    }
}

/**
 * 출력 클래스
 */
@AllArgsConstructor
class Message {
    private Row row;

    public String makeName() {
        return "Name : \"" + row.getName() + "\"";
    }

    public String makeEmail() {
        return "Email : \"" + row.getEmail() + "\"";
    }

    public String makeBirthDay() {
        return "BirthDay : \"" + row.getBirthDay() + "\"";
    }

    public String getMessage() {
        return makeName() + ", " + makeEmail() + ", " + makeBirthDay();
    }
}