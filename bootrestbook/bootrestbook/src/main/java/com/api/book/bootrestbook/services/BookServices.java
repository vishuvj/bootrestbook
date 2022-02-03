package com.api.book.bootrestbook.services;

import com.api.book.bootrestbook.entities.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookServices {
    //Create fake services
    private static List<Book> list = new ArrayList<>();


    static {
        list.add(new Book(12, "Scala Book", "Martin Odersky"));
        list.add(new Book(13, "Java Book", "James Gosling"));
        list.add(new Book(14, "Rich Dad Poor Dad", "Robert Kiyoaski"));
        list.add(new Book(15, "Scala Book", "Martin Odersky"));
        list.add(new Book(16, "Java Book", "James Gosling"));
        list.add(new Book(17, "Rich Dad Poor Dad", "Robert Kiyoaski"));
    }

    //create method

    //get all books

    public List<Book> getAllBooks() {
        return list;
    }

    // get single book by id
    public Book getBookById(int id) {
        Book book = null;

        //exception handling use try catch
        try {


            book = list.stream().filter(e -> e.getId() == id).findFirst().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;

    }

    // adding book
    public Book addBook(Book b) {
        list.add(b);
        return b;

    }

}
