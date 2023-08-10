package com.adp.coinChange.Utils;

import com.adp.coinChange.model.Coin;

import java.math.BigDecimal;

public class CoinUtil {

    /**
     * The below method is a helper method to return the denominations.
     * @return array of coins
     */
    public static Coin[] getCoinDetails(){
        Coin[] coins = {
                new Coin(new BigDecimal("0.25"), 100),
                new Coin(new BigDecimal("0.10"), 100),
                new Coin(new BigDecimal("0.05"), 100),
                new Coin(new BigDecimal("0.01"), 100)
        };
        return coins;
    }
}
