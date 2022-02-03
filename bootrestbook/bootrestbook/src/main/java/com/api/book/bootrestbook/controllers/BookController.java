package com.api.book.bootrestbook.controllers;


import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//if you use #RestController then no need to use @Controller & @ResponseBody
@RestController
public class BookController {

    //    @RequestMapping(value = "/books", method = RequestMethod.GET) if use @GetMapping
    @Autowired
    private BookServices bookServices;

    //get all books
    @GetMapping("/books")
    public List<Book> getBooks() {
        return this.bookServices.getAllBooks();
    }

    //get single book by id
    @GetMapping("books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
        Book book = bookServices.getBookById(id);

        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // use build for object creation

        }
        return ResponseEntity.of(Optional.of(book));
    }


    //adding book method
    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        Book b = this.bookServices.addBook(book);
        return b;
    }

}

