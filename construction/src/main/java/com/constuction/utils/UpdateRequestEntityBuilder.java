package com.constuction.utils;

import com.constuction.dto.request.update.*;
import com.constuction.entity.*;
import com.constuction.enums.ConstructionType;
import com.constuction.enums.Gender;
import com.constuction.enums.ProjectStatus;
import com.constuction.enums.Role;
import com.constuction.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UpdateRequestEntityBuilder {
    public final OrderRepository orderRepository;
    public final ProjectRepository projectRepository;
    public final AdminRepository adminRepository;
    public final BuilderRepository builderRepository;
    public final CustomerRepository customerRepository;
    public final BasicDetailsRepository basicDetailsRepository;
    public final CompanyDetailsRepository companyDetailsRepository;
    public final LocationDetailsRepository locationDetailsRepository;


    public LocationDetails convertLocationDetailsToDto(Long id, UpdateLocationDetailsRequestDto locationDetailsRequestDto) {
        LocationDetails existingLocationDetails = locationDetailsRepository.findById(id).orElseThrow();
        return locationDetailsRepository.save(
                existingLocationDetails
                        .toBuilder()
                        .address(locationDetailsRequestDto.getAddress())
                        .city(locationDetailsRequestDto.getCity())
                        .state(locationDetailsRequestDto.getState())
                        .pincode(locationDetailsRequestDto.getPincode())
                        .build());

    }

    public BasicDetails convertBasicDetailsToDto(Long id, UpdateBasicDetailsRequestDto basicDetailsRequestDto) {
        BasicDetails existingBasicDetails = basicDetailsRepository.findById(id).orElseThrow();
        return
                basicDetailsRepository.save(
                        existingBasicDetails
                                .toBuilder()
                                .dob(basicDetailsRequestDto.getDob())
                                .email(basicDetailsRequestDto.getEmail())
                                .firstName(basicDetailsRequestDto.getFirstName())
                                .lastName(basicDetailsRequestDto.getLastName())
                                .gender(Gender.valueOf(basicDetailsRequestDto.getGender().toUpperCase()))
                                .mobileNumber(basicDetailsRequestDto.getMobileNumber())
                                .role(Role.valueOf(basicDetailsRequestDto.getRole().toUpperCase()))
                                .build());
    }

    public CompanyDetails convertCompanyDetailsToDto(Long id, UpdateCompanyDetailsRequestDto companyDetailsRequestDto) {
        CompanyDetails existingCompanyDetails = companyDetailsRepository.findById(id).orElseThrow();
        return
                companyDetailsRepository.save(
                        existingCompanyDetails
                                .toBuilder()
                                .annualRevenue(companyDetailsRequestDto.getAnnualRevenue())
                                .constructionType(ConstructionType.valueOf(companyDetailsRequestDto.getConstructionType().toUpperCase()))
                                .foundingDate(companyDetailsRequestDto.getFoundingDate())
                                .locationDetails(convertLocationDetailsToDto(companyDetailsRequestDto.getLocationDetails().getId(), companyDetailsRequestDto.getLocationDetails()))
                                .build());
    }

    public Admin convertAdminEntityToDto(Long id, UpdateAdminRequestDto adminRequestDto) {
        Admin existingAdmin = adminRepository.findById(id).orElseThrow();

        if (Objects.nonNull(existingAdmin)) {

            return
                    adminRepository.save(
                            existingAdmin
                                    .toBuilder()
                                    .basicDetails(convertBasicDetailsToDto(adminRequestDto.getBasicDetails().getId(), adminRequestDto.getBasicDetails()))
                                    .locationDetails(convertLocationDetailsToDto(adminRequestDto.getLocationDetails().getId(), adminRequestDto.getLocationDetails()))
                                    .build());
        }
        return null;
    }

    public Builder convertBuilderEntityToDto(Long id, UpdateBuilderRequestDto builderRequestDto) {
        Builder existingBuilder = builderRepository.findById(id).orElseThrow();
        return
                builderRepository.save(
                        existingBuilder
                                .toBuilder()
                                .rate(builderRequestDto.getRate())
                                .basicDetails(convertBasicDetailsToDto(builderRequestDto.getBasicDetails().getId(), builderRequestDto.getBasicDetails()))
                                .companyDetails(convertCompanyDetailsToDto(builderRequestDto.getCompanyDetails().getId(), builderRequestDto.getCompanyDetails()))
                                .isAvailable(builderRequestDto.getIsAvailable())
                                .yearsOfExperience(builderRequestDto.getYearsOfExperience())
                                .locationDetails(convertLocationDetailsToDto(builderRequestDto.getLocationDetails().getId(), builderRequestDto.getLocationDetails()))
                                .build());
    }

    public Customer convertCustomerEntityToDto(Long id, UpdateCustomerRequestDto customerRequestDto) {
        Customer existingCustomer = customerRepository.findById(id).orElseThrow();
        return
                customerRepository.save(
                        existingCustomer
                                .toBuilder()
                                .basicDetails(convertBasicDetailsToDto(customerRequestDto.getBasicDetails().getId(), customerRequestDto.getBasicDetails()))
                                .locationDetails(convertLocationDetailsToDto(customerRequestDto.getLocationDetails().getId(), customerRequestDto.getLocationDetails()))
                                .build());
    }

    public Order convertOrderEntityToDto(Long id, UpdateOrderRequestDto orderRequestDto) {
        Order existingOrder = orderRepository.findById(id).orElseThrow();
        return
                orderRepository.save(
                        existingOrder
                                .toBuilder()
                                .build());
    }

    public Project convertProjectEntityToDto(Long id, UpdateProjectRequestDto projectRequestDto) {
        Project existingProject = projectRepository.findById(id).orElseThrow();
        return
                projectRepository.save(
                        existingProject
                                .toBuilder()
                                .builder(convertBuilderEntityToDto(projectRequestDto.getBuilder().getId(), projectRequestDto.getBuilder()))
                                .area(projectRequestDto.getArea())
                                .constructionType(ConstructionType.valueOf(projectRequestDto.getConstructionType().toUpperCase()))
                                .description(projectRequestDto.getDescription())
                                .projectStatus(ProjectStatus.valueOf(projectRequestDto.getProjectStatus().toUpperCase()))
                                .startDate(projectRequestDto.getStartDate())
                                .endDate(projectRequestDto.getEndDate())
                                .estimatedPrice(projectRequestDto.getEstimatedPrice())
                                .projectName(projectRequestDto.getProjectName())
                                .locationDetails(convertLocationDetailsToDto(projectRequestDto.getLocationDetails().getId(), projectRequestDto.getLocationDetails()))
                                .build());
    }


}
