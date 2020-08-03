package ru.itprogram.controller.integration;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.Assert.assertEquals;
import static ru.itprogram.controller.utils.TestDataFactory.*;
import static ru.itprogram.controller.utils.TestDataFactory.DTO_READER_JSON;

@DirtiesContext
public class LibraryIntegrationTestIt extends AbstractIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testSpringBootContext() {

    }

    @Test
    public void testAddBookInDb() throws Exception {
        //given
        String requestBody = DTO_BOOK_JSON;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //when
        ResponseEntity<HttpStatus> response = testRestTemplate
                .exchange("/books", HttpMethod.POST,
                        new HttpEntity<>(requestBody, headers), HttpStatus.class);

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testAddReaderInDb() {

        //given
        String requestBody = DTO_READER_JSON;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //when
        ResponseEntity<HttpStatus> response = testRestTemplate
                .withBasicAuth("Administrator", "Administrator")
                .exchange("/readers", HttpMethod.POST,
                        new HttpEntity<>(requestBody, headers), HttpStatus.class);

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    public void testAddUserAndUserAddReader() {
        //given
        String requestBodyUser = DTO_USER_JSON;
        String requestBodyReader = DTO_READER_JSON;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //when
        ResponseEntity<HttpStatus> responseNewUser = testRestTemplate
                .exchange("/registration", HttpMethod.POST,
                        new HttpEntity<>(requestBodyUser, headers), HttpStatus.class);

        ResponseEntity<HttpStatus> responseNewReader = testRestTemplate
                .withBasicAuth("testuser", "testuser")
                .exchange("/readers", HttpMethod.POST,
                        new HttpEntity<>(requestBodyReader, headers), HttpStatus.class);

        //then
        assertEquals(HttpStatus.OK, responseNewUser.getStatusCode());
        assertEquals(HttpStatus.OK, responseNewReader.getStatusCode());
    }

    @Test
    public void testNotAuthorityAccessDenied() {
        //given
        String requestBody = DTO_READER_JSON;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //when
        ResponseEntity<HttpStatus> response = testRestTemplate
                .withBasicAuth("", "")
                .exchange("/readers", HttpMethod.POST,
                        new HttpEntity<>(requestBody, headers), HttpStatus.class);

        //then
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }


}
