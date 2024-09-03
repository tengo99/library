package com.example_mysql.demo_mysql.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<Book>();
}
