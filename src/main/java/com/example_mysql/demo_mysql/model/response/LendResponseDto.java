package com.example_mysql.demo_mysql.model.response;

import lombok.Data;

import java.time.Instant;

@Data
public class LendResponseDto {

    private Instant startOn;
    private Instant dueOn;
    private String bookName;
    private String bookIsbn;
    private String bookAuthor;

    public LendResponseDto(Instant startOn, Instant dueOn, String bookName, String bookIsbn, String bookAuthor) {
        this.startOn = startOn;
        this.dueOn = dueOn;
        this.bookName = bookName;
        this.bookIsbn = bookIsbn;
        this.bookAuthor = bookAuthor;
    }
}
