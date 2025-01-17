package com.constuction.service;

import com.constuction.dto.ApiResponseDto;
import com.constuction.dto.request.CreateAdminRequestDto;
import com.constuction.dto.response.CreateAdminResponseDto;

import java.util.List;

public interface AdminService {

    public CreateAdminResponseDto createAdmin(CreateAdminRequestDto adminRequestDto);
    public CreateAdminResponseDto updateAdmin(CreateAdminRequestDto adminRequestDto);
    public List<CreateAdminResponseDto> getAllAdmins();
}
