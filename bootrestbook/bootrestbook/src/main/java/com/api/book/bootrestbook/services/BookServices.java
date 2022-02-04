package com.api.book.bootrestbook.services;

import com.api.book.bootrestbook.dao.BookRepository;
import com.api.book.bootrestbook.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookServices {

    @Autowired
    private BookRepository bookRepository;
    //Create fake services
//    private static List<Book> list = new ArrayList<>();
//
//
//    static {
//        list.add(new Book(12, "Scala Book", "Martin Odersky"));
//        list.add(new Book(13, "Java Book", "James Gosling"));
//        list.add(new Book(14, "Rich Dad Poor Dad", "Robert Kiyoaski"));
//        list.add(new Book(15, "Scala Book", "Martin Odersky"));
//        list.add(new Book(123, "Bhagavad Gita", "Maharishi Veda Vyasa"));
//    }

    //create method

    //get all books

    public List<Book> getAllBooks() {

        List<Book> list = (List<Book>) this.bookRepository.findAll();
        return list;
    }

    // get single book by id
    public Book getBookById(int id) {
        Book book = null;

        //exception handling use try catch
        try {


//            book = list.stream().filter(e -> e.getId() == id).findFirst().get();

            book = this.bookRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;

    }

    // adding book
    public Book addBook(Book b) {
//        list.add(b);
        Book result = bookRepository.save(b);
        return b;
    }

    //delete book method
    public void deleteBook(int bid) {
//        list = list.stream().filter(book -> book.getId() != bid).collect(Collectors.toList());

        bookRepository.deleteById(bid);
    }

    //put book method
    public void updateBook(Book book, int bookId) {
//        list = list.stream().map(b -> {
//            if (b.getId() == bookId) {
//                b.setTitle(book.getTitle());
//                b.setAuthor(book.getAuthor());
//            }
//            return b;
//        }).collect(Collectors.toList());


        book.setId(bookId);
        bookRepository.save(book);
    }
}

