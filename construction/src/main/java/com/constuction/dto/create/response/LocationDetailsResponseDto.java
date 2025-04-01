package com.constuction.dto.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LocationDetailsResponseDto {
    private Long id;
    private String State;
    private String city;
    private String pincode;
    private String address;
}
