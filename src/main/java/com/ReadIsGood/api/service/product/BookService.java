package com.ReadIsGood.api.service.product;

import com.ReadIsGood.api.model.enity.product.Book;
import com.ReadIsGood.api.model.repository.product.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addNewBook(Book book){
        Optional<Book> existingBook = bookRepository.findBookByIsbn(book.getIsbn());
        if (existingBook.isPresent()){
            throw new IllegalArgumentException("Existing book");
        }
        return bookRepository.save(book);
    }
}
