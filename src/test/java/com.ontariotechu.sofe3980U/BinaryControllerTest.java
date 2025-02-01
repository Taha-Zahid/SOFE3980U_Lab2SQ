package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
	
	@Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }
	@Test
	    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }
 // Adding 3 test cases for binary web application
    @Test
        public void getDefaultOperand1Focused() throws Exception {
         this.mvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
            .andExpect(model().attribute("operand1Focused", false)); // the original is false
    }
 
    @Test
        public void getParameterOperand1Focused() throws Exception {
        this.mvc.perform(get("/").param("operand1", "1101"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
            .andExpect(model().attribute("operand1", "1101"))
            .andExpect(model().attribute("operand1Focused", true)); // when operand 1 is provided this to set to true
    }
 
    @Test
        public void postInvalidOperator() throws Exception {
        this.mvc.perform(post("/").param("operand1", "101").param("operator", "-").param("operand2", "10"))
            .andExpect(status().isOk())
            .andExpect(content().string(org.hamcrest.Matchers.containsString("Error: Invalid operator"))); // checks inside the html for text
    }


    // Test Cases for Operators (2 per)
    @Test
        public void postOrOperation() throws Exception {
        this.mvc.perform(post("/").param("operand1", "101").param("operator", "|").param("operand2", "011"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "111")) 
            .andExpect(model().attribute("operand1", "101"));
    }

    @Test
        public void postOrOperationSecondCase() throws Exception {
        this.mvc.perform(post("/").param("operand1", "1100").param("operator", "|").param("operand2", "0011"))
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1111")) // Expected --> 1100 | 0011 = 1111
        .andExpect(model().attribute("operand1", "1100"));
    }

    @Test
        public void postAndOperation() throws Exception {
        this.mvc.perform(post("/").param("operand1", "101").param("operator", "&").param("operand2", "011"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1")) 
            .andExpect(model().attribute("operand1", "101"));
    }
    @Test
        public void postAndOperationSecondCase() throws Exception {
        this.mvc.perform(post("/").param("operand1", "1100").param("operator", "&").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1000")) // Expected --> 1100 & 1010 = 1000
            .andExpect(model().attribute("operand1", "1100"));
    }

    @Test
        public void postMultiplyOperation() throws Exception {
        this.mvc.perform(post("/").param("operand1", "101").param("operator", "*").param("operand2", "11"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1111")) 
            .andExpect(model().attribute("operand1", "101"));
    }

    @Test
        public void postMultiplyOperationSecondCase() throws Exception {
        this.mvc.perform(post("/").param("operand1", "11").param("operator", "*").param("operand2", "10"))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "110")) // Expected --> 11 * 10 = 110
            .andExpect(model().attribute("operand1", "11"));
    }


}