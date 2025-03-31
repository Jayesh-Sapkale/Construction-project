package com.constuction.controller;

import com.constuction.dto.ApiResponseDto;
import com.constuction.dto.request.create.CreateProjectRequestDto;
import com.constuction.dto.response.CreateProjectResponseDto;
import com.constuction.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
@Slf4j
public class ProjectController {

    public final ProjectService projectService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createProject(@RequestBody CreateProjectRequestDto createprojectRequestDto) {
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            CreateProjectResponseDto projectResponseDto = projectService.createProject(createprojectRequestDto);
            responseDto.setStatus("SUCCESS");
            responseDto.setData(projectResponseDto);
            log.info("project created successfully with id: {}", projectResponseDto.getId());
            responseDto.setMessage("project created successfully with id: " + projectResponseDto.getId());
        } catch (Exception e) {
            responseDto.setStatus("FAILURE");
            responseDto.setData(null);
            log.error(e.getMessage());
            responseDto.setMessage("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<?> getAllProjects() {
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            List<CreateProjectResponseDto> projects = projectService.getAllProjects();
            responseDto.setStatus("SUCCESS");
            responseDto.setData(projects);
            log.info("{} projects details fetched successfully", projects.size());
            responseDto.setMessage(projects.size() + " projects details fetched successfully");
            if (projects.isEmpty()) {
                responseDto.setData(null);
                log.info("No projects found");
                responseDto.setMessage("No projects found");
            }
        } catch (Exception e) {
            responseDto.setStatus("FAILURE");
            responseDto.setData(null);
            log.error(e.getMessage());
            responseDto.setMessage("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deletedProject(@PathVariable Long id) {
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            responseDto = projectService.deleteProject(id);
        } catch (Exception e) {
            responseDto.setStatus("FAILURE");
            responseDto.setData(null);
            log.error(e.getMessage());
            responseDto.setMessage("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok(responseDto);
    }
}
