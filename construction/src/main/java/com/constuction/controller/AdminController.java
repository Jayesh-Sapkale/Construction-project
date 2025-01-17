package com.constuction.controller;

import com.constuction.dto.ApiResponseDto;
import com.constuction.dto.request.CreateAdminRequestDto;
import com.constuction.dto.response.CreateAdminResponseDto;
import com.constuction.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    public final AdminService adminService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createAdmin(@RequestBody CreateAdminRequestDto createAdminRequestDto) {
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            CreateAdminResponseDto adminResponseDto = adminService.createAdmin(createAdminRequestDto);
            responseDto.setStatus("SUCCESS");
            responseDto.setData(adminResponseDto);
            responseDto.setMessage("Admin created successfully with id: " + adminResponseDto.getId());
        } catch (Exception e) {
            responseDto.setStatus("FAILURE");
            responseDto.setData(null);
            responseDto.setMessage("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<?> getAllAdmins(){
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            List<CreateAdminResponseDto> admins = adminService.getAllAdmins();
            responseDto.setStatus("SUCCESS");
            responseDto.setData(admins);
            responseDto.setMessage(admins.size()+" admins details fetched successfully");
        } catch (Exception e) {
            responseDto.setStatus("FAILURE");
            responseDto.setData(null);
            responseDto.setMessage("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok(responseDto);
    }
}
