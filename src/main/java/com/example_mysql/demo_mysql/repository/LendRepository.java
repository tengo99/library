package com.example_mysql.demo_mysql.repository;

import com.example_mysql.demo_mysql.model.Book;
import com.example_mysql.demo_mysql.model.Lend;
import com.example_mysql.demo_mysql.model.LendStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LendRepository extends JpaRepository<Lend, Long> {
    Optional<Lend> findByBookAndStatus(Book book, LendStatus status);
}
