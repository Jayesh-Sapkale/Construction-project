package com.constuction.serviceImpl;

import com.constuction.dto.request.CreateCustomerRequestDto;
import com.constuction.dto.response.CreateCustomerResponseDto;
import com.constuction.entity.Customer;
import com.constuction.repository.CustomerRepository;
import com.constuction.service.CustomerService;
import com.constuction.utils.EntityRequestBuilder;
import com.constuction.utils.EntityResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    public final EntityResponseBuilder entityResponseBuilder;
    public final EntityRequestBuilder entityRequestBuilder;
    public final CustomerRepository CustomerRepository;

    @Override
    public CreateCustomerResponseDto createCustomer(CreateCustomerRequestDto CustomerRequestDto) {
        Customer savedCustomer = CustomerRepository.save(entityRequestBuilder.convertCustomerEntityToDto(CustomerRequestDto));
        return entityResponseBuilder.convertCustomerEntityToDto(savedCustomer.getId());
    }

    @Override
    public CreateCustomerResponseDto updateCustomer(CreateCustomerRequestDto CustomerRequestDto) {
        return null;
    }

    @Override
    public List<CreateCustomerResponseDto> getAllCustomers() {
        List<Customer> Customers = CustomerRepository.findAll();
        return Customers.stream()
                .map(
                        Customer -> entityResponseBuilder.convertCustomerEntityToDto(Customer.getId())
                ).toList();
    }
}
