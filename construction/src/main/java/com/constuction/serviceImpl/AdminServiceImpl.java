package com.constuction.serviceImpl;

import com.constuction.dto.request.CreateAdminRequestDto;
import com.constuction.dto.response.CreateAdminResponseDto;
import com.constuction.entity.Admin;
import com.constuction.repository.AdminRepository;
import com.constuction.service.AdminService;
import com.constuction.utils.EntityRequestBuilder;
import com.constuction.utils.EntityResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    public final EntityResponseBuilder entityResponseBuilder;
    public final EntityRequestBuilder entityRequestBuilder;
    public final AdminRepository adminRepository;


    @Override
    public CreateAdminResponseDto createAdmin(CreateAdminRequestDto adminRequestDto) {
        Admin savedAdmin = entityRequestBuilder.convertAdminEntityToDto(adminRequestDto);
        return entityResponseBuilder.convertAdminEntityToDto(savedAdmin.getId());
    }

    @Override
    public CreateAdminResponseDto updateAdmin(CreateAdminRequestDto adminRequestDto) {
        return null;
    }

    @Override
    public List<CreateAdminResponseDto> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream()
                .map(
                        admin -> entityResponseBuilder.convertAdminEntityToDto(admin.getId())
                ).toList();
    }
}
