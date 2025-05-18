package com.api.book.bootrestbook.controllers.services;

//import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.book.bootrestbook.dao.BookRepository;
import com.api.book.bootrestbook.entities.Book;

@Component
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;

    //private static List<Book> list= new ArrayList<>();
    // static{
    //     list.add(new Book(123,"java","xyz"));
    //     list.add(new Book(36,"python","pqr"));
    //     list.add(new Book(56,"cpp","abc"));
    // }

    //get all books
    public List<Book> getAllBooks(){
        //return list;
        List<Book> list= (List<Book>)this.bookRepository.findAll();
        return list; 
    }

    //get single book by id 
    public Book getBookById(int id){

        //Book book = null;
        //book= list.stream().filter(e->e.getId()==id).findFirst().get();
        //this.bookRepository.findById(id);
        //return book;
        //return this.bookRepository.findById(id).orElse(null);
        Optional<Book> optionalBook = this.bookRepository.findById(id);
        if(optionalBook.isPresent()){
            return optionalBook.get();
        } else {
            return null;
        }
    }

    //adding the book
    public Book addBook(Book b){
        //list.add(b);
        Book res= bookRepository.save(b);
        //return b;
        return res;
    }

    //delete book
    public void deleteBook(int bid){
        //list= list.stream().filter(book->book.getId()!=bid).collect(Collectors.toList());
        bookRepository.deleteById(bid);
    }

    //update the book
    public void updateBook(Book book, int bookId){
        // list= list.stream().map(b->{
        //     if(b.getId()==bookId){
        //         b.setTitle(book.getTitle());
        //         b.setAuthor(book.getAuthor());
        //     }
        //     return b;
        // }).collect(Collectors.toList());

        book.setId(bookId);

        bookRepository.save(book);
    }
}
