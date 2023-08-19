package com.dareforce.junctionbackend.service;

import static com.dareforce.junctionbackend.common.ErrorCode.INGRED_NOT_FOUND;

import com.dareforce.junctionbackend.common.error.exception.NotFoundException;
import com.dareforce.junctionbackend.domain.Ingredient;
import com.dareforce.junctionbackend.domain.User;
import com.dareforce.junctionbackend.domain.UserIngred;
import com.dareforce.junctionbackend.dto.UserRequestDto;
import com.dareforce.junctionbackend.repository.IngredRepository;
import com.dareforce.junctionbackend.repository.UserIngredRepository;
import com.dareforce.junctionbackend.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final IngredRepository ingredRepository;

    private final UserIngredRepository userIngredRepository;

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

}
