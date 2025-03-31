package com.constuction.utils;

import com.constuction.dto.request.create.*;
import com.constuction.entity.*;
import com.constuction.enums.*;
import com.constuction.exceptions.ConstructionException;
import com.constuction.repository.*;
import lombok.RequiredArgsConstructor;
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

    public Admin convertAdminEntityToDto(CreateAdminRequestDto adminRequestDto) throws ConstructionException {
        Long basicDetailsId = convertBasicDetailsToDto(adminRequestDto.getBasicDetails()).getId();
        Long locationDetailsId = convertLocationDetailsToDto(adminRequestDto.getLocationDetails()).getId();
        BasicDetails savedBasicDetails = basicDetailsRepository.findById(basicDetailsId).orElseThrow(() -> new ConstructionException("Basic details not found for id: " + basicDetailsId));
        LocationDetails savedLocationDetails = locationDetailsRepository.findById(locationDetailsId).orElseThrow(() -> new ConstructionException("Location details not found for id: " + locationDetailsId));
        savedLocationDetails = savedLocationDetails
                .toBuilder()
                .type(LocationType.ADMIN)
                .build();
        return
                adminRepository.save(
                        Admin
                                .builder()
                                .basicDetails(savedBasicDetails)
                                .locationDetails(savedLocationDetails)
                                .isDeleted(Boolean.FALSE)
                                .build());
    }

    public com.constuction.entity.Builder convertBuilderEntityToDto(CreateBuilderRequestDto builderRequestDto) throws ConstructionException {

        Long basicDetailsId = convertBasicDetailsToDto(builderRequestDto.getBasicDetails()).getId();
        Long companyDetailsId = convertCompanyDetailsToDto(builderRequestDto.getCompanyDetails()).getId();
        Long locationDetailsId = convertLocationDetailsToDto(builderRequestDto.getLocationDetails()).getId();

        BasicDetails savedBasicDetails = basicDetailsRepository.findById(basicDetailsId).orElseThrow(() -> new ConstructionException("Basic details not found for id: " + basicDetailsId));
        CompanyDetails savedCompanyDetails = companyDetailsRepository.findById(companyDetailsId).orElseThrow(() -> new ConstructionException("Company details not found for id: " + companyDetailsId));
        LocationDetails savedLocationDetails = locationDetailsRepository.findById(locationDetailsId).orElseThrow(() -> new ConstructionException("Location details not found for id: " + locationDetailsId));
        savedLocationDetails = savedLocationDetails
                .toBuilder()
                .type(LocationType.BUILDER)
                .build();
        return
                builderRepository.save(
                        com.constuction.entity.Builder
                                .builder()
                                .rate(builderRequestDto.getRate())
                                .basicDetails(savedBasicDetails)
                                .companyDetails(savedCompanyDetails)
                                .isAvailable(builderRequestDto.getIsAvailable())
                                .yearsOfExperience(builderRequestDto.getYearsOfExperience())
                                .locationDetails(savedLocationDetails)
                                .isDeleted(Boolean.FALSE)
                                .build());
    }

    public Customer convertCustomerEntityToDto(CreateCustomerRequestDto customerRequestDto) throws ConstructionException {

        Long basicDetailsId = convertBasicDetailsToDto(customerRequestDto.getBasicDetails()).getId();
        Long locationDetailsId = convertLocationDetailsToDto(customerRequestDto.getLocationDetails()).getId();

        BasicDetails savedBasicDetails = basicDetailsRepository.findById(basicDetailsId).orElseThrow(() -> new ConstructionException("Basic details not found for id: " + basicDetailsId));
        LocationDetails savedLocationDetails = locationDetailsRepository.findById(locationDetailsId).orElseThrow(() -> new ConstructionException("Location details not found for id: " + locationDetailsId));
        savedLocationDetails = savedLocationDetails
                .toBuilder()
                .type(LocationType.CUSTOMER)
                .build();
        return
                customerRepository.save(
                        Customer
                                .builder()
                                .basicDetails(savedBasicDetails)
                                .locationDetails(savedLocationDetails)
                                .isDeleted(Boolean.FALSE)
                                .build());
    }

    public Order convertOrderEntityToDto(CreateOrderRequestDto orderRequestDto) throws ConstructionException {

        Long customerId = convertCustomerEntityToDto(orderRequestDto.getCreateCustomerRequestDto()).getId();
        Long projectId = convertProjectEntityToDto(orderRequestDto.getCreateProjectRequestDto()).getId();
        Customer savedCustomer = customerRepository.findById(customerId).orElseThrow(() -> new ConstructionException("Customer details not found for id: " + customerId));
        Project savedProject = projectRepository.findById(projectId).orElseThrow(() -> new ConstructionException("Project details not found for id: " + projectId));
        return
                orderRepository.save(
                        Order
                                .builder()
                                .customer(savedCustomer)
                                .project(savedProject)
                                .isDeleted(Boolean.FALSE)
                                .build());
    }

    public Project convertProjectEntityToDto(CreateProjectRequestDto projectRequestDto) throws ConstructionException {

        Long builderId = convertBuilderEntityToDto(projectRequestDto.getBuilder()).getId();
        Long locationDetailsId = convertLocationDetailsToDto(projectRequestDto.getLocationDetails()).getId();
        Builder savedBuilder = builderRepository.findById(builderId).orElseThrow(() -> new ConstructionException("Builder details not found for id: " + builderId));
        LocationDetails savedLocationDetails = locationDetailsRepository.findById(locationDetailsId).orElseThrow(() -> new ConstructionException("Location details not found for id: " + locationDetailsId));
        savedLocationDetails = savedLocationDetails
                .toBuilder()
                .type(LocationType.PROJECT)
                .build();
        return
                projectRepository.save(
                        Project
                                .builder()
                                .builder(savedBuilder)
                                .area(projectRequestDto.getArea())
                                .constructionType(ConstructionType.valueOf(projectRequestDto.getConstructionType().toUpperCase()))
                                .description(projectRequestDto.getDescription())
                                .projectStatus(ProjectStatus.valueOf(projectRequestDto.getProjectStatus().toUpperCase()))
                                .startDate(projectRequestDto.getStartDate())
                                .endDate(projectRequestDto.getEndDate())
                                .estimatedPrice(projectRequestDto.getEstimatedPrice())
                                .projectName(projectRequestDto.getProjectName())
                                .locationDetails(savedLocationDetails)
                                .isDeleted(Boolean.FALSE)
                                .build());
    }


}
