package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

    // this method adjusted to reflect the actual response without leading zeroes.
    @Test
    public void add() throws Exception {
    this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
        .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
        .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
        .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    // 3 added test cases to already implemented functionality
    @Test
    public void addDefaultOperand() throws Exception {
    this.mvc.perform(get("/add_json"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value("0"))  // Default should be 0
        .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value("0"))  // Default should be 0
        .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("0"))  // Expected result --> 0 + 0 = 0
        .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }


    @Test
    public void addWithZeroOperand() throws Exception {
    this.mvc.perform(get("/add_json").param("operand1", "0").param("operand2", "1010"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value("0"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value("1010"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("1010"))  // Expected result is  -->  ,0 + 1010 = 1010
        .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }


 // 2 x (3 operator) test cases added --> OR, AND, MULTIPLY
    @Test
    public void add2() throws Exception {
    this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value("111"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value("1010"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("10001"))  
        .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }


    @Test
    public void orOperation() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1", "101").param("operand2", "011"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value("101"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value("11"))  
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("111"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
    }

    @Test
    public void orOperationSecondCase() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1", "1001").param("operand2", "0011"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value("1011")); // Expected result --> 1001 | 0011 = 1011
    }

    @Test
    public void andOperation() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1", "101").param("operand2", "011"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value("101"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value("11")) 
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("1"))  // Expected result --> ("001"  "1")
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
    }

    @Test
    public void andOperationSecondCase() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1", "1111").param("operand2", "1100"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value("1100")); // Expected is --> 1111 & 1100 = 1100
    }

    @Test
    public void multiplyOperation() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1", "101").param("operand2", "11"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value("101"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value("11"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("1111"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multiply"));
    }

    @Test
    public void multiplyOperationSecondCase() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1", "11").param("operand2", "10"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value("110")); // Expected is -->  11 * 10 = 110
    }
}
