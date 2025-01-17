package com.constuction.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CreateProjectResponseDto {

    private Long id;
    private CreateLocationDetailsResponseDto locationDetails;
    private CreateBuilderResponseDto builder;
    private String constructionType;
    private Double area;
    private String projectName;
    private String projectStatus;
    private Double estimatedPrice;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}
