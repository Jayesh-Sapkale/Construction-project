package com.constuction.controller;

import com.constuction.dto.ApiResponseDto;
import com.constuction.dto.request.create.CreateOrderRequestDto;
import com.constuction.dto.response.CreateOrderResponseDto;
import com.constuction.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    public final OrderService orderService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequestDto createOrderRequestDto) {
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            CreateOrderResponseDto OrderResponseDto = orderService.createOrder(createOrderRequestDto);
            responseDto.setStatus("SUCCESS");
            responseDto.setData(OrderResponseDto);
            responseDto.setMessage("Order created successfully with id: " + OrderResponseDto.getId());
        } catch (Exception e) {
            responseDto.setStatus("FAILURE");
            responseDto.setData(null);
            responseDto.setMessage("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            List<CreateOrderResponseDto> Orders = orderService.getAllOrders();
            responseDto.setStatus("SUCCESS");
            responseDto.setData(Orders);
            responseDto.setMessage(Orders.size() + " Orders details fetched successfully");
            if (Orders.isEmpty()){
                responseDto.setData(null);
                responseDto.setMessage("No Orders found");
            }
        } catch (Exception e) {
            responseDto.setStatus("FAILURE");
            responseDto.setData(null);
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
            responseDto.setMessage("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok(responseDto);
    }
}
