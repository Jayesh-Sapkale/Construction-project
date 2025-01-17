package com.constuction.service;

import com.constuction.dto.ApiResponseDto;
import com.constuction.dto.request.CreateBuilderRequestDto;
import com.constuction.dto.response.CreateAdminResponseDto;
import com.constuction.dto.response.CreateBuilderResponseDto;

import java.util.List;

public interface BuilderService {

    public CreateBuilderResponseDto createBuilder(CreateBuilderRequestDto BuilderRequestDto);
    public CreateBuilderResponseDto updateBuilder(CreateBuilderRequestDto BuilderRequestDto);
    public List<CreateBuilderResponseDto> getAllBuilders();
}
