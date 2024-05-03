package com.mark.designpattern.k_iterator;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
class Post {
    String title;
    LocalDate date;

    @Override
    public String toString() {
        return title + " / " + date;
    }
}

@Getter
class Board {
    List<Post> posts = new ArrayList<>();

    public void addPost(String title, LocalDate date) {
        this.posts.add(new Post(title, date));
    }

    /**
     * ListPostIterator 이더레이터 객체 반환
     */
    public Iterator<Post> getListPostIterator() {
        return new ListPostIterator(posts);
    }

    /**
     * DatePostIterator 이더레이터 객체 반환
     */
    public Iterator<Post> getDatePostIterator() {
        return new DatePostIterator(posts);
    }
}

/**
 * 저장 순서 이더레이터
 */
class ListPostIterator implements Iterator<Post> {
    private Iterator<Post> iter;

    public ListPostIterator(List<Post> posts) {
        this.iter = posts.iterator();
    }

    @Override
    public boolean hasNext() {
        return this.iter.hasNext(); // 자바 내부 이더레이터에 위임
    }

    @Override
    public Post next() {
        return this.iter.next();    // 자바 내부 이더레이터에 위임
    }
}

class DatePostIterator implements Iterator<Post> {
    private Iterator<Post> iter;

    public DatePostIterator(List<Post> posts) {
        posts.sort(Comparator.comparing(p -> p.date));
        this.iter = posts.iterator();
    }

    @Override
    public boolean hasNext() {
        return this.iter.hasNext(); // 자바 내부 이더레이터에 위임
    }

    @Override
    public Post next() {
        return this.iter.next();    // 자바 내부 이더레이터에 위임
    }
}