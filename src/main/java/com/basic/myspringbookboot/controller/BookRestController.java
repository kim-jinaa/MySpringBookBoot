package com.basic.myspringbookboot.controller;

import com.basic.myspringbookboot.dto.BookReqDTO;
import com.basic.myspringbookboot.dto.BookResDTO;
import com.basic.myspringbookboot.sevice.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookRestController {
    private final BookService bookService;

    //등록
    @PostMapping
    public BookResDTO saveBook(@RequestBody BookReqDTO bookReqDTO){
        return bookService.saveBook(bookReqDTO);
    }

    //id조회
    @GetMapping("/{id}")
    public BookResDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    //isbn조회
    @GetMapping("/isbn/{isbn}")
    public BookResDTO getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn);
    }


    //전체조회
    @GetMapping
    public List<BookResDTO> getBooks(){
        return bookService.getBook();
    }

    //수정
    @PatchMapping("/{id}")
    public BookResDTO updateBook(@PathVariable Long id, @RequestBody BookReqDTO bookReqDTO){
        return bookService.updateBook(id, bookReqDTO);
    }

    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok(id + "번 Book이 삭제처리 되었습니다.");
    }

}
