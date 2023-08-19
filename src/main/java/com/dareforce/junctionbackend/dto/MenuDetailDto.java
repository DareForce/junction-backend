package com.dareforce.junctionbackend.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MenuDetailDto {
    private final Long menuId;

    private final String name;

    private final String thumbnail;

    private final List<String> allergies;

    private final List<String> additional;
}
