package com.constuction.controller;

import com.constuction.dto.ApiResponseDto;
import com.constuction.dto.request.create.CreateBuilderRequestDto;
import com.constuction.dto.response.CreateBuilderResponseDto;
import com.constuction.service.BuilderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/builder")
@RequiredArgsConstructor
public class BuilderController {

    public final BuilderService builderService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createBuilder(@RequestBody CreateBuilderRequestDto createBuilderRequestDto) {
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            CreateBuilderResponseDto BuilderResponseDto = builderService.createBuilder(createBuilderRequestDto);
            responseDto.setStatus("SUCCESS");
            responseDto.setData(BuilderResponseDto);
            responseDto.setMessage("Builder created successfully with id: " + BuilderResponseDto.getId());
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
            List<CreateBuilderResponseDto> Builders = builderService.getAllBuilders();
            responseDto.setStatus("SUCCESS");
            responseDto.setData(Builders);
            responseDto.setMessage(Builders.size() + " Builders details fetched successfully");
            if (Builders.isEmpty()){
                responseDto.setData(null);
                responseDto.setMessage("No Builders found");
            }
        } catch (Exception e) {
            responseDto.setStatus("FAILURE");
            responseDto.setData(null);
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
            responseDto.setMessage("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok(responseDto);
    }
}
