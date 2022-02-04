package com.example.addressbookms.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.addressbookms.domain.AddressBook;
import com.example.addressbookms.domain.Contact;
import com.example.addressbookms.dto.AddressBookDto;
import com.example.addressbookms.dto.AddressBookUpdateDto;
import com.example.addressbookms.dto.NewAddressBookDto;
import com.example.addressbookms.repositories.AddressBookRepository;
import com.example.addressbookms.services.AddressBookService;
import com.example.addressbookms.wrappers.AddressBookWrapper;

import org.assertj.core.util.Lists;
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
class AddressBookServiceImplTest {

    @Mock private AddressBookRepository addressBookRepository;

    private AddressBookService addressBookService;

    @Autowired private AddressBookWrapper addressBookWrapper;

    @Mock private NewAddressBookDto requestBean;

    @Mock private AddressBookUpdateDto updateBean;

    @BeforeEach
    void setUp() {
        addressBookService = new AddressBookServiceImpl(addressBookRepository, addressBookWrapper);
    }

    @Test
    void createAddressBook() {
        AddressBook addressBook =
                AddressBook.builder().userId("test_id1").name("Test Address 1").build();
        when(addressBookRepository.save(any(AddressBook.class))).thenReturn(addressBook);
        AddressBookUpdateDto responseBean = addressBookService.createAddressBook(requestBean);
        assertEquals(responseBean.getName(), "Test Address 1");
    }

    @Test
    void updateAddressBook() {
        UUID id = UUID.randomUUID();
        when(updateBean.getId()).thenReturn(id);
        AddressBook addressBook =
                AddressBook.builder().userId("test_id1").name("Test Address 1").build();
        when(addressBookRepository.findById(id)).thenReturn(Optional.of(addressBook));
        AddressBook updated =
                AddressBook.builder().userId("test_id1").name("Test Address 2").build();
        when(addressBookRepository.save(addressBook)).thenReturn(updated);
        AddressBookUpdateDto responseBean = addressBookService.updateAddressBook(updateBean);
        assertEquals(responseBean.getName(), "Test Address 2");
    }

    @Test
    void listAddressBook() {
        List<Contact> contactList = Lists.newArrayList();
        AddressBook addressBook =
                AddressBook.builder().userId("test_id1").name("Test Address 1").build();
        contactList.add(
                Contact.builder()
                        .firstName("c1")
                        .phoneNumber("010123")
                        .addressBook(addressBook)
                        .build());
        contactList.add(
                Contact.builder()
                        .firstName("c2")
                        .phoneNumber("110123")
                        .addressBook(addressBook)
                        .build());
        addressBook.setContactList(contactList);
        List<Contact> contactList1 = Lists.newArrayList();
        AddressBook addressBook1 =
                AddressBook.builder().userId("test_id1").name("Test Address 1").build();
        contactList1.add(
                Contact.builder()
                        .firstName("c11")
                        .phoneNumber("1010123")
                        .addressBook(addressBook)
                        .build());
        contactList1.add(
                Contact.builder()
                        .firstName("c12")
                        .phoneNumber("1110123")
                        .addressBook(addressBook)
                        .build());
        addressBook1.setContactList(contactList1);
        List<AddressBook> addressBookList = Arrays.asList(addressBook, addressBook1);
        when(addressBookRepository.findAddressBookByUserId("test_id1")).thenReturn(addressBookList);
        List<AddressBookDto> addressBookDtos = addressBookService.listAddressBook("test_id1");
        assertEquals(addressBookDtos.size(), 2);
        assertEquals(addressBookDtos.get(1).getName(), "Test Address 1");
    }
}
