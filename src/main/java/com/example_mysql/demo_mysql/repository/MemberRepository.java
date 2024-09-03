package com.example_mysql.demo_mysql.repository;

import com.example_mysql.demo_mysql.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
