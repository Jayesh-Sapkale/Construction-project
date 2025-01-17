package com.constuction.serviceImpl;

import com.constuction.dto.request.CreateAdminRequestDto;
import com.constuction.dto.response.CreateAdminResponseDto;
import com.constuction.entity.Admin;
import com.constuction.service.AdminService;
import com.constuction.utils.EntityResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    public final EntityResponseBuilder entityResponseBuilder;


    @Override
    public CreateAdminResponseDto createAdmin(CreateAdminRequestDto adminRequestDto) {
        return null;
    }

    @Override
    public CreateAdminResponseDto updateAdmin(CreateAdminRequestDto adminRequestDto) {
        return null;
    }

    @Override
    public List<CreateAdminResponseDto> getAllAdmins() {
        return List.of();
    }
}
