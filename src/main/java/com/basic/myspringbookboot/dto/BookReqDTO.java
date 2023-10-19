package com.basic.myspringbookboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookReqDTO {
    private Long id;

    @NotEmpty(message = "title은 null이 될 수 없습니다.")
    private String title;

    @NotEmpty(message = "author은 null이 될 수 없습니다.")
    private String author;

    @NotBlank(message = "isbn은 null이 될 수 없습니다.")
    private String isbn;

    private String genre;


}
