package com.example.addressbookms.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class NewAddressBookDto {
    @NotBlank(message = "userId is mandatory")
    private String userId;

    @NotBlank(message = "name is mandatory")
    private String name;
}
