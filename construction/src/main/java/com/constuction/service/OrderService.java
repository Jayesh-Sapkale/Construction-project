package com.constuction.service;

import com.constuction.dto.request.CreateOrderRequestDto;
import com.constuction.dto.response.CreateOrderResponseDto;

import java.util.List;

public interface OrderService {

    public CreateOrderResponseDto createOrderBooking(CreateOrderRequestDto OrderBookingRequestDto);
    public CreateOrderResponseDto updateOrderBooking(CreateOrderRequestDto OrderBookingRequestDto);
    public List<CreateOrderResponseDto> getAllOrderBookings();
}
