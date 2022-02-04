package com.example.addressbookms.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.example.addressbookms.dto.AddressBookDto;
import com.example.addressbookms.dto.AddressBookUpdateDto;
import com.example.addressbookms.dto.ContactDto;
import com.example.addressbookms.dto.NewAddressBookDto;
import com.example.addressbookms.dto.NewContactDto;
import com.example.addressbookms.services.AddressBookService;
import com.example.addressbookms.services.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(
        value = "Address Book API endpoints",
        tags = {"Address Book API Endpoints"})
@RestController
@RequestMapping("/api/v1")
public class AddressBookController {
    private final AddressBookService addressBookService;
    private final ContactService contactService;

    @Autowired
    public AddressBookController(
            final AddressBookService addressBookService, final ContactService contactService) {
        this.addressBookService = addressBookService;
        this.contactService = contactService;
    }

    /**
     * @param userId
     * @return
     */
    @ApiOperation(value = "Get all address book of a user")
    @GetMapping("/{userId}/addressbook")
    public List<AddressBookDto> listAddressBookByUserId(
            @PathVariable(value = "userId") final String userId) {
        return addressBookService.listAddressBook(userId);
    }

    @ApiOperation(value = "Create new address book")
    @PostMapping("/addressbook")
    public AddressBookUpdateDto createAddressBook(
            @RequestBody @Valid final NewAddressBookDto newAddressBookDto) {
        return addressBookService.createAddressBook(newAddressBookDto);
    }

    @ApiOperation(value = "Update address book")
    @PutMapping("/addressbook")
    public AddressBookUpdateDto updateAddressBook(
            @RequestBody @Valid final AddressBookUpdateDto addressBookUpdateDto) {
        return addressBookService.updateAddressBook(addressBookUpdateDto);
    }

    @ApiOperation(value = "Get an address book and its contacts ")
    @GetMapping("/addressbook/{uuid}")
    public AddressBookDto getAddressBooK(@PathVariable(value = "uuid") final UUID uuid) {
        return addressBookService.getAddressBook(uuid);
    }

    @ApiOperation(value = "Delete an address book ")
    @DeleteMapping("/addressbook/{uuid}")
    public ResponseEntity<Object> deleteAddressBoo(@PathVariable(value = "uuid") final UUID uuid) {
        addressBookService.deleteAddressBook(uuid);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Create new contact of an address book")
    @PostMapping("/addressbook/{uuid}/contact")
    public AddressBookDto createContact(
            @PathVariable(value = "uuid") final UUID uuid,
            @RequestBody @Valid final NewContactDto newContactDto) {
        return contactService.addContact(uuid, newContactDto);
    }

    @ApiOperation(value = "Create new contact of an address book")
    @PutMapping("/addressbook/{uuid}/contact")
    public AddressBookDto updateContact(
            @PathVariable(value = "uuid") final UUID uuid,
            @RequestBody @Valid final ContactDto contactDto) {
        return contactService.updateContact(uuid, contactDto);
    }

    @ApiOperation(value = "Create new contact of an address book")
    @DeleteMapping("/addressbook/contact/{uuid}")
    public ResponseEntity<Object> deleteContact(@PathVariable(value = "uuid") final UUID uuid) {
        contactService.deleteContact(uuid);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Get all contacts of a user")
    @GetMapping("/{userId}/contacts")
    public List<ContactDto> listContactByUserId(
            @PathVariable(value = "userId") final String userId) {
        return contactService.listContactByUserId(userId);
    }
}
