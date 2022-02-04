package com.example.addressbookms.repositories;

import java.util.List;
import java.util.UUID;

import com.example.addressbookms.domain.AddressBook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBook, UUID> {
    List<AddressBook> findAddressBookByUserId(String userId);
}
