package com.dareforce.junctionbackend.service;

import static com.dareforce.junctionbackend.common.ErrorCode.INGRED_NOT_FOUND;

import com.dareforce.junctionbackend.common.error.exception.NotFoundException;
import com.dareforce.junctionbackend.domain.Ingredient;
import com.dareforce.junctionbackend.domain.Menu;
import com.dareforce.junctionbackend.domain.Restaurant;
import com.dareforce.junctionbackend.domain.User;
import com.dareforce.junctionbackend.domain.UserIngred;
import com.dareforce.junctionbackend.dto.MenuDto;
import com.dareforce.junctionbackend.dto.RestaurantDto;
import com.dareforce.junctionbackend.dto.UserRequestDto;
import com.dareforce.junctionbackend.repository.IngredRepository;
import com.dareforce.junctionbackend.repository.MenuRepository;
import com.dareforce.junctionbackend.repository.RestaurantRepository;
import com.dareforce.junctionbackend.repository.UserIngredRepository;
import com.dareforce.junctionbackend.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApiService {

    private final UserRepository userRepository;

    private final IngredRepository ingredRepository;

    private final UserIngredRepository userIngredRepository;

    private final RestaurantRepository restaurantRepository;

    private final MenuRepository menuRepository;

    @Transactional
    public void signUp(UserRequestDto requestDto) {
        User user = User.builder()
                .name(requestDto.getName())
                .age(requestDto.getAge())
                .gender(requestDto.getGender())
                .build();
        User savedUser = userRepository.save(user);
        List<UserIngred> userIngreds = new ArrayList<>();
        for (String allergyName : requestDto.getAllergies()) {
            Ingredient ingredient = ingredRepository.findByName(allergyName)
                    .orElseThrow(() -> new NotFoundException(INGRED_NOT_FOUND));
            userIngreds.add(new UserIngred(savedUser, ingredient));
        }
        for (String additional : requestDto.getAdditional()) {
            Ingredient ingredient = ingredRepository.findByName(additional)
                    .orElseThrow(() -> new NotFoundException(INGRED_NOT_FOUND));
            userIngreds.add(new UserIngred(savedUser, ingredient));
        }
        userIngredRepository.saveAll(userIngreds);
    }

    @Transactional
    public List<RestaurantDto> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream().map(RestaurantDto::from).toList();
    }

    public List<MenuDto> getMenuByRestaurantId(Long restaurantId) {
        List<Menu> menus = menuRepository.findByRestaurantId(restaurantId);
        return menus.stream().map(MenuDto::from).toList();
    }
}
