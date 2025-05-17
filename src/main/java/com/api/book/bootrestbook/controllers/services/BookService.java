package com.api.book.bootrestbook.controllers.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    //adding the book
    public Book addBook(Book b){
        list.add(b);
        return b;
    }

    //delete book
    public void deleteBook(int bid){
        list= list.stream().filter(book->book.getId()!=bid).collect(Collectors.toList());
    }
}
