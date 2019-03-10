package com.cherentsov.mapback.Controller;

import com.cherentsov.mapback.Model.Country;
import com.cherentsov.mapback.Service.DBService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(WebController.class)
public class WebControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DBService dBService;

    @Test
    public void map() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/map")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        // verify
        int status = result.getResponse().getStatus();
        assertEquals("Неправильный код возврата", HttpStatus.OK.value(), status);

        // verify that service method was called once
        verify(dBService).findByName(Country.class, any(String.class));

        /*Employee resultEmployee = TestUtils.jsonToObject(result.getResponse()
                .getContentAsString(), Employee.class);
        assertNotNull(resultEmployee);
        assertEquals(1l, resultEmployee.getId().longValue());*/
    }

    @Test
    public void index() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/")
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        // verify
        int status = result.getResponse().getStatus();
        assertEquals("Неправильный код возврата", HttpStatus.OK.value(), status);
    }
}