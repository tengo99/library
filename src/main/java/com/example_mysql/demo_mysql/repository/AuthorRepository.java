package com.example_mysql.demo_mysql.repository;

import com.example_mysql.demo_mysql.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
