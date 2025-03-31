package com.constuction.serviceImpl;

import com.constuction.dto.ApiResponseDto;
import com.constuction.dto.request.create.CreateOrderRequestDto;
import com.constuction.dto.response.CreateOrderResponseDto;
import com.constuction.entity.Builder;
import com.constuction.entity.Customer;
import com.constuction.entity.Order;
import com.constuction.exceptions.ConstructionException;
import com.constuction.repository.OrderRepository;
import com.constuction.service.OrderService;
import com.constuction.utils.EntityRequestBuilder;
import com.constuction.utils.EntityResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    public final EntityResponseBuilder entityResponseBuilder;
    public final EntityRequestBuilder entityRequestBuilder;
    public final OrderRepository orderRepository;

    @Override
    public CreateOrderResponseDto createOrder(CreateOrderRequestDto orderRequestDto) throws ConstructionException {
        log.info("START --> OrderServiceImpl.createOrder()");
        Order savedOrder = entityRequestBuilder.convertOrderEntityToDto(orderRequestDto);
        log.info("END --> OrderServiceImpl.createOrder()");
        return entityResponseBuilder.convertOrderEntityToDto(savedOrder.getId());
    }

    @Override
    public CreateOrderResponseDto updateOrder(CreateOrderRequestDto OrderRequestDto) {
        return null;
    }

    @Override
    public List<CreateOrderResponseDto> getAllOrders() {
        log.info("START --> OrderServiceImpl.getAllOrders()");
        List<Order> Orders = orderRepository.findAll();
        log.info("END --> OrderServiceImpl.getAllOrders()");
        return Orders.stream()
                .map(
                        Order -> {
                            try {
                                return entityResponseBuilder.convertOrderEntityToDto(Order.getId());
                            } catch (ConstructionException e) {
                                throw new RuntimeException(e);
                            }
                        }
                ).toList();
    }

    @Override
    public ApiResponseDto deleteOrder(Long id) throws ConstructionException {
        log.info("START --> OrderServiceImpl.deleteOrder()");
        Order savedOrder = orderRepository.findById(id).orElseThrow(() -> new ConstructionException("Order not found with id: " + id));
        savedOrder.setIsDeleted(Boolean.TRUE);
        Order deletedOrder = orderRepository.save(savedOrder);
        log.info("END --> OrderServiceImpl.deleteOrder()");
        return new ApiResponseDto("SUCCESS", deletedOrder, "Order deleted successfully");
    }
}
