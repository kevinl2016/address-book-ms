package com.example.addressbookms.services;

import java.util.List;
import java.util.UUID;

import com.example.addressbookms.dto.AddressBookDto;
import com.example.addressbookms.dto.AddressBookUpdateDto;
import com.example.addressbookms.dto.NewAddressBookDto;

import org.springframework.transaction.annotation.Transactional;

public interface AddressBookService {
    @Transactional
    AddressBookUpdateDto createAddressBook(NewAddressBookDto newAddressBookDto);

    @Transactional
    AddressBookUpdateDto updateAddressBook(AddressBookUpdateDto addressBookUpdateDto);

    @Transactional(readOnly = true)
    List<AddressBookDto> listAddressBook(String userId);

    @Transactional
    void deleteAddressBook(UUID id);

    @Transactional(readOnly = true)
    AddressBookDto getAddressBook(UUID uuid);
}
