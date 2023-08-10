package com.adp.coinChange.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoinChangeRequestDto {
    private String amount;
}
