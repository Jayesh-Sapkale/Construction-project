package com.constuction.serviceImpl;

import com.constuction.dto.ApiResponseDto;
import com.constuction.dto.request.create.CreateCustomerRequestDto;
import com.constuction.dto.response.CreateCustomerResponseDto;
import com.constuction.entity.Customer;
import com.constuction.exceptions.ConstructionException;
import com.constuction.repository.CustomerRepository;
import com.constuction.service.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {
    public final EntityResponseBuilder entityResponseBuilder;
    public final EntityRequestBuilder entityRequestBuilder;
    public final CustomerRepository customerRepository;

    @Override
    public CreateCustomerResponseDto createCustomer(CreateCustomerRequestDto customerRequestDto) throws ConstructionException {
        log.info("START --> CustomerServiceImpl.createCustomer()");

        Customer existingCustomer = customerRepository.findByFirstNameAndLastNameAndMobileNumber(customerRequestDto.getBasicDetails().getFirstName()
                , customerRequestDto.getBasicDetails().getLastName()
                , customerRequestDto.getBasicDetails().getMobileNumber());

        if (Objects.nonNull(existingCustomer))
            throw new ConstructionException("customer already exist");
        Customer savedCustomer = entityRequestBuilder.convertCustomerEntityToDto(customerRequestDto);
        log.info("END --> CustomerServiceImpl.createCustomer()");
        return entityResponseBuilder.convertCustomerEntityToDto(savedCustomer.getId());
    }

    @Override
    public CreateCustomerResponseDto updateCustomer(CreateCustomerRequestDto CustomerRequestDto) {
        return null;
    }

    @Override
    public List<CreateCustomerResponseDto> getAllCustomers() {
        log.info("START --> CustomerServiceImpl.getAllCustomers()");
        List<Customer> Customers = customerRepository.findAll();
        log.info("END --> CustomerServiceImpl.getAllCustomers()");
        return Customers.stream()
                .map(
                        Customer -> {
                            try {
                                return entityResponseBuilder.convertCustomerEntityToDto(Customer.getId());
                            } catch (ConstructionException e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
                .toList();
    }

    @Override
    public ApiResponseDto deleteCustomer(Long id) throws ConstructionException {
        log.info("START --> CustomerServiceImpl.deleteCustomer()");
        Customer savedCustomer = customerRepository.findById(id).orElseThrow(() -> new ConstructionException("Customer not found with id: " + id));
        savedCustomer.setIsDeleted(Boolean.TRUE);
        Customer deletedCustomer = customerRepository.save(savedCustomer);
        log.info("END --> CustomerServiceImpl.deleteCustomer()");
        return new ApiResponseDto("SUCCESS", deletedCustomer, "Customer deleted successfully");
    }
}
