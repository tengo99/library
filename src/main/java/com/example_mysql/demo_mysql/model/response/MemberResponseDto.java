package com.example_mysql.demo_mysql.model.response;

import com.example_mysql.demo_mysql.model.Lend;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MemberResponseDto {

    private String firstName;
    private String lastName;
    private List<LendResponseDto> lends = new ArrayList<>();

    public MemberResponseDto(String firstName, String lastName, List<LendResponseDto> lends) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.lends = lends;
    }
}
