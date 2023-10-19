package com.basic.myspringbookboot.sevice;

import com.basic.myspringbookboot.dto.BookReqDTO;
import com.basic.myspringbookboot.dto.BookResDTO;
import com.basic.myspringbookboot.entity.BookEntity;
import com.basic.myspringbookboot.exception.BusinessException;
import com.basic.myspringbookboot.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    //등록
    public BookResDTO saveBook(BookReqDTO bookReqDTO){
        BookEntity bookEntity = modelMapper.map(bookReqDTO, BookEntity.class);
        BookEntity saveBook = bookRepository.save(bookEntity);
        return modelMapper.map(saveBook, BookResDTO.class);
    }

    //id 조회
    @Transactional(readOnly = true)
    public BookResDTO getBookById(Long id){
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException(id + " book Not Found", HttpStatus.NOT_FOUND));
        BookResDTO bookResDTO = modelMapper.map(bookEntity, BookResDTO.class);
        return bookResDTO;
    }

    @Transactional(readOnly = true)
    public BookResDTO getBookByIsbn(String isbn){
        BookEntity bookEntity = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BusinessException(isbn + " book Not Found", HttpStatus.NOT_FOUND));
        BookResDTO bookResDTO = modelMapper.map(bookEntity, BookResDTO.class);
        return bookResDTO;
    }

    //전체조회
    @Transactional(readOnly = true)
    public List<BookResDTO> getBook(){
        List<BookEntity> customerList = bookRepository.findAll();
        List<BookResDTO> bookResDTOList = customerList.stream()
                .map(customer -> modelMapper.map(customer, BookResDTO.class))
                .collect(toList());
        return bookResDTOList;
    }

    //수정
    public BookResDTO updateBook(Long id, BookReqDTO bookReqDTO){
        BookEntity existBook = bookRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException(id + " Book Not Found", HttpStatus.NOT_FOUND));
        existBook.setTitle(bookReqDTO.getTitle());
        existBook.setAuthor(bookReqDTO.getAuthor());
        return modelMapper.map(existBook, BookResDTO.class);
    }

    //삭제
    public void deleteBook(Long id){
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException(id + " book Not Found", HttpStatus.NOT_FOUND));
        bookRepository.delete(bookEntity);
    }

    public void updateBookForm(BookReqDTO bookReqDTO){
        BookEntity existBook = bookRepository.findById(bookReqDTO.getId())
                .orElseThrow(() ->
                        new BusinessException(bookReqDTO.getId() + " book Not Found", HttpStatus.NOT_FOUND));
        existBook.setTitle(bookReqDTO.getTitle());
        existBook.setAuthor(bookReqDTO.getAuthor());
        existBook.setGenre(bookReqDTO.getGenre());
    }



}
