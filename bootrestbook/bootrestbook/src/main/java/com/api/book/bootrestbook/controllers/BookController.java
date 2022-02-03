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

    //get all books handler
    @GetMapping("/books")
//    public List<Book> getBooks() {
//        return this.bookServices.getAllBooks();
//    }

    //for change status
    public ResponseEntity<List<Book>> getBooks() {
        List list = bookServices.getAllBooks();

        //if there is no data then show status 404 Not_found
        if (list.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // build create a new object
        } else {
            return ResponseEntity.of(Optional.of(list));
        }
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
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book b = null;
        try {
            b = this.bookServices.addBook(book);
            return ResponseEntity.of(Optional.of(b));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //delete book handler
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId) {
        try {

            this.bookServices.deleteBook(bookId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //put book handler update
    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable
            ("bookId") int bookId) {
        try {
            this.bookServices.updateBook(book, bookId);
            return ResponseEntity.ok().body(book);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

