package ru.itprogram.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testBookController() throws Exception {
        this.mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[]")));
    }

    @WithMockUser(username = "test", password = "test")
    @Test
    public void testAccessReaderController() throws Exception {
        this.mockMvc.perform(get("/readers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[]")));
    }

    @WithMockUser(username = "Administrator", password = "Administrator", roles = {"ADMIN"})
    @Test
    public void testAccessIssuedBookController() throws Exception {
        this.mockMvc.perform(get("/issuedBooks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[]")));
    }

    @WithMockUser(username = "test", password = "pass")
    @Test
    public void notAccessIssuedBookControllerWithBadCredentials() throws Exception {
        this.mockMvc.perform(get("/issuedBooks"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

}