package com.example.striderbackend.controller;
import com.example.striderbackend.StriderBackendApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = StriderBackendApplication.class)
@AutoConfigureMockMvc
public class ActivityControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void list_ok() throws Exception {
        mvc.perform(get("/api/v1/activities")).andExpect(status().isOk());
    }
}
