package com.example.addressbookms.repositories;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.example.addressbookms.domain.AddressBook;
import com.example.addressbookms.domain.Contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
class ContactRepositoryTest {
    @Autowired private TestEntityManager entityManager;

    @Autowired private ContactRepository contactRepository;

    private AddressBook addressBook;

    @BeforeEach
    void setUp() {
        addressBook = AddressBook.builder().name("test address book").userId("testUserId").build();
        Contact contact1 =
                Contact.builder()
                        .firstName("Kevin")
                        .phoneNumber("0400123123")
                        .addressBook(addressBook)
                        .build();
        Contact contact2 =
                Contact.builder()
                        .firstName("Kevin")
                        .phoneNumber("0400123124")
                        .addressBook(addressBook)
                        .build();
        addressBook.setContactList(Arrays.asList(new Contact[] {contact1, contact2}));
        entityManager.persist(addressBook);
        AddressBook addressBook2 =
                AddressBook.builder().name("test address book2").userId("testUserId2").build();
        Contact contact3 =
                Contact.builder()
                        .firstName("Ken")
                        .phoneNumber("0410123124")
                        .addressBook(addressBook2)
                        .build();
        addressBook.setContactList(Collections.singletonList(contact3));
        entityManager.persist(addressBook2);
    }

    @Test
    void findContactByUserId() {
        List<Contact> contactList = contactRepository.findContactByUserId("testUserId");
        assertEquals(contactList.size(), 2);
    }
}
