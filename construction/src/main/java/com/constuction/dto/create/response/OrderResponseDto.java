package com.constuction.dto.create.response;

import com.constuction.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrderResponseDto {
    private Long id;
    private CreateCustomerResponseDto customer;
    private CreateProjectResponseDto project;

}
