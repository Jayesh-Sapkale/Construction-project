package com.constuction.service;

import com.constuction.dto.request.CreateProjectRequestDto;
import com.constuction.dto.response.CreateProjectResponseDto;

import java.util.List;

public interface ProjectService {

    public CreateProjectResponseDto createProject(CreateProjectRequestDto ProjectRequestDto);
    public CreateProjectResponseDto updateProject(CreateProjectRequestDto ProjectRequestDto);
    public List<CreateProjectResponseDto> getAllProjects();
}
