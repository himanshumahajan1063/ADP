package com.adp.coinChange.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CoinChangeResponseDto {
    Map<BigDecimal, Integer> coinChangeMap;

}
