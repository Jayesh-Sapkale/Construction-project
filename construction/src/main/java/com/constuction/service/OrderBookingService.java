package com.constuction.service;

import com.constuction.dto.ApiResponseDto;
import com.constuction.dto.request.CreateOrderBookingRequestDto;

import java.util.List;

public interface OrderBookingService {

    public ApiResponseDto createOrderBooking(CreateOrderBookingRequestDto OrderBookingRequestDto);
    public ApiResponseDto updateOrderBooking(CreateOrderBookingRequestDto OrderBookingRequestDto);
    public List<ApiResponseDto> getAllOrderBookings();
}
