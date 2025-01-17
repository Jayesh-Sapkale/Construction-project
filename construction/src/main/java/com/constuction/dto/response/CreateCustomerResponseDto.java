package com.constuction.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CreateCustomerResponseDto {
    private Long id;
    private CreateBasicDetailsResponseDto basicDetails;
}
