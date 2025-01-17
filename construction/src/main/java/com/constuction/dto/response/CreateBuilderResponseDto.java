package com.constuction.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CreateBuilderResponseDto {

    private Long id;
    private Double rate;
    private Double yearsOfExperience;
    private Boolean isAvailable;
    private CreateCompanyDetailsResponseDto companyDetails;
    private CreateBasicDetailsResponseDto basicDetails;
}
