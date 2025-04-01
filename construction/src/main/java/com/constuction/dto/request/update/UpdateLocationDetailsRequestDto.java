package com.constuction.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UpdateLocationDetailsRequestDto {
    private Long id;
    private String State;
    private String city;
    private String pincode;
    private String address;
}
