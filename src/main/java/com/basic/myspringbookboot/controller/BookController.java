package com.basic.myspringbookboot.controller;

import com.basic.myspringbookboot.dto.BookReqDTO;
import com.basic.myspringbookboot.dto.BookResDTO;
import com.basic.myspringbookboot.sevice.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/bookpage")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/index")
    public ModelAndView index(){
        List<BookResDTO> bookResDTOList = bookService.getBook();
        return new ModelAndView("index","book",bookResDTOList);
    }

    @GetMapping("/signup")
    public String showSignUpForm(BookReqDTO book) {
        return "add-book";
    }

    @PostMapping("/addbook")
    public String addBook(@Valid BookReqDTO book, BindingResult result, Model model) {
        //입력항목 검증 오류가 발생했나요?
        if (result.hasErrors()) {
            return "add-book";
        }
        //등록요청
        bookService.saveBook(book);
        return "redirect:/bookpage/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateFrom(@PathVariable Long id, Model model){
        BookResDTO bookResDTO = bookService.getBookById(id);
        model.addAttribute("book", bookResDTO);
        return "update-book";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") long id, @Valid BookReqDTO book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println(">>>> hasErrors book " + book);
            model.addAttribute("book", book);
            return "update-book";
        }
        bookService.updateBookForm(book);
        return "redirect:/bookpage/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id){
        bookService.deleteBook(id);
        return "redirect:/bookpage/index";
    }


}
