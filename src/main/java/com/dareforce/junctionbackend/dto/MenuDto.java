package com.dareforce.junctionbackend.dto;

import com.dareforce.junctionbackend.domain.Menu;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MenuDto {
    private final Long menuId;

    private final String name;

    private final Long price;

    private final String thumbnail;

    private final List<String> ingredients;

    public static MenuDto from(Menu menu) {
        return MenuDto.builder()
                .menuId(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .thumbnail(menu.getThumbnail())
                .ingredients(menu.getMenuIngreds().stream().map(menuIngred -> menuIngred.getIngredient().getName()).collect(
                        Collectors.toList()))
                .build();
    }
}
