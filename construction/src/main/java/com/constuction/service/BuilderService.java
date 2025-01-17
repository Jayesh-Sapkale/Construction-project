package com.constuction.service;

import com.constuction.dto.ApiResponseDto;
import com.constuction.dto.request.CreateBuilderRequestDto;

import java.util.List;

public interface BuilderService {

    public ApiResponseDto createBuilder(CreateBuilderRequestDto BuilderRequestDto);
    public ApiResponseDto updateBuilder(CreateBuilderRequestDto BuilderRequestDto);
    public List<ApiResponseDto> getAllBuilders();
}
