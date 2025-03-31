package com.constuction.service;

import com.constuction.dto.ApiResponseDto;
import com.constuction.dto.request.create.CreateOrderRequestDto;
import com.constuction.dto.response.CreateOrderResponseDto;
import com.constuction.exceptions.ConstructionException;

import java.util.List;

public interface OrderService {

    public CreateOrderResponseDto createOrder(CreateOrderRequestDto OrderRequestDto) throws ConstructionException;
    public CreateOrderResponseDto updateOrder(CreateOrderRequestDto OrderRequestDto);
    public List<CreateOrderResponseDto> getAllOrders();

    ApiResponseDto deleteOrder(Long id) throws ConstructionException;
}
