package com.ReadIsGood.api.model.enity.product;

import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book extends Product{
    private String author;
    private String isbn;
    private BookCategory bookCategory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return getIsbn() != null && Objects.equals(getIsbn(), book.getIsbn());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
