package com.example.addressbookms.wrappers;

import java.util.List;

import com.example.addressbookms.domain.AddressBook;
import com.example.addressbookms.dto.AddressBookDto;
import com.example.addressbookms.dto.AddressBookUpdateDto;
import com.example.addressbookms.dto.NewAddressBookDto;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        uses = {ContactWrapper.class},
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressBookWrapper {

    AddressBook newToAddressBook(NewAddressBookDto newAddressBookDto);

    AddressBookUpdateDto createAddressBookUpdateDto(AddressBook addressBook);

    List<AddressBookDto> createAddressBookDtoList(List<AddressBook> addressBookList);

    AddressBookDto createAddressBookDto(AddressBook addressBook);
}
