package com.dareforce.junctionbackend;

import com.dareforce.junctionbackend.common.ApiResponse;
import com.dareforce.junctionbackend.dto.MenuDetailDto;
import com.dareforce.junctionbackend.dto.MenuDto;
import com.dareforce.junctionbackend.dto.RestaurantDto;
import com.dareforce.junctionbackend.dto.UserRequestDto;
import com.dareforce.junctionbackend.service.ApiService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/restaurant/{restaurantId}/menu")
    public ResponseEntity<ApiResponse<List<MenuDto>>> getMenuByRestaurantId(@RequestParam Long userId, @PathVariable Long restaurantId) {
        ApiResponse response = ApiResponse.builder()
                .message("메뉴 가져오기 성공")
                .status(HttpStatus.OK.value())
                .data(apiService.getMenuByRestaurantId(restaurantId, userId))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/menu/{menuId}")
    public ResponseEntity<ApiResponse<MenuDetailDto>> getMenuDetailById(@RequestParam Long userId, @PathVariable Long menuId) {
        ApiResponse response = ApiResponse.builder()
                .message("메뉴 상세 정보 조회 성공")
                .status(HttpStatus.OK.value())
                .data(apiService.getMenuDetailById(menuId, userId))
                .build();
        return ResponseEntity.ok(response);

    }

}
