package com.constuction.service;

import com.constuction.dto.response.ApiResponseDto;
import com.constuction.dto.request.create.CreateAdminRequestDto;
import com.constuction.dto.response.CreateAdminResponseDto;
import com.constuction.exceptions.ConstructionException;

import java.util.List;

public interface AdminService {

    public CreateAdminResponseDto createAdmin(CreateAdminRequestDto adminRequestDto) throws ConstructionException;
    public CreateAdminResponseDto updateAdmin(CreateAdminRequestDto adminRequestDto);
    public ApiResponseDto deleteAdmin(Long id) throws ConstructionException;
    public List<CreateAdminResponseDto> getAllAdmins();
}
