package com.example_mysql.demo_mysql.model.response;

import lombok.Data;

@Data
public class BookResponseDto {

    private String name;
    private String isbn;
    private String author;

    public BookResponseDto(String isbn, String name, String author) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
    }
}
