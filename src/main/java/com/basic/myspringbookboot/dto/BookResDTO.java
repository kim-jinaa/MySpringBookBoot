package com.basic.myspringbookboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResDTO {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private LocalDateTime createdAt;
}
