package com.constuction.controller;

import com.constuction.dto.response.ApiResponseDto;
import com.constuction.dto.request.create.CreateAdminRequestDto;
import com.constuction.dto.response.CreateAdminResponseDto;
import com.constuction.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    public final AdminService adminService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createAdmin(@RequestBody CreateAdminRequestDto createAdminRequestDto) {
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            CreateAdminResponseDto adminResponseDto = adminService.createAdmin(createAdminRequestDto);
            responseDto.setStatus("SUCCESS");
            responseDto.setData(adminResponseDto);
            log.info("Admin created successfully with id: {}", adminResponseDto.getId());
            responseDto.setMessage("Admin created successfully with id: " + adminResponseDto.getId());
        } catch (Exception e) {
            responseDto.setStatus("FAILURE");
            responseDto.setData(null);
            log.error(e.getMessage());
            responseDto.setMessage("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<?> getAllAdmins() {
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            List<CreateAdminResponseDto> admins = adminService.getAllAdmins();
            responseDto.setStatus("SUCCESS");
            responseDto.setData(admins);
            log.info("{} admins details fetched successfully", admins.size());
            responseDto.setMessage(admins.size() + " admins details fetched successfully");
            if (admins.isEmpty()) {
                responseDto.setData(null);
                log.info("No admins found");
                responseDto.setMessage("No admins found");
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
    public ResponseEntity<?> deletedAdmin(@PathVariable Long id) {
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            responseDto = adminService.deleteAdmin(id);
        } catch (Exception e) {
            responseDto.setStatus("FAILURE");
            responseDto.setData(null);
            log.error(e.getMessage());
            responseDto.setMessage("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok(responseDto);
    }
}
