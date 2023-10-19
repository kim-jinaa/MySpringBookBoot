package com.basic.myspringbookboot.repository;

import com.basic.myspringbookboot.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> findByIsbn(String isbn);
    List<BookEntity> findByTitle(String title);

}
