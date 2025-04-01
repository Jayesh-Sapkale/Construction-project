package com.constuction.serviceImpl;

import com.constuction.dto.request.create.CreateUserRequestDto;
import com.constuction.dto.response.CreateUserResponseDto;
import com.constuction.entity.Users;
import com.constuction.exceptions.ConstructionException;
import com.constuction.service.UserService;
import com.constuction.utils.EntityRequestBuilder;
import com.constuction.utils.EntityResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final EntityRequestBuilder entityRequestBuilder;
	private final EntityResponseBuilder entityResponseBuilder;

	@Override
	public CreateUserResponseDto registerUser(CreateUserRequestDto userRequestDto) throws ConstructionException {
		Users savedUser = entityRequestBuilder.convertUserEntityToDto(userRequestDto);
		return entityResponseBuilder.convertUserEntityToDto(savedUser.getId());
	}
}
