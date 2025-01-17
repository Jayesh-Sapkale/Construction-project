package com.constuction.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CreateBuilderRequestDto {

    private Double rate;
    private Double yearsOfExperience;
    private Boolean isAvailable;
    private CreateCompanyDetailsRequestDto companyDetails;
    private CreateBasicDetailsRequestDto basicDetails;
}
