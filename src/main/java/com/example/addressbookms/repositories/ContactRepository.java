package com.example.addressbookms.repositories;

import java.util.List;
import java.util.UUID;

import com.example.addressbookms.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID> {

  @Query(
      value =
          "Select distinct new com.example.addressbookms.domain.Contact"
              + "(c.firstName, c.lastName, c.phoneNumber)"
              + " from Contact c inner join AddressBook a"
              + " on c.addressBook.id = a.id"
              + " Where a.userId = :userId")
  List<Contact> findContactByUserId(String userId);

}
