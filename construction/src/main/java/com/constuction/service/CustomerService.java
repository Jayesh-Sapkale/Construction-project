package com.constuction.service;

import com.constuction.dto.ApiResponseDto;
import com.constuction.dto.request.CreateCustomerRequestDto;

import java.util.List;

public interface CustomerService {

    public ApiResponseDto createCustomer(CreateCustomerRequestDto CustomerRequestDto);
    public ApiResponseDto updateCustomer(CreateCustomerRequestDto CustomerRequestDto);
    public List<ApiResponseDto> getAllCustomers();
}
