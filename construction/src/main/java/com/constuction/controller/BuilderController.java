package com.constuction.controller;

import com.constuction.dto.ApiResponseDto;
import com.constuction.dto.request.create.CreateBuilderRequestDto;
import com.constuction.dto.response.CreateBuilderResponseDto;
import com.constuction.service.BuilderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/builder")
@RequiredArgsConstructor
@Slf4j
public class BuilderController {

    public final BuilderService builderService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createBuilder(@RequestBody CreateBuilderRequestDto createBuilderRequestDto) {
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            CreateBuilderResponseDto builderResponseDto = builderService.createBuilder(createBuilderRequestDto);
            responseDto.setStatus("SUCCESS");
            responseDto.setData(builderResponseDto);
            log.info("Builder created successfully with id: {}", builderResponseDto.getId());
            responseDto.setMessage("Builder created successfully with id: " + builderResponseDto.getId());
        } catch (Exception e) {
            responseDto.setStatus("FAILURE");
            responseDto.setData(null);
            responseDto.setMessage("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<?> getAllBuilders() {
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            List<CreateBuilderResponseDto> builders = builderService.getAllBuilders();
            responseDto.setStatus("SUCCESS");
            responseDto.setData(builders);
            log.info("{} Builders details fetched successfully", builders.size());
            responseDto.setMessage(builders.size() + " Builders details fetched successfully");
            if (builders.isEmpty()) {
                responseDto.setData(null);
                log.info("No builders found");
                responseDto.setMessage("No Builders found");
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
    public ResponseEntity<?> deletedBuilder(@PathVariable Long id) {
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            responseDto = builderService.deleteBuilder(id);
        } catch (Exception e) {
            responseDto.setStatus("FAILURE");
            responseDto.setData(null);
            log.error(e.getMessage());
            responseDto.setMessage("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok(responseDto);
    }
}
