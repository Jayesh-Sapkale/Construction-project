package com.constuction.utils;

import com.constuction.dto.response.*;
import com.constuction.entity.*;
import com.constuction.exceptions.ConstructionException;
import com.constuction.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EntityResponseBuilder {

    public final OrderRepository orderRepository;
    public final ProjectRepository projectRepository;
    public final AdminRepository adminRepository;
    public final BuilderRepository builderRepository;
    public final CustomerRepository customerRepository;
    public final BasicDetailsRepository basicDetailsRepository;
    public final CompanyDetailsRepository companyDetailsRepository;
    public final LocationDetailsRepository locationDetailsRepository;


    public CreateLocationDetailsResponseDto convertLocationDetailsToDto(Long id) throws ConstructionException {
        LocationDetails savedLocationDetails = locationDetailsRepository.findById(id).orElseThrow(() -> new ConstructionException("Location details not found for id: " + id));
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

    public CreateBasicDetailsResponseDto convertBasicDetailsToDto(Long id) throws ConstructionException {
        BasicDetails savedBasicDetails = basicDetailsRepository.findById(id).orElseThrow(() -> new ConstructionException("Basic details not found for id: " + id));
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

    public CreateCompanyDetailsResponseDto convertCompanyDetailsToDto(Long id) throws ConstructionException {
        CompanyDetails savedCompanyDetails = companyDetailsRepository.findById(id).orElseThrow(() -> new ConstructionException("Company details not found for id: " + id));
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

    public CreateAdminResponseDto convertAdminEntityToDto(Long id) throws ConstructionException {
        Admin savedAdmin = adminRepository.findById(id).orElseThrow(() -> new ConstructionException("Admin details not found for id: " + id));
        CreateAdminResponseDto adminResponseDto = new CreateAdminResponseDto();
        if (Objects.nonNull(savedAdmin)) {
            adminResponseDto = CreateAdminResponseDto
                    .builder()
                    .id(savedAdmin.getId())
                    .basicDetails(convertBasicDetailsToDto(savedAdmin.getBasicDetails().getId()))
                    .locationDetails(convertLocationDetailsToDto(savedAdmin.getLocationDetails().getId()))
                    .build();
        }
        return adminResponseDto;
    }

    public CreateBuilderResponseDto convertBuilderEntityToDto(Long id) throws ConstructionException {
        Builder savedBuilder = builderRepository.findById(id).orElseThrow(() -> new ConstructionException("Builder details not found for id: " + id));
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
                    .locationDetails(convertLocationDetailsToDto(savedBuilder.getLocationDetails().getId()))
                    .build();
        }
        return builderResponseDto;
    }

    public CreateCustomerResponseDto convertCustomerEntityToDto(Long id) throws ConstructionException {
        Customer savedCustomer = customerRepository.findById(id).orElseThrow(() -> new ConstructionException("Customer details not found for id: " + id));
        CreateCustomerResponseDto customerResponseDto = new CreateCustomerResponseDto();
        if (Objects.nonNull(savedCustomer)) {
            customerResponseDto = CreateCustomerResponseDto
                    .builder()
                    .id(savedCustomer.getId())
                    .basicDetails(convertBasicDetailsToDto(savedCustomer.getBasicDetails().getId()))
                    .locationDetails(convertLocationDetailsToDto(savedCustomer.getLocationDetails().getId()))
                    .build();
        }
        return customerResponseDto;
    }

    public CreateOrderResponseDto convertOrderEntityToDto(Long id) throws ConstructionException {
        Order savedOrder = orderRepository.findById(id).orElseThrow(() -> new ConstructionException("Order details not found for id: " + id));
        Customer savedCustomer = customerRepository.findById(savedOrder.getCustomer().getId()).orElseThrow(() -> new ConstructionException("Customer details not found for id: " + savedOrder.getCustomer().getId()));
        Project savedProject = projectRepository.findById(savedOrder.getProject().getId()).orElseThrow(() -> new ConstructionException("Project details not found for id: " + savedOrder.getProject().getId()));

        CreateOrderResponseDto orderResponseDto = new CreateOrderResponseDto();
        orderResponseDto = CreateOrderResponseDto
                .builder()
                .id(savedOrder.getId())
                .customer(convertCustomerEntityToDto(savedCustomer.getId()))
                .project(convertProjectEntityToDto(savedProject.getId()))
                .build();

        return orderResponseDto;
    }

    public CreateProjectResponseDto convertProjectEntityToDto(Long id) throws ConstructionException {
        Project savedProject = projectRepository.findById(id).orElseThrow(() -> new ConstructionException("Project details not found for id: " + id));
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
