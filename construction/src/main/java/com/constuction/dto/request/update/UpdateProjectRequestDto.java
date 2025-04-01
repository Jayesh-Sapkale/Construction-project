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
public class UpdateProjectRequestDto {

    private Long id;
    private UpdateLocationDetailsRequestDto locationDetails;
    private UpdateBuilderRequestDto builder;
    private String constructionType;
    private Double area;
    private String projectName;
    private String projectStatus;
    private Double estimatedPrice;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}
