package com.constuction.dto.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CompanyDetailsResponseDto {
    private Long id;
    private String name;
    private Long numberOfEmployee;
    private Double annualRevenue;
    private String website;
    private LocalDate foundingDate;
    private String constructionType;
    private LocationDetailsResponseDto locationDetails;


}
