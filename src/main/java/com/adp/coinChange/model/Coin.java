package com.adp.coinChange.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Coin {
    private BigDecimal value;
}
