package com.example.addressbookms.wrappers;

import java.util.List;

import com.example.addressbookms.domain.Contact;
import com.example.addressbookms.dto.ContactDto;
import com.example.addressbookms.dto.NewContactDto;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContactWrapper {

    Contact createContactFromNewDto(NewContactDto contactBean);

    ContactDto createContactDto(Contact contact);

    List<ContactDto> createContactDtoList(List<Contact> contacts);
}
