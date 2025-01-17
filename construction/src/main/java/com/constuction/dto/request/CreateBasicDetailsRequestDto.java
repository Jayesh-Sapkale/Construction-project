package com.constuction.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CreateBasicDetailsRequestDto {

    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private LocalDate dob;
    private String role;
    private String gender;
    private CreateLocationDetailsRequestDto locationDetails;
}
