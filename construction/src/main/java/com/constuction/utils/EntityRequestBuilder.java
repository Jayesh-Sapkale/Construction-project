package com.constuction.utils;

import com.constuction.dto.request.create.*;
import com.constuction.entity.*;
import com.constuction.enums.*;
import com.constuction.exceptions.ConstructionException;
import com.constuction.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class EntityRequestBuilder {
    public final OrderRepository orderRepository;
    public final ProjectRepository projectRepository;
    public final AdminRepository adminRepository;
    public final BuilderRepository builderRepository;
    public final CustomerRepository customerRepository;
    public final BasicDetailsRepository basicDetailsRepository;
    public final CompanyDetailsRepository companyDetailsRepository;
    public final LocationDetailsRepository locationDetailsRepository;


    public LocationDetails convertLocationDetailsToDto(CreateLocationDetailsRequestDto locationDetailsRequestDto, LocationType type) {
        return locationDetailsRepository.save(
                LocationDetails
                        .builder()
                        .address(locationDetailsRequestDto.getAddress())
                        .city(locationDetailsRequestDto.getCity())
                        .state(locationDetailsRequestDto.getState())
                        .pincode(locationDetailsRequestDto.getPincode())
                        .type(type)
                        .build());

    }

    public BasicDetails convertBasicDetailsToDto(CreateBasicDetailsRequestDto basicDetailsRequestDto, Role role) {
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
                                .role(role)
                                .build());
    }

    public CompanyDetails convertCompanyDetailsToDto(CreateCompanyDetailsRequestDto companyDetailsRequestDto) {

        LocationDetails savedLocationDetails = convertLocationDetailsToDto(companyDetailsRequestDto.getLocationDetails(), LocationType.COMPANY);
        log.info("Company location details saved successfully with id: {}",savedLocationDetails.getId());
        return
                companyDetailsRepository.save(
                        CompanyDetails
                                .builder()
                                .name(companyDetailsRequestDto.getName())
                                .numberOfEmployee(companyDetailsRequestDto.getNumberOfEmployee())
                                .website(companyDetailsRequestDto.getWebsite())
                                .annualRevenue(companyDetailsRequestDto.getAnnualRevenue())
                                .constructionType(ConstructionType.valueOf(companyDetailsRequestDto.getConstructionType().toUpperCase()))
                                .foundingDate(companyDetailsRequestDto.getFoundingDate())
                                .locationDetails(savedLocationDetails)
                                .isDeleted(Boolean.FALSE)
                                .build());
    }

    public Admin convertAdminEntityToDto(CreateAdminRequestDto adminRequestDto) throws ConstructionException {
        Long basicDetailsId = convertBasicDetailsToDto(adminRequestDto.getBasicDetails(), Role.ADMIN).getId();
        Long locationDetailsId = convertLocationDetailsToDto(adminRequestDto.getLocationDetails(), LocationType.ADMIN).getId();
        BasicDetails savedBasicDetails = basicDetailsRepository.findById(basicDetailsId).orElseThrow(() -> new ConstructionException("Basic details not found for id: " + basicDetailsId));
        log.info("Basic details saved successfully with id: {}",savedBasicDetails.getId());
        LocationDetails savedLocationDetails = locationDetailsRepository.findById(locationDetailsId).orElseThrow(() -> new ConstructionException("Location details not found for id: " + locationDetailsId));
        log.info("Admin location details saved successfully with id: {}",savedLocationDetails.getId());

        return
                adminRepository.save(
                        Admin
                                .builder()
                                .basicDetails(savedBasicDetails)
                                .locationDetails(savedLocationDetails)
                                .isDeleted(Boolean.FALSE)
                                .build());
    }

    public Builder convertBuilderEntityToDto(CreateBuilderRequestDto builderRequestDto) throws ConstructionException {

        Long basicDetailsId = convertBasicDetailsToDto(builderRequestDto.getBasicDetails(), Role.BUILDER).getId();
        CompanyDetails existingCompany = companyDetailsRepository.findByName(builderRequestDto.getCompanyDetails().getName());
        Long companyDetailsId = Objects.nonNull(existingCompany) ? existingCompany.getId() : convertCompanyDetailsToDto(builderRequestDto.getCompanyDetails()).getId();
        Long locationDetailsId = convertLocationDetailsToDto(builderRequestDto.getLocationDetails(), LocationType.BUILDER).getId();

        BasicDetails savedBasicDetails = basicDetailsRepository.findById(basicDetailsId).orElseThrow(() -> new ConstructionException("Basic details not found for id: " + basicDetailsId));
        log.info("Builder basic details saved successfully with id: {}",savedBasicDetails.getId());
        CompanyDetails savedCompanyDetails = companyDetailsRepository.findById(companyDetailsId).orElseThrow(() -> new ConstructionException("Company details not found for id: " + companyDetailsId));
        log.info("Builder Company details saved successfully with id: {}",savedCompanyDetails.getId());
        LocationDetails savedLocationDetails = locationDetailsRepository.findById(locationDetailsId).orElseThrow(() -> new ConstructionException("Location details not found for id: " + locationDetailsId));
        log.info("Builder location details saved successfully with id: {}",savedLocationDetails.getId());

        return
                builderRepository.save(
                        com.constuction.entity.Builder
                                .builder()
                                .rate(builderRequestDto.getRate())
                                .basicDetails(basicDetailsRepository.save(savedBasicDetails
                                        .toBuilder()
                                        .role(Role.BUILDER)
                                        .build()))
                                .companyDetails(savedCompanyDetails)
                                .isAvailable(builderRequestDto.getIsAvailable())
                                .yearsOfExperience(builderRequestDto.getYearsOfExperience())
                                .locationDetails(locationDetailsRepository.save(savedLocationDetails.
                                        toBuilder()
                                        .type(LocationType.BUILDER)
                                        .build()))
                                .isDeleted(Boolean.FALSE)
                                .build());
    }

    public Customer convertCustomerEntityToDto(CreateCustomerRequestDto customerRequestDto) throws ConstructionException {

        Long basicDetailsId = convertBasicDetailsToDto(customerRequestDto.getBasicDetails(), Role.CUSTOMER).getId();
        Long locationDetailsId = convertLocationDetailsToDto(customerRequestDto.getLocationDetails(), LocationType.CUSTOMER).getId();

        BasicDetails savedBasicDetails = basicDetailsRepository.findById(basicDetailsId).orElseThrow(() -> new ConstructionException("Basic details not found for id: " + basicDetailsId));
        log.info("Customer basic details saved successfully with id: {}",savedBasicDetails.getId());
        LocationDetails savedLocationDetails = locationDetailsRepository.findById(locationDetailsId).orElseThrow(() -> new ConstructionException("Location details not found for id: " + locationDetailsId));
        log.info("Customer location details saved successfully with id: {}",savedLocationDetails.getId());

        return
                customerRepository.save(
                        Customer
                                .builder()
                                .basicDetails(basicDetailsRepository.save(savedBasicDetails.
                                        toBuilder()
                                        .role(Role.CUSTOMER)
                                        .build()))
                                .locationDetails(locationDetailsRepository.save(savedLocationDetails
                                        .toBuilder()
                                        .type(LocationType.CUSTOMER)
                                        .build()))
                                .isDeleted(Boolean.FALSE)
                                .build());
    }

    public Order convertOrderEntityToDto(CreateOrderRequestDto orderRequestDto) throws ConstructionException {

        Customer existingCustomer = customerRepository.findByFirstNameAndLastNameAndMobileNumber(orderRequestDto.getCustomer().getBasicDetails().getFirstName()
                , orderRequestDto.getCustomer().getBasicDetails().getLastName()
                , orderRequestDto.getCustomer().getBasicDetails().getMobileNumber());

        Project existingProject = projectRepository.findByProjectName(orderRequestDto.getProject().getProjectName());

        Long customerId = Objects.nonNull(existingCustomer) ? existingCustomer.getId() : convertCustomerEntityToDto(orderRequestDto.getCustomer()).getId();
        Long projectId = Objects.nonNull(existingProject) ? existingProject.getId() : convertProjectEntityToDto(orderRequestDto.getProject()).getId();
        Customer savedCustomer = customerRepository.findById(customerId).orElseThrow(() -> new ConstructionException("Customer details not found for id: " + customerId));
        log.info("Order customer details saved successfully with id: {}",savedCustomer.getId());
        Project savedProject = projectRepository.findById(projectId).orElseThrow(() -> new ConstructionException("Project details not found for id: " + projectId));
        log.info("Order project details saved successfully with id: {}",savedProject.getId());

        return
                orderRepository.save(
                        Order
                                .builder()
                                .customer(savedCustomer)
                                .project(savedProject)
                                .orderStatus(OrderStatus.STARTED)
                                .isDeleted(Boolean.FALSE)
                                .build());
    }

    public Project convertProjectEntityToDto(CreateProjectRequestDto projectRequestDto) throws ConstructionException {

        Builder exitingBuilder = builderRepository.findByFirstNameAndLastNameAndMobileNumber(projectRequestDto.getBuilder().getBasicDetails().getFirstName()
                , projectRequestDto.getBuilder().getBasicDetails().getLastName()
                , projectRequestDto.getBuilder().getBasicDetails().getMobileNumber());

        LocationDetails existingLocationDetails = locationDetailsRepository.findByStateIgnoreCaseAndCityIgnoreCaseAndPincodeAndAddressIgnoreCase(
                projectRequestDto.getLocationDetails().getState(),
                projectRequestDto.getLocationDetails().getCity(),
                projectRequestDto.getLocationDetails().getPincode(),
                projectRequestDto.getLocationDetails().getAddress()
        );

        Long builderId = Objects.nonNull(exitingBuilder) ? exitingBuilder.getId() : convertBuilderEntityToDto(projectRequestDto.getBuilder()).getId();
        Long locationDetailsId = Objects.nonNull(existingLocationDetails) ? existingLocationDetails.getId() : convertLocationDetailsToDto(projectRequestDto.getLocationDetails(), LocationType.PROJECT).getId();
        Builder savedBuilder = builderRepository.findById(builderId).orElseThrow(() -> new ConstructionException("Builder details not found for id: " + builderId));
        log.info("Project builder details saved successfully with id: {}",savedBuilder.getId());
        LocationDetails savedLocationDetails = locationDetailsRepository.findById(locationDetailsId).orElseThrow(() -> new ConstructionException("Location details not found for id: " + locationDetailsId));
        log.info("Project location details saved successfully with id: {}",savedLocationDetails.getId());

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
