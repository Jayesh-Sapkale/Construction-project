package com.constuction.utils;

import com.constuction.dto.request.*;
import com.constuction.entity.*;
import com.constuction.enums.ConstructionType;
import com.constuction.enums.Gender;
import com.constuction.enums.ProjectStatus;
import com.constuction.enums.Role;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder(toBuilder = true)
public class EntityRequestBuilder {


    public LocationDetails convertLocationDetailsToDto(CreateLocationDetailsRequestDto locationDetailsRequestDto) {
        return LocationDetails
                .builder()
                .address(locationDetailsRequestDto.getAddress())
                .city(locationDetailsRequestDto.getCity())
                .State(locationDetailsRequestDto.getState())
                .pincode(locationDetailsRequestDto.getPincode())
                .build();

    }

    public BasicDetails convertBasicDetailsToDto(CreateBasicDetailsRequestDto basicDetailsResponseDto) {
        return BasicDetails
                .builder()
                .dob(basicDetailsResponseDto.getDob())
                .email(basicDetailsResponseDto.getEmail())
                .firstName(basicDetailsResponseDto.getFirstName())
                .lastName(basicDetailsResponseDto.getLastName())
                .gender(Gender.valueOf(basicDetailsResponseDto.getGender().toUpperCase()))
                .mobileNumber(basicDetailsResponseDto.getMobileNumber())
                .role(Role.valueOf(basicDetailsResponseDto.getRole().toUpperCase()))
                .build();
    }

    public CompanyDetails convertCompanyDetailsToDto(CreateCompanyDetailsRequestDto companyDetailsRequestDto) {

        return CompanyDetails
                .builder()
                .annualRevenue(companyDetailsRequestDto.getAnnualRevenue())
                .constructionType(ConstructionType.valueOf(companyDetailsRequestDto.getConstructionType().toUpperCase()))
                .foundingDate(companyDetailsRequestDto.getFoundingDate())
                .locationDetails(convertLocationDetailsToDto(companyDetailsRequestDto.getLocationDetails()))
                .build();
    }

    public Admin convertAdminEntityToDto(CreateAdminRequestDto adminRequestDto) {
        return Admin
                .builder()
                .basicDetails(convertBasicDetailsToDto(adminRequestDto.getBasicDetails()))
                .build();
    }

    public com.constuction.entity.Builder convertBuilderEntityToDto(CreateBuilderRequestDto builderResponseDto) {
        return com.constuction.entity.Builder
                .builder()
                .rate(builderResponseDto.getRate())
                .basicDetails(convertBasicDetailsToDto(builderResponseDto.getBasicDetails()))
                .companyDetails(convertCompanyDetailsToDto(builderResponseDto.getCompanyDetails()))
                .isAvailable(builderResponseDto.getIsAvailable())
                .yearsOfExperience(builderResponseDto.getYearsOfExperience())
                .build();
    }

    public Customer convertCustomerEntityToDto(CreateCustomerRequestDto customerRequestDto) {
        return Customer
                .builder()
                .basicDetails(convertBasicDetailsToDto(customerRequestDto.getBasicDetails()))
                .build();
    }

    public Order convertOrderEntityToDto(CreateOrderRequestDto orderRequestDto) {
        return Order
                .builder()
                .build();
    }

    public Project convertProjectEntityToDto(CreateProjectRequestDto projectRequestDto) {
        return Project
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
                .build();
    }


}
