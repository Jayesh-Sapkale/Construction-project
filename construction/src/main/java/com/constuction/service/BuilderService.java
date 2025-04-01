package com.constuction.service;

import com.constuction.dto.response.ApiResponseDto;
import com.constuction.dto.request.create.CreateBuilderRequestDto;
import com.constuction.dto.response.CreateBuilderResponseDto;
import com.constuction.exceptions.ConstructionException;

import java.util.List;

public interface BuilderService {

    public CreateBuilderResponseDto createBuilder(CreateBuilderRequestDto BuilderRequestDto) throws ConstructionException;
    public CreateBuilderResponseDto updateBuilder(CreateBuilderRequestDto BuilderRequestDto);
    public List<CreateBuilderResponseDto> getAllBuilders();

    ApiResponseDto deleteBuilder(Long id) throws ConstructionException;
}
