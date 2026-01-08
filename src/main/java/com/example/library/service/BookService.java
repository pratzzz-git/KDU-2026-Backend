package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    private final BookRepository repository;
    private final RegistryClient registryClient;

    public BookService(BookRepository repository, RegistryClient registryClient) {
        this.repository = repository;
        this.registryClient = registryClient;
    }

    public Book add(Book book) {
        repository.save(book);
        log.info("Book added: {}", book.getId());
        return book;
    }

    public Book update(Long id, Book book) {
        book.setId(id);
        repository.save(book);
        return book;
    }

    public Book get(Long id) {
        try {
            Book book = repository.findById(id).orElseThrow();
            book.setCoverUrl(registryClient.fetchCover(id));
            return book;
        } catch (Exception e) {
            log.error("Search failed", e);
            throw e;
        }
    }
}
