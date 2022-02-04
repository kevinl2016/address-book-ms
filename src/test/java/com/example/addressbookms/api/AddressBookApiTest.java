package com.example.addressbookms.api;

import java.util.List;
import java.util.UUID;

import com.example.addressbookms.dto.AddressBookDto;
import com.example.addressbookms.dto.AddressBookUpdateDto;
import com.example.addressbookms.dto.ContactDto;
import com.example.addressbookms.dto.NewAddressBookDto;
import com.example.addressbookms.dto.NewContactDto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AddressBookApiTest {
    @Autowired private TestRestTemplate restTemplate;

    @LocalServerPort private int port;

    private String url;

    @BeforeEach
    public void setUp() {
        url = String.format("http://localhost:%d/%s/", port, "address-service/api/v1");
    }

    @Test
    void testCreateAddressBook() {
        NewAddressBookDto newAddressBookDto =
                NewAddressBookDto.builder().userId("test_id1").name("Test Address 1").build();
        ResponseEntity<AddressBookDto> postResponse =
                restTemplate.postForEntity(
                        url + "addressbook", newAddressBookDto, AddressBookDto.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    void getAddressBooK() {
        AddressBookDto response =
                restTemplate.getForObject(
                        url + "addressbook/12e96e96-3a1b-44f7-957e-ad9c6c061018",
                        AddressBookDto.class);
        assertNotNull(response);
        assertEquals(response.getName(), "Coles");
    }

    @Test
    void testUserAddressBook() {
        List<AddressBookDto> response =
                restTemplate.getForObject(url + "u0001/addressbook", List.class);
        assertNotNull(response);
    }

    @Test
    void updateAddressBook() {
        AddressBookUpdateDto addressBookUpdateDto =
                AddressBookUpdateDto.builder().userId("u0001").name("Coles-update").build();
        addressBookUpdateDto.setId(UUID.fromString("12e96e96-3a1b-44f7-957e-ad9c6c061018"));
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<AddressBookUpdateDto> requestUpdate =
                new HttpEntity<>(addressBookUpdateDto, headers);
        ResponseEntity<AddressBookDto> putResponse =
                restTemplate.exchange(
                        url + "addressbook", HttpMethod.PUT, requestUpdate, AddressBookDto.class);
        assertNotNull(putResponse);
        // assertEquals(putResponse.getBody().getName(), "Coles-update");
    }

    @Test
    void createContact() {
        NewContactDto newContactDto =
                NewContactDto.builder()
                        .firstName("Ken")
                        .lastName("White")
                        .phoneNumber("0401012012")
                        .build();
        ResponseEntity<AddressBookDto> postResponse =
                restTemplate.postForEntity(
                        url + "addressbook/12e96e96-3a1b-44f7-957e-ad9c6c061018/contact",
                        newContactDto,
                        AddressBookDto.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    void updateContact() {
        ContactDto contactDto =
                ContactDto.builder()
                        .firstName("Rose")
                        .lastName("White")
                        .phoneNumber("0401012012")
                        .build();
        contactDto.setId(UUID.fromString("917b21df-ff88-4105-aaf5-bb23c3320073"));
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ContactDto> requestUpdate = new HttpEntity<>(contactDto, headers);
        ResponseEntity<AddressBookDto> putResponse =
                restTemplate.exchange(
                        url + "addressbook/12e96e96-3a1b-44f7-957e-ad9c6c061018/contact",
                        HttpMethod.PUT,
                        requestUpdate,
                        AddressBookDto.class);
        assertNotNull(putResponse);
        assertNotNull(putResponse.getBody());
    }

    @Test
    void listContactByUserId() {
        List<ContactDto> response = restTemplate.getForObject(url + "u0001/contacts", List.class);
        assertNotNull(response);
    }

    @Test
    void deleteContact() {
        restTemplate.delete(url + "/addressbook/contact/917b21df-ff88-4105-aaf5-bb23c3320073");
    }

    @Test
    void deleteAddressBook() {
        restTemplate.delete(url + "/addressbook/12e96e96-3a1b-44f7-957e-ad9c6c061018");
    }
}
