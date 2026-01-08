package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BookRepository {

    private final Map<Long, Book> store = new ConcurrentHashMap<>();

    public Book save(Book book) {
        store.put(book.getId(), book);
        return book;
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
