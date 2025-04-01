package com.constuction.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UpdateCompanyDetailsRequestDto {
    private Long id;
    private String name;
    private Long numberOfEmployee;
    private Double annualRevenue;
    private String website;
    private LocalDate foundingDate;
    private String constructionType;
    private UpdateLocationDetailsRequestDto locationDetails;


}
