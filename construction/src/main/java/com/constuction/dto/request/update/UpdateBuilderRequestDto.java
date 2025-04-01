package com.constuction.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UpdateBuilderRequestDto {

    private Long id;
    private Double rate;
    private Double yearsOfExperience;
    private Boolean isAvailable;
    private UpdateCompanyDetailsRequestDto companyDetails;
    private UpdateBasicDetailsRequestDto basicDetails;
    private UpdateLocationDetailsRequestDto locationDetails;
}
