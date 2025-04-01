package com.constuction.dto.update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UpdateAdminRequestDto {
    private Long id;
    private UpdateBasicDetailsRequestDto basicDetails;
    private UpdateLocationDetailsRequestDto locationDetails;

}
