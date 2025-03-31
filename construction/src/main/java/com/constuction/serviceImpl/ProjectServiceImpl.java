package com.constuction.serviceImpl;

import com.constuction.dto.ApiResponseDto;
import com.constuction.dto.request.create.CreateProjectRequestDto;
import com.constuction.dto.response.CreateProjectResponseDto;
import com.constuction.entity.Builder;
import com.constuction.entity.Project;
import com.constuction.exceptions.ConstructionException;
import com.constuction.repository.BuilderRepository;
import com.constuction.repository.ProjectRepository;
import com.constuction.service.ProjectService;
import com.constuction.utils.EntityRequestBuilder;
import com.constuction.utils.EntityResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectServiceImpl implements ProjectService {
    public final EntityResponseBuilder entityResponseBuilder;
    public final EntityRequestBuilder entityRequestBuilder;
    public final ProjectRepository projectRepository;
    public final BuilderRepository builderRepository;

    @Override
    public CreateProjectResponseDto createProject(CreateProjectRequestDto projectRequestDto) throws ConstructionException {
        log.info("START --> ProjectServiceImpl.createProject()");

        Project existingProject = projectRepository.findByProjectName(projectRequestDto.getProjectName());

        if (Objects.nonNull(existingProject))
            throw new ConstructionException("project already exist");
        Project savedProject = entityRequestBuilder.convertProjectEntityToDto(projectRequestDto);
        log.info("END --> ProjectServiceImpl.createProject()");
        return entityResponseBuilder.convertProjectEntityToDto(savedProject.getId());
    }

    @Override
    public CreateProjectResponseDto updateProject(CreateProjectRequestDto ProjectRequestDto) {
        return null;
    }

    @Override
    public List<CreateProjectResponseDto> getAllProjects() {
        log.info("START --> ProjectServiceImpl.getAllProjects()");
        List<Project> Projects = projectRepository.findAll();
        log.info("END --> ProjectServiceImpl.getAllProjects()");
        return Projects.stream()
                .map(
                        Project -> {
                            try {
                                return entityResponseBuilder.convertProjectEntityToDto(Project.getId());
                            } catch (ConstructionException e) {
                                throw new RuntimeException(e);
                            }
                        }
                ).toList();
    }

    @Override
    public ApiResponseDto deleteProject(Long id) throws ConstructionException {
        log.info("START --> ProjectServiceImpl.deleteProject()");
        Project savedProject = projectRepository.findById(id).orElseThrow(() -> new ConstructionException("Project not found with id: " + id));
        savedProject.setIsDeleted(Boolean.TRUE);
        Project deletedProject = projectRepository.save(savedProject);
        log.info("END --> ProjectServiceImpl.deleteProject()");
        return new ApiResponseDto("SUCCESS", deletedProject, "Project deleted successfully");
    }
}
