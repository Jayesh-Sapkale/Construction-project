package com.constuction.utils;

import com.constuction.dto.request.*;
import com.constuction.entity.*;
import com.constuction.enums.ConstructionType;
import com.constuction.enums.Gender;
import com.constuction.enums.ProjectStatus;
import com.constuction.enums.Role;
import com.constuction.repository.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntityRequestBuilder {
    public final OrderRepository orderRepository;
    public final ProjectRepository projectRepository;
    public final AdminRepository adminRepository;
    public final BuilderRepository builderRepository;
    public final CustomerRepository customerRepository;
    public final BasicDetailsRepository basicDetailsRepository;
    public final CompanyDetailsRepository companyDetailsRepository;
    public final LocationDetailsRepository locationDetailsRepository;


    public LocationDetails convertLocationDetailsToDto(CreateLocationDetailsRequestDto locationDetailsRequestDto) {
        return locationDetailsRepository.save(
                LocationDetails
                        .builder()
                        .address(locationDetailsRequestDto.getAddress())
                        .city(locationDetailsRequestDto.getCity())
                        .State(locationDetailsRequestDto.getState())
                        .pincode(locationDetailsRequestDto.getPincode())
                        .build());

    }

    public BasicDetails convertBasicDetailsToDto(CreateBasicDetailsRequestDto basicDetailsRequestDto) {
        return
                basicDetailsRepository.save(
                        BasicDetails
                                .builder()
                                .dob(basicDetailsRequestDto.getDob())
                                .email(basicDetailsRequestDto.getEmail())
                                .firstName(basicDetailsRequestDto.getFirstName())
                                .lastName(basicDetailsRequestDto.getLastName())
                                .gender(Gender.valueOf(basicDetailsRequestDto.getGender().toUpperCase()))
                                .mobileNumber(basicDetailsRequestDto.getMobileNumber())
                                .role(Role.valueOf(basicDetailsRequestDto.getRole().toUpperCase()))
                                .build());
    }

    public CompanyDetails convertCompanyDetailsToDto(CreateCompanyDetailsRequestDto companyDetailsRequestDto) {

        return
                companyDetailsRepository.save(
                        CompanyDetails
                                .builder()
                                .annualRevenue(companyDetailsRequestDto.getAnnualRevenue())
                                .constructionType(ConstructionType.valueOf(companyDetailsRequestDto.getConstructionType().toUpperCase()))
                                .foundingDate(companyDetailsRequestDto.getFoundingDate())
                                .locationDetails(convertLocationDetailsToDto(companyDetailsRequestDto.getLocationDetails()))
                                .build());
    }

    public Admin convertAdminEntityToDto(CreateAdminRequestDto adminRequestDto) {
        return
                adminRepository.save(
                        Admin
                                .builder()
                                .basicDetails(convertBasicDetailsToDto(adminRequestDto.getBasicDetails()))
                                .locationDetails(convertLocationDetailsToDto(adminRequestDto.getLocationDetails()))
                                .build());
    }

    public com.constuction.entity.Builder convertBuilderEntityToDto(CreateBuilderRequestDto builderRequestDto) {
        return
                builderRepository.save(
                        com.constuction.entity.Builder
                                .builder()
                                .rate(builderRequestDto.getRate())
                                .basicDetails(convertBasicDetailsToDto(builderRequestDto.getBasicDetails()))
                                .companyDetails(convertCompanyDetailsToDto(builderRequestDto.getCompanyDetails()))
                                .isAvailable(builderRequestDto.getIsAvailable())
                                .yearsOfExperience(builderRequestDto.getYearsOfExperience())
                                .locationDetails(convertLocationDetailsToDto(builderRequestDto.getLocationDetails()))
                                .build());
    }

    public Customer convertCustomerEntityToDto(CreateCustomerRequestDto customerRequestDto) {
        return
                customerRepository.save(
                        Customer
                                .builder()
                                .basicDetails(convertBasicDetailsToDto(customerRequestDto.getBasicDetails()))
                                .locationDetails(convertLocationDetailsToDto(customerRequestDto.getLocationDetails()))
                                .build());
    }

    public Order convertOrderEntityToDto(CreateOrderRequestDto orderRequestDto) {
        return
                orderRepository.save(
                        Order
                                .builder()
                                .build());
    }

    public Project convertProjectEntityToDto(CreateProjectRequestDto projectRequestDto) {
        return
                projectRepository.save(
                        Project
                                .builder()
                                .builder(convertBuilderEntityToDto(projectRequestDto.getBuilder()))
                                .area(projectRequestDto.getArea())
                                .constructionType(ConstructionType.valueOf(projectRequestDto.getConstructionType().toUpperCase()))
                                .description(projectRequestDto.getDescription())
                                .projectStatus(ProjectStatus.valueOf(projectRequestDto.getProjectStatus().toUpperCase()))
                                .startDate(projectRequestDto.getStartDate())
                                .endDate(projectRequestDto.getEndDate())
                                .estimatedPrice(projectRequestDto.getEstimatedPrice())
                                .projectName(projectRequestDto.getProjectName())
                                .locationDetails(convertLocationDetailsToDto(projectRequestDto.getLocationDetails()))
                                .build());
    }


}
