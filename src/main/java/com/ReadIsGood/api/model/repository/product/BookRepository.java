package com.ReadIsGood.api.model.repository.product;

import com.ReadIsGood.api.model.enity.product.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByIsbn(String isbn);
}
