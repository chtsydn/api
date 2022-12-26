package com.ReadIsGood.api.controller.product;

import com.ReadIsGood.api.model.enity.product.Book;
import com.ReadIsGood.api.service.product.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/addNewBook")
    public ResponseEntity<?> addNewBook(@RequestBody Book book){
        try {
            return ResponseEntity.ok().body(bookService.addNewBook(book));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
