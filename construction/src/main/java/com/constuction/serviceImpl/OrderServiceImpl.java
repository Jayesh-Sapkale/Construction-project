package com.constuction.serviceImpl;

import com.constuction.dto.request.CreateOrderRequestDto;
import com.constuction.dto.response.CreateOrderResponseDto;
import com.constuction.entity.Order;
import com.constuction.repository.OrderRepository;
import com.constuction.service.OrderService;
import com.constuction.utils.EntityRequestBuilder;
import com.constuction.utils.EntityResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    public final EntityResponseBuilder entityResponseBuilder;
    public final EntityRequestBuilder entityRequestBuilder;
    public final OrderRepository OrderRepository;

    @Override
    public CreateOrderResponseDto createOrder(CreateOrderRequestDto OrderRequestDto) {
        Order savedOrder = OrderRepository.save(entityRequestBuilder.convertOrderEntityToDto(OrderRequestDto));
        return entityResponseBuilder.convertOrderEntityToDto(savedOrder.getId());
    }

    @Override
    public CreateOrderResponseDto updateOrder(CreateOrderRequestDto OrderRequestDto) {
        return null;
    }

    @Override
    public List<CreateOrderResponseDto> getAllOrders() {
        List<Order> Orders = OrderRepository.findAll();
        return Orders.stream()
                .map(
                        Order -> entityResponseBuilder.convertOrderEntityToDto(Order.getId())
                ).toList();
    }
}
