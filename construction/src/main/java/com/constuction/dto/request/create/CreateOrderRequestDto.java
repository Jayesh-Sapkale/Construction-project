package com.constuction.dto.request.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CreateOrderRequestDto {

    private Long id;

    private CreateCustomerRequestDto createCustomerRequestDto;

    private CreateProjectRequestDto createProjectRequestDto;

}
