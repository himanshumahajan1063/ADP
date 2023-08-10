package com.adp.coinChange.service;

import com.adp.coinChange.model.CoinChangeResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoinChangeServiceTest {

    @InjectMocks
    private CoinChangeService coinChangeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Verify that empty message is returned when there is zero amount")
    void verify_empty_response() {
        CoinChangeResponseDto coinChangeResponseDto = coinChangeService.getChange("0");

        assertEquals(0, coinChangeResponseDto.getCoinChangeMap().size());
    }
    @Test
    @DisplayName("Verify that correct number of single coins are being returned")
    void verify_single_coin_returned() {
        CoinChangeResponseDto coinChangeResponseDto = coinChangeService.getChange(".04");

        assertEquals(1, coinChangeResponseDto.getCoinChangeMap().size());
        assertEquals(4, coinChangeResponseDto.getCoinChangeMap().get(new BigDecimal("0.01")));
    }

    @Test
    @DisplayName("Verify that correct number of 2 coins coins are being returned")
    void verify_two_coins_returned() {
        CoinChangeResponseDto coinChangeResponseDto = coinChangeService.getChange("4.79");

        assertEquals(2, coinChangeResponseDto.getCoinChangeMap().size());
        assertEquals(19, coinChangeResponseDto.getCoinChangeMap().get(new BigDecimal("0.25")));
        assertEquals(4, coinChangeResponseDto.getCoinChangeMap().get(new BigDecimal("0.01")));
    }

    @Test
    @DisplayName("Verify that correct number of all coins coins are being returned")
    void verify_all_coins_returned() {
        CoinChangeResponseDto coinChangeResponseDto = coinChangeService.getChange(".41");

        assertEquals(4, coinChangeResponseDto.getCoinChangeMap().size());
        assertEquals(1, coinChangeResponseDto.getCoinChangeMap().get(new BigDecimal("0.25")));
        assertEquals(1, coinChangeResponseDto.getCoinChangeMap().get(new BigDecimal("0.1")));
        assertEquals(1, coinChangeResponseDto.getCoinChangeMap().get(new BigDecimal("0.05")));
        assertEquals(1, coinChangeResponseDto.getCoinChangeMap().get(new BigDecimal("0.01")));
    }
}
