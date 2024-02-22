package dev.crain.webfilterbug;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WebfilterBugApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void itShould_NotCallTheFilters() throws Exception {
        WebfilterBugApplication.SimpleFilterOne.CALLED = false;
        WebfilterBugApplication.SimpleFilterTwo.CALLED = false;
        WebfilterBugApplication.SimpleFilterThree.CALLED = false;
        mockMvc.perform(get("/rest/simple"))
                .andExpect(status().isOk())
                .andExpect(content().bytes("Hello World".getBytes()));
        Assertions.assertAll(
                () -> Assertions.assertFalse(WebfilterBugApplication.SimpleFilterOne.CALLED),
                () -> Assertions.assertFalse(WebfilterBugApplication.SimpleFilterTwo.CALLED),
                () -> Assertions.assertFalse(WebfilterBugApplication.SimpleFilterThree.CALLED)
        );
    }

}
