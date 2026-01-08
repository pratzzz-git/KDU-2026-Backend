package com.example.bookshelf.controller;

import com.example.bookshelf.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private Map<Long, Book> bookStore = new HashMap<>();
    private Long idCounter = 1L;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        book.setId(idCounter);
        bookStore.put(idCounter, book);
        idCounter++;
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(new ArrayList<>(bookStore.values()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        if (!bookStore.containsKey(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        book.setId(id);
        bookStore.put(id, book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookStore.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
