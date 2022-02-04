package com.example.addressbookms.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewContactDto {
    @NotBlank(message = "firstName is mandatory")
    private String firstName;

    private String lastName;

    @NotBlank(message = "phoneNumber is mandatory")
    private String phoneNumber;
}
