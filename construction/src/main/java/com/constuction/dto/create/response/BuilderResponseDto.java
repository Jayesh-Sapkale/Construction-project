package com.constuction.dto.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BuilderResponseDto {

    private Long id;
    private Double rate;
    private Double yearsOfExperience;
    private Boolean isAvailable;
    private CompanyDetailsResponseDto companyDetails;
    private BasicDetailsResponseDto basicDetails;
    private LocationDetailsResponseDto locationDetails;
}
