package com.constuction.dto;

import com.constuction.enums.ErroCodes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ApiResponseDto {
    private String status;
    private Object data;
    private String message;
    private ErroCodes erroCodes;
}
