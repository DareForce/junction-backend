package com.dareforce.junctionbackend.dto;

import com.dareforce.junctionbackend.domain.Gender;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRequestDto {
    private Gender gender;
    private Long age;
    private String name;
    private List<String> allergies;
    private List<String> additional;
}
