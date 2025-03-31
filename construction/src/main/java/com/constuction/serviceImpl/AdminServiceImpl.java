package com.constuction.serviceImpl;

import com.constuction.dto.ApiResponseDto;
import com.constuction.dto.request.create.CreateAdminRequestDto;
import com.constuction.dto.response.CreateAdminResponseDto;
import com.constuction.entity.Admin;
import com.constuction.exceptions.ConstructionException;
import com.constuction.repository.AdminRepository;
import com.constuction.service.AdminService;
import com.constuction.utils.EntityRequestBuilder;
import com.constuction.utils.EntityResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    public final EntityResponseBuilder entityResponseBuilder;
    public final EntityRequestBuilder entityRequestBuilder;
    public final AdminRepository adminRepository;


    @Override
    public CreateAdminResponseDto createAdmin(CreateAdminRequestDto adminRequestDto) throws ConstructionException {
        log.info("START --> AdminServiceImpl.createAdmin()");
        List<Admin> existingAdmins = adminRepository.findByFirstNameAndLastNameAndMobileNumber(adminRequestDto.getBasicDetails().getFirstName()
                , adminRequestDto.getBasicDetails().getLastName()
                , adminRequestDto.getBasicDetails().getMobileNumber());

        if (!existingAdmins.isEmpty())
            throw new ConstructionException("Admin already exist");

        Admin savedAdmin = entityRequestBuilder.convertAdminEntityToDto(adminRequestDto);
        log.info("END --> AdminServiceImpl.createAdmin()");
        return entityResponseBuilder.convertAdminEntityToDto(savedAdmin.getId());
    }

    @Override
    public CreateAdminResponseDto updateAdmin(CreateAdminRequestDto adminRequestDto) {
        return null;
    }

    @Override
    public ApiResponseDto deleteAdmin(Long id) throws ConstructionException {
        log.info("START --> AdminServiceImpl.deleteAdmin()");
        Admin savedAdmin = adminRepository.findById(id).orElseThrow(() -> new ConstructionException("Admin not found with id: " + id));
        savedAdmin.setIsDeleted(Boolean.TRUE);
        Admin deletedAdmin = adminRepository.save(savedAdmin);
        log.info("END --> AdminServiceImpl.deleteAdmin()");
        return new ApiResponseDto("SUCCESS", deletedAdmin, "Admin deleted successfully");
    }

    @Override
    public List<CreateAdminResponseDto> getAllAdmins() {
        log.info("START --> AdminServiceImpl.getAllAdmins()");
        List<Admin> admins = adminRepository.findAll();
        log.info("END --> AdminServiceImpl.getAllAdmins()");
        return admins.stream()
                .filter(admin -> Objects.nonNull(admin.getIsDeleted()))
                .filter(admin -> admin.getIsDeleted().equals(Boolean.FALSE))
                .map(
                        admin -> entityResponseBuilder.convertAdminEntityToDto(admin.getId())
                ).toList();
    }
}
