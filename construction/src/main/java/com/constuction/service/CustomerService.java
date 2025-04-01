package com.constuction.service;

import com.constuction.dto.response.ApiResponseDto;
import com.constuction.dto.request.create.CreateCustomerRequestDto;
import com.constuction.dto.response.CreateCustomerResponseDto;
import com.constuction.exceptions.ConstructionException;

import java.util.List;

public interface CustomerService {

    public CreateCustomerResponseDto createCustomer(CreateCustomerRequestDto CustomerRequestDto) throws ConstructionException;
    public CreateCustomerResponseDto updateCustomer(CreateCustomerRequestDto CustomerRequestDto);
    public List<CreateCustomerResponseDto> getAllCustomers();

    ApiResponseDto deleteCustomer(Long id) throws ConstructionException;
}
