package com.constuction.controller;

import com.constuction.dto.ApiResponseDto;
import com.constuction.dto.request.create.CreateCustomerRequestDto;
import com.constuction.dto.response.CreateCustomerResponseDto;
import com.constuction.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    public final CustomerService customerService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> createCustomer(@RequestBody CreateCustomerRequestDto createCustomerRequestDto) {
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            CreateCustomerResponseDto CustomerResponseDto = customerService.createCustomer(createCustomerRequestDto);
            responseDto.setStatus("SUCCESS");
            responseDto.setData(CustomerResponseDto);
            responseDto.setMessage("Customer created successfully with id: " + CustomerResponseDto.getId());
        } catch (Exception e) {
            responseDto.setStatus("FAILURE");
            responseDto.setData(null);
            responseDto.setMessage("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        ApiResponseDto responseDto = new ApiResponseDto();
        try {
            List<CreateCustomerResponseDto> Customers = customerService.getAllCustomers();
            responseDto.setStatus("SUCCESS");
            responseDto.setData(Customers);
            responseDto.setMessage(Customers.size() + " Customers details fetched successfully");
            if (Customers.isEmpty()){
                responseDto.setData(null);
                responseDto.setMessage("No Customers found");
            }
        } catch (Exception e) {
            responseDto.setStatus("FAILURE");
            responseDto.setData(null);
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
            responseDto.setMessage("An error occurred: " + e.getMessage());
        }
        return ResponseEntity.ok(responseDto);
    }
}
