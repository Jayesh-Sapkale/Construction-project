package com.constuction.utils;

import com.constuction.dto.response.*;
import com.constuction.entity.*;
import com.constuction.repository.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@Builder(toBuilder = true)
public class EntityResponseBuilder {

    public final OrderRepository orderRepository;
    public final ProjectRepository projectRepository;
    public final AdminRepository adminRepository;
    public final BuilderRepository builderRepository;
    public final CustomerRepository customerRepository;
    public final BasicDetailsRepository basicDetailsRepository;
    public final CompanyDetailsRepository companyDetailsRepository;
    public final LocationDetailsRepository locationDetailsRepository;


    public CreateLocationDetailsResponseDto convertLocationDetailsToDto(Long id) {
        LocationDetails savedLocationDetails = locationDetailsRepository.findById(id).orElseThrow();
        CreateLocationDetailsResponseDto locationDetailsResponseDto = new CreateLocationDetailsResponseDto();

        if (Objects.nonNull(savedLocationDetails)) {
            locationDetailsResponseDto = CreateLocationDetailsResponseDto
                    .builder()
                    .id(savedLocationDetails.getId())
                    .address(savedLocationDetails.getAddress())
                    .city(savedLocationDetails.getCity())
                    .State(savedLocationDetails.getState())
                    .pincode(savedLocationDetails.getPincode())
                    .build();
        }
        return locationDetailsResponseDto;
    }

    public CreateBasicDetailsResponseDto convertBasicDetailsToDto(Long id) {
        Admin savedAdmin = adminRepository.findById(id).orElseThrow();
        BasicDetails savedBasicDetails = savedAdmin.getBasicDetails();
        CreateBasicDetailsResponseDto basicDetailsResponseDto = new CreateBasicDetailsResponseDto();

        if (Objects.nonNull(savedBasicDetails)) {
            basicDetailsResponseDto = CreateBasicDetailsResponseDto
                    .builder()
                    .id(savedBasicDetails.getId())
                    .dob(savedBasicDetails.getDob())
                    .email(savedBasicDetails.getEmail())
                    .firstName(savedBasicDetails.getFirstName())
                    .lastName(savedBasicDetails.getLastName())
                    .gender(savedBasicDetails.getGender().toString())
                    .mobileNumber(savedBasicDetails.getMobileNumber())
                    .role(savedBasicDetails.getRole().toString())
                    .build();
        }
        return basicDetailsResponseDto;
    }

    public CreateCompanyDetailsResponseDto convertCompanyDetailsToDto(Long id) {
        CompanyDetails savedCompanyDetails = companyDetailsRepository.findById(id).orElseThrow();
        CreateCompanyDetailsResponseDto companyDetailsResponseDto = new CreateCompanyDetailsResponseDto();

        if (Objects.nonNull(savedCompanyDetails)) {
            companyDetailsResponseDto = CreateCompanyDetailsResponseDto
                    .builder()
                    .id(savedCompanyDetails.getId())
                    .annualRevenue(savedCompanyDetails.getAnnualRevenue())
                    .constructionType(savedCompanyDetails.getConstructionType().toString())
                    .foundingDate(savedCompanyDetails.getFoundingDate())
                    .locationDetails(convertLocationDetailsToDto(savedCompanyDetails.getLocationDetails().getId()))
                    .build();
        }
        return companyDetailsResponseDto;
    }

    public CreateAdminResponseDto convertAdminEntityToDto(Long id) {
        Admin savedAdmin = adminRepository.findById(id).orElseThrow();
        CreateAdminResponseDto adminResponseDto = new CreateAdminResponseDto();
        if (Objects.nonNull(savedAdmin)) {
            adminResponseDto = CreateAdminResponseDto
                    .builder()
                    .id(savedAdmin.getId())
                    .basicDetails(convertBasicDetailsToDto(savedAdmin.getBasicDetails().getId()))
                    .build();
        }
        return adminResponseDto;
    }

    public CreateBuilderResponseDto convertBuilderEntityToDto(Long id) {
        com.constuction.entity.Builder savedBuilder = builderRepository.findById(id).orElseThrow();
        CreateBuilderResponseDto builderResponseDto = new CreateBuilderResponseDto();
        if (Objects.nonNull(savedBuilder)) {
            builderResponseDto = CreateBuilderResponseDto
                    .builder()
                    .id(savedBuilder.getId())
                    .rate(savedBuilder.getRate())
                    .basicDetails(convertBasicDetailsToDto(savedBuilder.getBasicDetails().getId()))
                    .companyDetails(convertCompanyDetailsToDto(savedBuilder.getCompanyDetails().getId()))
                    .isAvailable(savedBuilder.getIsAvailable())
                    .yearsOfExperience(savedBuilder.getYearsOfExperience())
                    .build();
        }
        return builderResponseDto;
    }

    public CreateCustomerResponseDto convertCustomerEntityToDto(Long id) {
        Customer savedCustomer = customerRepository.findById(id).orElseThrow();
        CreateCustomerResponseDto customerResponseDto = new CreateCustomerResponseDto();
        if (Objects.nonNull(savedCustomer)) {
            customerResponseDto = CreateCustomerResponseDto
                    .builder()
                    .id(savedCustomer.getId())
                    .basicDetails(convertBasicDetailsToDto(savedCustomer.getBasicDetails().getId()))
                    .build();
        }
        return customerResponseDto;
    }

    public CreateOrderResponseDto convertOrderEntityToDto(Long id) {
        Order savedOrder = orderRepository.findById(id).orElseThrow();
        CreateOrderResponseDto orderResponseDto = new CreateOrderResponseDto();
        if (Objects.nonNull(savedOrder)) {
            orderResponseDto = CreateOrderResponseDto
                    .builder()
                    .id(savedOrder.getId())
                    .build();
        }
        return orderResponseDto;
    }

    public CreateProjectResponseDto convertProjectEntityToDto(Long id) {
        Project savedProject = projectRepository.findById(id).orElseThrow();
        CreateProjectResponseDto projectResponseDto = new CreateProjectResponseDto();

        if (Objects.nonNull((savedProject))) {
            projectResponseDto = CreateProjectResponseDto
                    .builder()
                    .id(savedProject.getId())
                    .builder(convertBuilderEntityToDto(savedProject.getBuilder().getId()))
                    .area(savedProject.getArea())
                    .constructionType(savedProject.getConstructionType().toString())
                    .description(savedProject.getDescription())
                    .projectStatus(savedProject.getProjectStatus().toString())
                    .startDate(savedProject.getStartDate())
                    .endDate(savedProject.getEndDate())
                    .estimatedPrice(savedProject.getEstimatedPrice())
                    .projectName(savedProject.getProjectName())
                    .locationDetails(convertLocationDetailsToDto(savedProject.getLocationDetails().getId()))
                    .build();
        }

        return projectResponseDto;
    }


}
