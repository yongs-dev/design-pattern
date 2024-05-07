package com.mark.designpattern.o_composite;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

public class FileSystem {}

/**
 * Component
 */
interface Node {
    // 계층 트리 출력
    void print();
    void print(String str);

    // 파일/폴더 용량 얻기
    int getSize();
}

/**
 * Composite
 */
@Slf4j
class Folder implements Node {
    private String name;
    private ArrayList<Node> list;

    public Folder(String name) {
        this.name = name;
        this.list = new ArrayList<>();
    }

    public void add(Node node) {
        list.add(node);
    }

    /**
     * 공백 indent 표형 처리를 위한 print 메서드 오버로딩
     */
    @Override
    public void print() {
        this.print("");
    }

    @Override
    public void print(String str) {
        log.info("{}\uD83D\uDCC2{} ({}KB)", str, name, getSize());

        for (Node node : list) {
            node.print(str + "    ");
        }
    }

    @Override
    public int getSize() {
        int size = 0;
        for (Node node : list) {
            size += node.getSize();
        }
        return size;
    }
}

@Slf4j
@AllArgsConstructor
class File implements Node {
    private String name;
    private int size;

    @Override
    public void print() {
        this.print("");
    }

    @Override
    public void print(String str) {
        log.info("  {}\uD83D\uDCC2{} ({}KB)", str, name, getSize());
    }

    @Override
    public int getSize() {
        return size;
    }
}