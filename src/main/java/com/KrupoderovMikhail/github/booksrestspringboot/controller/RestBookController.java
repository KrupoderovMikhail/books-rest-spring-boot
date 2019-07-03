package com.KrupoderovMikhail.github.booksrestspringboot.controller;

import com.KrupoderovMikhail.github.booksrestspringboot.model.Book;
import com.KrupoderovMikhail.github.booksrestspringboot.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/books")
public class RestBookController {

    private final BookRepository repository;

    @Autowired
    public RestBookController(BookRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        return new ResponseEntity<>(repository.save(book), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Book>> getAllBooks() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Book> getBookWithId(@PathVariable Long id) {
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public ResponseEntity<Collection<Book>> findBookWithName(@RequestParam(value = "name") String name) {
        return new ResponseEntity<>(repository.findByName(name), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Book> updateBookFromDb(@PathVariable("id") Long id, @RequestBody Book book) {
        Book currentBook = repository.findOne(id);
        currentBook.setName(book.getName());
        currentBook.setDescription(book.getDescription());
        currentBook.setTags(book.getTags());

        return new ResponseEntity<>(repository.save(currentBook), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllBooks() {
        repository.deleteAll();
    }
}
