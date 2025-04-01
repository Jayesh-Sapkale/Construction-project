package com.constuction.service;

import com.constuction.dto.request.create.CreateUserRequestDto;
import com.constuction.dto.response.CreateUserResponseDto;
import com.constuction.exceptions.ConstructionException;

public interface UserService {

	public CreateUserResponseDto registerUser(CreateUserRequestDto userRequestDto) throws ConstructionException;
}
