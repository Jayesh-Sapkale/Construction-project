package com.constuction.service;

import com.constuction.dto.ApiResponseDto;
import com.constuction.dto.request.CreateProjectRequestDto;

import java.util.List;

public interface ProjectService {

    public ApiResponseDto createProject(CreateProjectRequestDto ProjectRequestDto);
    public ApiResponseDto updateProject(CreateProjectRequestDto ProjectRequestDto);
    public List<ApiResponseDto> getAllProjects();
}
