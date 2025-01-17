package com.constuction.service;

import com.constuction.dto.ApiResponseDto;
import com.constuction.dto.request.CreateCustomerRequestDto;
import com.constuction.dto.response.CreateBuilderResponseDto;
import com.constuction.dto.response.CreateCustomerResponseDto;

import java.util.List;

public interface CustomerService {

    public CreateCustomerResponseDto createCustomer(CreateCustomerRequestDto CustomerRequestDto);
    public CreateCustomerResponseDto updateCustomer(CreateCustomerRequestDto CustomerRequestDto);
    public List<CreateCustomerResponseDto> getAllCustomers();
}
