package com.constuction.service;

import com.constuction.dto.response.ApiResponseDto;
import com.constuction.dto.request.create.CreateProjectRequestDto;
import com.constuction.dto.response.CreateProjectResponseDto;
import com.constuction.exceptions.ConstructionException;

import java.util.List;

public interface ProjectService {

    public CreateProjectResponseDto createProject(CreateProjectRequestDto ProjectRequestDto) throws ConstructionException;
    public CreateProjectResponseDto updateProject(CreateProjectRequestDto ProjectRequestDto);
    public List<CreateProjectResponseDto> getAllProjects();

    ApiResponseDto deleteProject(Long id) throws ConstructionException;
}
