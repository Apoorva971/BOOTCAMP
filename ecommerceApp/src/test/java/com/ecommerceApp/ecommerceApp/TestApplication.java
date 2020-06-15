package com.ecommerceApp.ecommerceApp;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
public class TestApplication extends EcommerceAppApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
      @Before(value = "one")
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void testCustomer() throws Exception {
        mockMvc.perform(get("/customer")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.firstName").value("Daljit"))
                .andExpect(jsonPath("$.middleName").value("new"))
                .andExpect(jsonPath("$.lastName").value("kalsi")).
                andExpect(jsonPath("$.email").value("apoorva.garg@tothenew.com")).
                andExpect(jsonPath("$.contact").value("9837564567"));
    }
}

