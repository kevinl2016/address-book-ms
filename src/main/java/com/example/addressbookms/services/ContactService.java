package com.example.addressbookms.services;

import java.util.List;
import java.util.UUID;

import com.example.addressbookms.dto.AddressBookDto;
import com.example.addressbookms.dto.ContactDto;
import com.example.addressbookms.dto.NewContactDto;

import org.springframework.transaction.annotation.Transactional;

public interface ContactService {
    @Transactional
    AddressBookDto addContact(UUID addressBookId, NewContactDto contactBean);

    @Transactional
    AddressBookDto updateContact(UUID addressBookId, ContactDto contactBean);

    @Transactional(readOnly = true)
    List<ContactDto> listContactByUserId(String userId);

    @Transactional
    void deleteContact(UUID id);
}
