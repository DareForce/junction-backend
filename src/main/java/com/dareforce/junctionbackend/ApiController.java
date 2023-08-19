package com.dareforce.junctionbackend;

import com.dareforce.junctionbackend.common.ApiResponse;
import com.dareforce.junctionbackend.dto.UserRequestDto;
import com.dareforce.junctionbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@SuppressWarnings({"rawtypes", "unchecked"})
public class ApiController {

    private final UserService userService;

    @PostMapping("/user/signUp")
    public ResponseEntity<ApiResponse> signUp(@RequestBody UserRequestDto requestDto) {
        System.out.println(requestDto.getName());
        userService.signUp(requestDto);
        ApiResponse response = ApiResponse.builder()
                .message("유저 회원가입 성공")
                .status(HttpStatus.OK.value()).build();
        return ResponseEntity.ok(response);
    }
}
