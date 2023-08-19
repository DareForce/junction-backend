package com.dareforce.junctionbackend;

import com.dareforce.junctionbackend.common.ApiResponse;
import com.dareforce.junctionbackend.dto.RestaurantDto;
import com.dareforce.junctionbackend.dto.UserRequestDto;
import com.dareforce.junctionbackend.service.ApiService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@SuppressWarnings({"rawtypes", "unchecked"})
public class ApiController {

    private final ApiService apiService;

    @PostMapping("/user/signUp")
    public ResponseEntity<ApiResponse> signUp(@RequestBody UserRequestDto requestDto) {
        apiService.signUp(requestDto);
        ApiResponse response = ApiResponse.builder()
                .message("유저 회원가입 성공")
                .status(HttpStatus.OK.value()).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/restaurant")
    public ResponseEntity<ApiResponse<List<RestaurantDto>>> getRestaurant() {
        ApiResponse response = ApiResponse.builder()
                .message("레스토랑 가져오기 성공")
                .status(HttpStatus.OK.value())
                .data(apiService.getRestaurants())
                .build();
        return ResponseEntity.ok(response);
    }
}
