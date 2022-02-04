package com.example.addressbookms.repositories;

import java.util.List;

import com.example.addressbookms.domain.AddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
class AddressBookRepositoryTest {

    @Autowired private TestEntityManager entityManager;

    @Autowired private AddressBookRepository addressBookRepository;

    @BeforeEach
    void setUp() {
        AddressBook addressBook =
                AddressBook.builder().name("test address book").userId("testUserId").build();
        entityManager.persist(addressBook);
    }

    @Test
    void findAddressBookByUserId() {
        List<AddressBook> addressBookList =
                addressBookRepository.findAddressBookByUserId("testUserId");
        assertEquals(addressBookList.size(), 1);
        assertEquals(addressBookList.get(0).getName(), "test address book");
    }
}
