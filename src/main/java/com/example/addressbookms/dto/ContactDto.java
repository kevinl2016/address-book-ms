package com.example.addressbookms.dto;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {
    @NotNull(message = "id is mandatory")
    private UUID id;

    @NotBlank(message = "firstName is mandatory")
    private String firstName;

    private String lastName;

    @NotBlank(message = "phoneNumber is mandatory")
    private String phoneNumber;
}
