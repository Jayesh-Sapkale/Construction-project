package com.constuction.dto.response;

import com.constuction.dto.request.CreateBasicDetailsRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CreateAdminResponseDto {
    private Long id;
    private CreateBasicDetailsRequestDto basicDetails;

}
