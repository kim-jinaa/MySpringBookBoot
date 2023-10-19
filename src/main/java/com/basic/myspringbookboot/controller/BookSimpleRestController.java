package com.basic.myspringbookboot.controller;

import com.basic.myspringbookboot.entity.BookEntity;
import com.basic.myspringbookboot.exception.BusinessException;
import com.basic.myspringbookboot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookSimpleRestController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public BookEntity create(@RequestBody BookEntity bookEntity){
        return bookRepository.save(bookEntity);
    }

    @RequestMapping(produces = {"application/json"})
    public List<BookEntity> getBooks(){
        return bookRepository.findAll();
    }

    @RequestMapping(value = "/{id}")
    public BookEntity getBook(@PathVariable Long id){
        return bookRepository.findById(id).orElseThrow(()->new BusinessException("Book Not Found", HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/isbn/{isbn}", produces = {"application/json"})
    public BookEntity getBookByIsbn(@PathVariable String isbn){
        return bookRepository.findByIsbn(isbn).orElseThrow(()->new BusinessException("Book Not Found",HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public BookEntity updateBook(@PathVariable Long id, @RequestBody BookEntity bookDetail) {
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException("book Not Found", HttpStatus.NOT_FOUND));
        // 수정하려는 값을 저장
        bookEntity.setTitle(bookDetail.getTitle());
        bookEntity.setAuthor(bookDetail.getAuthor());
        bookEntity.setIsbn(bookDetail.getIsbn());
        bookEntity.setGenre(bookDetail.getGenre());
        BookEntity updateBook = bookRepository.save(bookEntity);
        return updateBook;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        BookEntity bookEntity = bookRepository.findById(id).orElseThrow(()->new BusinessException("Book Not Found",HttpStatus.NOT_FOUND));
        bookRepository.delete(bookEntity);
        return ResponseEntity.ok("delete book success");
    }

}
