package com.example.addressbookms.services.impl;

import java.util.List;
import java.util.UUID;

import com.example.addressbookms.domain.AddressBook;
import com.example.addressbookms.dto.AddressBookDto;
import com.example.addressbookms.dto.AddressBookUpdateDto;
import com.example.addressbookms.dto.NewAddressBookDto;
import com.example.addressbookms.repositories.AddressBookRepository;
import com.example.addressbookms.services.AddressBookService;
import com.example.addressbookms.wrappers.AddressBookWrapper;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl implements AddressBookService {

    private final AddressBookRepository addressBookRepository;
    private final AddressBookWrapper addressBookWrapper;

    @Autowired
    public AddressBookServiceImpl(
            final AddressBookRepository addressBookRepository,
            final AddressBookWrapper addressBookWrapper) {
        this.addressBookRepository = addressBookRepository;
        this.addressBookWrapper = addressBookWrapper;
    }

    /**
     * @param addressBookBean
     * @return
     */
    @Override
    public AddressBookUpdateDto createAddressBook(final NewAddressBookDto addressBookBean) {
        AddressBook addressBook = addressBookWrapper.newToAddressBook(addressBookBean);
        AddressBook saved = addressBookRepository.save(addressBook);
        return addressBookWrapper.createAddressBookUpdateDto(saved);
    }

    @Override
    public AddressBookUpdateDto updateAddressBook(final AddressBookUpdateDto addressBookBean) {
        AddressBook addressBook =
                addressBookRepository
                        .findById(addressBookBean.getId())
                        .orElseThrow(
                                () ->
                                        new ObjectNotFoundException(
                                                addressBookBean.getId(), "AddressBook"));

        addressBook.setName(addressBookBean.getName());
        AddressBook updated = addressBookRepository.save(addressBook);
        return addressBookWrapper.createAddressBookUpdateDto(updated);
    }

    @Override
    public List<AddressBookDto> listAddressBook(final String userId) {
        List<AddressBook> addressBooks = addressBookRepository.findAddressBookByUserId(userId);
        return addressBookWrapper.createAddressBookDtoList(addressBooks);
    }

    @Override
    public void deleteAddressBook(final UUID id) {
        addressBookRepository.deleteById(id);
    }

    @Override
    public AddressBookDto getAddressBook(final UUID uuid) {
        AddressBook addressBook =
                addressBookRepository
                        .findById(uuid)
                        .orElseThrow(() -> new ObjectNotFoundException(uuid, "AddressBook"));
        return addressBookWrapper.createAddressBookDto(addressBook);
    }
}
