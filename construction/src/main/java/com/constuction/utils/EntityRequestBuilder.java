package com.constuction.utils;

import com.constuction.dto.request.create.*;
import com.constuction.entity.*;
import com.constuction.enums.*;
import com.constuction.exceptions.ConstructionException;
import com.constuction.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class EntityRequestBuilder {
	private final OrderRepository orderRepository;
	private final ProjectRepository projectRepository;
	private final AdminRepository adminRepository;
	private final BuilderRepository builderRepository;
	private final CustomerRepository customerRepository;
	private final BasicDetailsRepository basicDetailsRepository;
	private final CompanyDetailsRepository companyDetailsRepository;
	private final LocationDetailsRepository locationDetailsRepository;
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


	@Transactional
	public LocationDetails convertLocationDetailsDtoToEntity(CreateLocationDetailsRequestDto locationDetailsRequestDto, LocationType type) {
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

	@Transactional
	public BasicDetails convertBasicDetailsDtoToEntity(CreateBasicDetailsRequestDto basicDetailsRequestDto, Role role) {
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

	@Transactional
	public CompanyDetails convertCompanyDetailsDtoToEntity(CreateCompanyDetailsRequestDto companyDetailsRequestDto) {

		LocationDetails savedLocationDetails = convertLocationDetailsDtoToEntity(companyDetailsRequestDto.getLocationDetails(), LocationType.COMPANY);
		log.info("Company location details saved successfully with id: {}", savedLocationDetails.getId());
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

	@Transactional
	public Admin convertAdminDtoToEntity(CreateAdminRequestDto adminRequestDto) throws ConstructionException {
		Long basicDetailsId = convertBasicDetailsDtoToEntity(adminRequestDto.getBasicDetails(), Role.ADMIN).getId();
		Long locationDetailsId = convertLocationDetailsDtoToEntity(adminRequestDto.getLocationDetails(), LocationType.ADMIN).getId();
		BasicDetails savedBasicDetails = basicDetailsRepository.findById(basicDetailsId).orElseThrow(() -> new ConstructionException("Basic details not found for id: " + basicDetailsId));
		log.info("Basic details saved successfully with id: {}", savedBasicDetails.getId());
		LocationDetails savedLocationDetails = locationDetailsRepository.findById(locationDetailsId).orElseThrow(() -> new ConstructionException("Location details not found for id: " + locationDetailsId));
		log.info("Admin location details saved successfully with id: {}", savedLocationDetails.getId());

		return
				adminRepository.save(
						Admin
								.builder()
								.basicDetails(savedBasicDetails)
								.locationDetails(savedLocationDetails)
								.isDeleted(Boolean.FALSE)
								.build());
	}

	@Transactional
	public Builder convertBuilderDtoToEntity(CreateBuilderRequestDto builderRequestDto) throws ConstructionException {

		Long basicDetailsId = convertBasicDetailsDtoToEntity(builderRequestDto.getBasicDetails(), Role.BUILDER).getId();
		CompanyDetails existingCompany = companyDetailsRepository.findByName(builderRequestDto.getCompanyDetails().getName());
		Long companyDetailsId = Objects.nonNull(existingCompany) ? existingCompany.getId() : convertCompanyDetailsDtoToEntity(builderRequestDto.getCompanyDetails()).getId();
		Long locationDetailsId = convertLocationDetailsDtoToEntity(builderRequestDto.getLocationDetails(), LocationType.BUILDER).getId();

		BasicDetails savedBasicDetails = basicDetailsRepository.findById(basicDetailsId).orElseThrow(() -> new ConstructionException("Basic details not found for id: " + basicDetailsId));
		log.info("Builder basic details saved successfully with id: {}", savedBasicDetails.getId());
		CompanyDetails savedCompanyDetails = companyDetailsRepository.findById(companyDetailsId).orElseThrow(() -> new ConstructionException("Company details not found for id: " + companyDetailsId));
		log.info("Builder Company details saved successfully with id: {}", savedCompanyDetails.getId());
		LocationDetails savedLocationDetails = locationDetailsRepository.findById(locationDetailsId).orElseThrow(() -> new ConstructionException("Location details not found for id: " + locationDetailsId));
		log.info("Builder location details saved successfully with id: {}", savedLocationDetails.getId());

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

	@Transactional
	public Customer convertCustomerDtoToEntity(CreateCustomerRequestDto customerRequestDto) throws ConstructionException {

		Long basicDetailsId = convertBasicDetailsDtoToEntity(customerRequestDto.getBasicDetails(), Role.CUSTOMER).getId();
		Long locationDetailsId = convertLocationDetailsDtoToEntity(customerRequestDto.getLocationDetails(), LocationType.CUSTOMER).getId();

		BasicDetails savedBasicDetails = basicDetailsRepository.findById(basicDetailsId).orElseThrow(() -> new ConstructionException("Basic details not found for id: " + basicDetailsId));
		log.info("Customer basic details saved successfully with id: {}", savedBasicDetails.getId());
		LocationDetails savedLocationDetails = locationDetailsRepository.findById(locationDetailsId).orElseThrow(() -> new ConstructionException("Location details not found for id: " + locationDetailsId));
		log.info("Customer location details saved successfully with id: {}", savedLocationDetails.getId());

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

	@Transactional
	public Order convertOrderDtoToEntity(CreateOrderRequestDto orderRequestDto) throws ConstructionException {

		Customer existingCustomer = customerRepository.findByFirstNameAndLastNameAndMobileNumber(orderRequestDto.getCustomer().getBasicDetails().getFirstName()
				, orderRequestDto.getCustomer().getBasicDetails().getLastName()
				, orderRequestDto.getCustomer().getBasicDetails().getMobileNumber());

		Project existingProject = projectRepository.findByProjectName(orderRequestDto.getProject().getProjectName());

		Long customerId = Objects.nonNull(existingCustomer) ? existingCustomer.getId() : convertCustomerDtoToEntity(orderRequestDto.getCustomer()).getId();
		Long projectId = Objects.nonNull(existingProject) ? existingProject.getId() : convertProjectDtoToEntity(orderRequestDto.getProject()).getId();
		Customer savedCustomer = customerRepository.findById(customerId).orElseThrow(() -> new ConstructionException("Customer details not found for id: " + customerId));
		log.info("Order customer details saved successfully with id: {}", savedCustomer.getId());
		Project savedProject = projectRepository.findById(projectId).orElseThrow(() -> new ConstructionException("Project details not found for id: " + projectId));
		log.info("Order project details saved successfully with id: {}", savedProject.getId());

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

	@Transactional
	public Project convertProjectDtoToEntity(CreateProjectRequestDto projectRequestDto) throws ConstructionException {

		Builder exitingBuilder = builderRepository.findByFirstNameAndLastNameAndMobileNumber(projectRequestDto.getBuilder().getBasicDetails().getFirstName()
				, projectRequestDto.getBuilder().getBasicDetails().getLastName()
				, projectRequestDto.getBuilder().getBasicDetails().getMobileNumber());

		LocationDetails existingLocationDetails = locationDetailsRepository.findByStateIgnoreCaseAndCityIgnoreCaseAndPincodeAndAddressIgnoreCase(
				projectRequestDto.getLocationDetails().getState(),
				projectRequestDto.getLocationDetails().getCity(),
				projectRequestDto.getLocationDetails().getPincode(),
				projectRequestDto.getLocationDetails().getAddress()
																																				);

		Long builderId = Objects.nonNull(exitingBuilder) ? exitingBuilder.getId() : convertBuilderDtoToEntity(projectRequestDto.getBuilder()).getId();
		Long locationDetailsId = Objects.nonNull(existingLocationDetails) ? existingLocationDetails.getId() : convertLocationDetailsDtoToEntity(projectRequestDto.getLocationDetails(), LocationType.PROJECT).getId();
		Builder savedBuilder = builderRepository.findById(builderId).orElseThrow(() -> new ConstructionException("Builder details not found for id: " + builderId));
		log.info("Project builder details saved successfully with id: {}", savedBuilder.getId());
		LocationDetails savedLocationDetails = locationDetailsRepository.findById(locationDetailsId).orElseThrow(() -> new ConstructionException("Location details not found for id: " + locationDetailsId));
		log.info("Project location details saved successfully with id: {}", savedLocationDetails.getId());

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

	@Transactional
	public Users convertUserEntityToDto(CreateUserRequestDto userRequestDto) throws ConstructionException {
		Users existingUser = userRepository.findByUserName(userRequestDto.getUserName());

		if(Objects.nonNull(existingUser))
			throw new ConstructionException("User already exists with id: "+existingUser.getId());
		
		return userRepository.save(
				Users
						.builder()
						.isDeleted(Boolean.FALSE)
						.userName(userRequestDto.getUserName())
						.password(encoder.encode(userRequestDto.getPassword()))
						.build());
	}


}
