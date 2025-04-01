package com.constuction.controller;

import com.constuction.dto.response.ApiResponseDto;
import com.constuction.dto.request.create.CreateCustomerRequestDto;
import com.constuction.dto.response.CreateCustomerResponseDto;
import com.constuction.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    public final CustomerService customerService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createCustomer(@RequestBody CreateCustomerRequestDto createCustomerRequestDto) {
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            CreateCustomerResponseDto customerResponseDto = customerService.createCustomer(createCustomerRequestDto);
            responseDto.setStatus("SUCCESS");
            responseDto.setData(customerResponseDto);
            log.info("Customer created successfully with id: {}", customerResponseDto.getId());
            responseDto.setMessage("Customer created successfully with id: " + customerResponseDto.getId());
        } catch (Exception e) {
            responseDto.setStatus("FAILURE");
            responseDto.setData(null);
            log.error(e.getMessage());
            responseDto.setMessage("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            List<CreateCustomerResponseDto> customers = customerService.getAllCustomers();
            responseDto.setStatus("SUCCESS");
            responseDto.setData(customers);
            responseDto.setMessage(customers.size() + " Customers details fetched successfully");
            if (customers.isEmpty()) {
                responseDto.setData(null);
                log.info("No customers found");
                responseDto.setMessage("No customers found");
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
    public ResponseEntity<?> deletedCustomer(@PathVariable Long id) {
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            responseDto = customerService.deleteCustomer(id);
        } catch (Exception e) {
            responseDto.setStatus("FAILURE");
            responseDto.setData(null);
            log.error(e.getMessage());
            responseDto.setMessage("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok(responseDto);
    }
}
