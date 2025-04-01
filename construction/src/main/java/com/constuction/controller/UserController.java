package com.constuction.controller;

import com.constuction.dto.request.create.CreateUserRequestDto;
import com.constuction.dto.response.ApiResponseDto;
import com.constuction.dto.response.CreateUserResponseDto;
import com.constuction.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

	private final UserService userService;

	@GetMapping("/csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}

	@PostMapping("/register")
	public ApiResponseDto register(@RequestBody CreateUserRequestDto user) {
		ApiResponseDto responseDto = new ApiResponseDto();

		try {
			CreateUserResponseDto userResponseDto = userService.registerUser(user);
			responseDto.setStatus("SUCCESS");
			responseDto.setData(userResponseDto);
			responseDto.setMessage("User registered successfully");
			log.info("User registered successfully with id: {}", userResponseDto.getId());
		} catch (Exception e) {
			responseDto.setStatus("FAILURE");
			responseDto.setData(null);
			responseDto.setMessage("User registration failed: " + e.getMessage());
			log.error("User registration failed: {}", e.getMessage());
		}
		return responseDto;
	}

	@PostMapping("/login")
	public String login(@RequestBody CreateUserRequestDto user) {

		return userService.verify(user);
	}

}
