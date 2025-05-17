package com.api.book.bootrestbook.controllers.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.api.book.bootrestbook.entities.Book;

@Component
public class BookService {
    
    private static List<Book> list= new ArrayList<>();
    static{
        list.add(new Book(123,"java","xyz"));
        list.add(new Book(36,"python","pqr"));
        list.add(new Book(56,"cpp","abc"));
    }

    //get all books
    public List<Book> getAllBooks(){
        return list;
    }

    //get single book by id 
    public Book getBookById(int id){

        Book book = null;
        book= list.stream().filter(e->e.getId()==id).findFirst().get();
        return book;
    }

}
