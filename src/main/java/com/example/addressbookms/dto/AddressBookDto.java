package com.example.addressbookms.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressBookDto {
    private UUID id;
    private String userId;
    private String name;
    private List<ContactDto> contactList;
}
