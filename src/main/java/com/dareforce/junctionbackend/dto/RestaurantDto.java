package com.dareforce.junctionbackend.dto;

import com.dareforce.junctionbackend.domain.Menu;
import com.dareforce.junctionbackend.domain.Restaurant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class RestaurantDto {

    private final Long restaurantId;

    private final String name;

    private final String category;

    private final String thumbnail;

    private final List<String> menus;

    public static RestaurantDto from(Restaurant restaurant) {
        return RestaurantDto.builder()
                .restaurantId(restaurant.getId())
                .name(restaurant.getName())
                .category(restaurant.getCategory())
                .thumbnail(restaurant.getThumbnail())
                .menus(restaurant.getMenus().stream().map(Menu::getName).collect(Collectors.toList()))
                .build();
    }
}
