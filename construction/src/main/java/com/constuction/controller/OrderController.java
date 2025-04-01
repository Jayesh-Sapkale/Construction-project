package com.constuction.controller;

import com.constuction.dto.response.ApiResponseDto;
import com.constuction.dto.request.create.CreateOrderRequestDto;
import com.constuction.dto.response.CreateOrderResponseDto;
import com.constuction.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    public final OrderService orderService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequestDto createOrderRequestDto) {
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            CreateOrderResponseDto orderResponseDto = orderService.createOrder(createOrderRequestDto);
            responseDto.setStatus("SUCCESS");
            responseDto.setData(orderResponseDto);
            log.info("Order created successfully with id: {}", orderResponseDto.getId());
            responseDto.setMessage("Order created successfully with id: " + orderResponseDto.getId());
        } catch (Exception e) {
            responseDto.setStatus("FAILURE");
            responseDto.setData(null);
            log.error(e.getMessage());
            responseDto.setMessage("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            List<CreateOrderResponseDto> orders = orderService.getAllOrders();
            responseDto.setStatus("SUCCESS");
            responseDto.setData(orders);
            log.info("{} Orders details fetched successfully", orders.size());
            responseDto.setMessage(orders.size() + " Orders details fetched successfully");
            if (orders.isEmpty()) {
                responseDto.setData(null);
                log.info("No orders found");
                responseDto.setMessage("No orders found");
            }
        } catch (Exception e) {
            responseDto.setStatus("FAILURE");
            responseDto.setData(null);
            log.error(e.getMessage());
            responseDto.setMessage("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deletedOrder(@PathVariable Long id) {
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            responseDto = orderService.deleteOrder(id);
        } catch (Exception e) {
            responseDto.setStatus("FAILURE");
            responseDto.setData(null);
            log.error(e.getMessage());
            responseDto.setMessage("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok(responseDto);
    }
}
