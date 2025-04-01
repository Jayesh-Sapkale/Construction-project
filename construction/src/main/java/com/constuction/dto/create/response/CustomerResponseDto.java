package com.constuction.dto.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CustomerResponseDto {
    private Long id;
    private BasicDetailsResponseDto basicDetails;
    private LocationDetailsResponseDto locationDetails;

}
