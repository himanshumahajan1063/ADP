package com.adp.coinChange.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CoinChangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPostMinimumCoins() throws Exception {
        BigDecimal amount = new BigDecimal("4.79");

        mockMvc.perform(post("/api/coins/change")
                        .content("4.79")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.coinChangeMap['0.25']").value(19))
                .andExpect(jsonPath("$.coinChangeMap.['0.01']").value(4));
    }

    @Test
    void testGetMinimumCoinsWithNoChange() throws Exception {
        BigDecimal amount = new BigDecimal("0.00");

        mockMvc.perform(post("/api/coins/change")
                        .content(amount.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("coinChangeMap").isEmpty());
    }

    @Test
    void testInvalidAmountParameter() throws Exception {
        mockMvc.perform(post("/api/coins/change")
                        .content("invalid")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("EC-3"))
                .andExpect(jsonPath("$.message").value("Failed to parse invalid amount"));
    }
}
