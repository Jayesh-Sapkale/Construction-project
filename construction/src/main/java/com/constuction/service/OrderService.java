package com.constuction.service;

import com.constuction.dto.request.CreateOrderRequestDto;
import com.constuction.dto.response.CreateOrderResponseDto;

import java.util.List;

public interface OrderService {

    public CreateOrderResponseDto createOrder(CreateOrderRequestDto OrderRequestDto);
    public CreateOrderResponseDto updateOrder(CreateOrderRequestDto OrderRequestDto);
    public List<CreateOrderResponseDto> getAllOrders();
}
