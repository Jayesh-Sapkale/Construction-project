package com.constuction.serviceImpl;

import com.constuction.dto.request.CreateProjectRequestDto;
import com.constuction.dto.response.CreateProjectResponseDto;
import com.constuction.entity.Project;
import com.constuction.repository.ProjectRepository;
import com.constuction.service.ProjectService;
import com.constuction.utils.EntityRequestBuilder;
import com.constuction.utils.EntityResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    public final EntityResponseBuilder entityResponseBuilder;
    public final EntityRequestBuilder entityRequestBuilder;
    public final ProjectRepository ProjectRepository;

    @Override
    public CreateProjectResponseDto createProject(CreateProjectRequestDto ProjectRequestDto) {
        Project savedProject = entityRequestBuilder.convertProjectEntityToDto(ProjectRequestDto);
        return entityResponseBuilder.convertProjectEntityToDto(savedProject.getId());
    }

    @Override
    public CreateProjectResponseDto updateProject(CreateProjectRequestDto ProjectRequestDto) {
        return null;
    }

    @Override
    public List<CreateProjectResponseDto> getAllProjects() {
        List<Project> Projects = ProjectRepository.findAll();
        return Projects.stream()
                .map(
                        Project -> entityResponseBuilder.convertProjectEntityToDto(Project.getId())
                ).toList();
    }
}
