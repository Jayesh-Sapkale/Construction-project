package com.constuction.controller;

import com.constuction.dto.ApiResponseDto;
import com.constuction.dto.request.create.CreateProjectRequestDto;
import com.constuction.dto.response.CreateProjectResponseDto;
import com.constuction.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    public final ProjectService projectService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createProject(@RequestBody CreateProjectRequestDto createprojectRequestDto) {
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            CreateProjectResponseDto projectResponseDto = projectService.createProject(createprojectRequestDto);
            responseDto.setStatus("SUCCESS");
            responseDto.setData(projectResponseDto);
            responseDto.setMessage("project created successfully with id: " + projectResponseDto.getId());
        } catch (Exception e) {
            responseDto.setStatus("FAILURE");
            responseDto.setData(null);
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
            responseDto.setMessage(projects.size() + " projects details fetched successfully");
            if (projects.isEmpty()){
                responseDto.setData(null);
                responseDto.setMessage("No projects found");
            }
        } catch (Exception e) {
            responseDto.setStatus("FAILURE");
            responseDto.setData(null);
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
            responseDto.setMessage("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok(responseDto);
    }
}
