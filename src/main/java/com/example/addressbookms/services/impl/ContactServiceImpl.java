package com.example.addressbookms.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.addressbookms.domain.AddressBook;
import com.example.addressbookms.domain.Contact;
import com.example.addressbookms.dto.AddressBookDto;
import com.example.addressbookms.dto.ContactDto;
import com.example.addressbookms.dto.NewContactDto;
import com.example.addressbookms.repositories.AddressBookRepository;
import com.example.addressbookms.repositories.ContactRepository;
import com.example.addressbookms.services.ContactService;
import com.example.addressbookms.wrappers.AddressBookWrapper;
import com.example.addressbookms.wrappers.ContactWrapper;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ContactServiceImpl implements ContactService {
    private final AddressBookRepository addressBookRepository;
    private final ContactRepository contactRepository;
    private final AddressBookWrapper addressBookWrapper;
    private final ContactWrapper contactWrapper;

    @Autowired
    public ContactServiceImpl(
            final AddressBookRepository addressBookRepository,
            final ContactRepository contactRepository,
            final AddressBookWrapper addressBookWrapper,
            final ContactWrapper contactWrapper) {
        this.addressBookRepository = addressBookRepository;
        this.contactRepository = contactRepository;
        this.addressBookWrapper = addressBookWrapper;
        this.contactWrapper = contactWrapper;
    }

    @Override
    public AddressBookDto addContact(final UUID addressBookId, final NewContactDto contactBean) {
        AddressBook addressBook =
                addressBookRepository
                        .findById(addressBookId)
                        .orElseThrow(
                                () -> new ObjectNotFoundException(addressBookId, "AddressBook"));
        Contact contact = contactWrapper.createContactFromNewDto(contactBean);
        contact.setAddressBook(addressBook);
        List<Contact> contactList =
                addressBook.getContactList() == null
                        ? new ArrayList<>()
                        : new ArrayList<>(addressBook.getContactList());
        contactList.add(contact);
        addressBook.setContactList(contactList);
        addressBookRepository.save(addressBook);
        return addressBookWrapper.createAddressBookDto(addressBook);
    }

    @Override
    public AddressBookDto updateContact(final UUID addressBookId, final ContactDto contactBean) {
        AddressBook addressBook =
                addressBookRepository
                        .findById(addressBookId)
                        .orElseThrow(
                                () -> new ObjectNotFoundException(addressBookId, "AddressBook"));
        List<Contact> contactList = addressBook.getContactList();
        if (CollectionUtils.isEmpty(contactList)) {
            throw new ObjectNotFoundException(contactBean.getId(), "Contact");
        }
        Contact contact =
                contactList.stream()
                        .filter(c -> c.getId().equals(contactBean.getId()))
                        .findAny()
                        .orElseThrow(
                                () -> new ObjectNotFoundException(contactBean.getId(), "Contact"));
        contact.setFirstName(contactBean.getFirstName());
        contact.setLastName(contactBean.getLastName());
        contact.setPhoneNumber(contactBean.getPhoneNumber());
        addressBookRepository.save(addressBook);
        return addressBookWrapper.createAddressBookDto(addressBook);
    }

    @Override
    public List<ContactDto> listContactByUserId(final String userId) {
        List<Contact> contactList = contactRepository.findContactByUserId(userId);
        return contactWrapper.createContactDtoList(contactList);
    }

    @Override
    public void deleteContact(final UUID id) {
        contactRepository.deleteById(id);
    }
}
