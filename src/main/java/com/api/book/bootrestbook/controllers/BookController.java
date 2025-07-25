package com.api.book.bootrestbook.controllers;
import java.util.List;
//import java.util.Optional;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookService;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    //get all books handler
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(){
        // Book book= new Book();
        // book.setId(123);
        // book.setTitle("java");
        // book.setAuthor("xyz");

        List<Book> list= bookService.getAllBooks();
        if(list.size()<=0) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(list);

        //return this.bookService.getAllBooks();
    }

    // //get single book handler
    // @GetMapping("/books/{id}")
    // public Book getBook(@PathVariable("id") int id){
    //     return bookService.getBookById(id);
    // }
    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable("bookId") int bookId) {
        Book book = this.bookService.getBookById(bookId);
        //System.out.println("Fetched book: " + book);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book)) ;
    }

    //new book handler
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        //Book b= null;
        try{
            Book b= this.bookService.addBook(book);
            //System.out.println(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(b);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }

    // @PostMapping("/books")
    // public ResponseEntity<Book> addBook(@RequestBody Book book){
    //     try {
    //         Book savedBook = this.bookService.addBook(book);
    //         return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    //     } catch(Exception e){
    //         e.printStackTrace();
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //     }
    // }


    //delete book handler
    @DeleteMapping("/books/{bookId}")
    public void deleteBook(@PathVariable("bookId") int bookId){
        this.bookService.deleteBook(bookId);
    }

    @PutMapping("/books/{bookId}")
    public Book updateBook(@RequestBody Book book ,@PathVariable("bookId") int bookId){
        this.bookService.updateBook(book,bookId);
        return book;
    }

}
