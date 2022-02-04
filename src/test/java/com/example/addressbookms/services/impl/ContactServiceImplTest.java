package com.example.addressbookms.services.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.WARN)
class ContactServiceImplTest {

    @Mock private AddressBookRepository addressBookRepository;
    @Mock private ContactRepository contactRepository;

    private ContactService contactService;

    @Autowired private AddressBookWrapper addressBookWrapper;

    @Autowired private ContactWrapper contactWrapper;

    @BeforeEach
    void setUp() {
        contactService =
                new ContactServiceImpl(
                        addressBookRepository,
                        contactRepository,
                        addressBookWrapper,
                        contactWrapper);
    }

    @Test
    void addContact() {
        UUID id = UUID.randomUUID();
        Contact oldContact = Contact.builder().firstName("AAA").phoneNumber("041231234").build();
        Contact newContact = Contact.builder().firstName("BBB").phoneNumber("041231234").build();
        AddressBook addressBook =
                AddressBook.builder().userId("test_id1").name("Test Address 1").build();
        addressBook.setId(id);
        oldContact.setAddressBook(addressBook);
        NewContactDto newContactDto =
                NewContactDto.builder().firstName("BBB").phoneNumber("041231234").build();
        addressBook.setContactList(Collections.singletonList(oldContact));
        when(addressBookRepository.findById(id)).thenReturn(Optional.of(addressBook));
        when(addressBookRepository.save(any(AddressBook.class))).thenReturn(addressBook);
        AddressBookDto responseBean = contactService.addContact(id, newContactDto);
        assertEquals(responseBean.getName(), "Test Address 1");
        assertEquals(responseBean.getContactList().size(), 2);
    }

    @Test
    void updateContact() {
        UUID id = UUID.randomUUID();
        UUID contactId = UUID.randomUUID();
        Contact contact = Contact.builder().firstName("AAA").phoneNumber("041231234").build();
        contact.setId(contactId);
        AddressBook addressBook =
                AddressBook.builder().userId("test_id1").name("Test Address 1").build();
        addressBook.setId(id);
        contact.setAddressBook(addressBook);
        ContactDto contactDto =
                ContactDto.builder()
                        .id(contactId)
                        .firstName("BBB")
                        .phoneNumber("041231234")
                        .build();
        addressBook.setContactList(Collections.singletonList(contact));
        when(addressBookRepository.findById(id)).thenReturn(Optional.of(addressBook));
        when(addressBookRepository.save(any(AddressBook.class))).thenReturn(addressBook);
        AddressBookDto responseBean = contactService.updateContact(id, contactDto);
        assertEquals(responseBean.getName(), "Test Address 1");
        assertEquals(responseBean.getContactList().size(), 1);
        assertEquals(responseBean.getContactList().get(0).getFirstName(), "BBB");
    }

    @Test
    void listContactByUserId() {
        Contact contact1 = Contact.builder().firstName("AAA").phoneNumber("040231234").build();
        Contact contact2 = Contact.builder().firstName("BBB").phoneNumber("041231234").build();
        List<Contact> contactList = Arrays.asList(contact1, contact2);
        when(contactRepository.findContactByUserId(any(String.class))).thenReturn(contactList);
        List<ContactDto> contactDtos = contactService.listContactByUserId("test1");
        assertEquals(contactDtos.size(), 2);
        assertEquals(contactDtos.get(0).getFirstName(), "AAA");
    }
}
