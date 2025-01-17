package com.constuction.service;

import com.constuction.dto.ApiResponseDto;
import com.constuction.dto.request.CreateAdminRequestDto;

import java.util.List;

public interface AdminService {

    public ApiResponseDto createAdmin(CreateAdminRequestDto adminRequestDto);
    public ApiResponseDto updateAdmin(CreateAdminRequestDto adminRequestDto);
    public List<ApiResponseDto> getAllAdmins();
}
