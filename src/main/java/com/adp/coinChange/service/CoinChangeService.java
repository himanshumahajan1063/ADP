package com.adp.coinChange.service;

import com.adp.coinChange.Exception.AmountParseException;
import com.adp.coinChange.Exception.InvalidAmountException;
import com.adp.coinChange.Utils.CoinUtil;
import com.adp.coinChange.model.Coin;
import com.adp.coinChange.model.CoinChangeResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service
public class CoinChangeService {

    /**
     * The following method returs the change and denominations used to get amount.
     * @param amount amount for which change is required.
     * @return CoinChangeResponseDto
     */
    public CoinChangeResponseDto getChange(final String amount) {
        log.info("Calculating minimum coins for amount: {}", amount);

        BigDecimal parsedAmount = validateAndGetParsedAmount(amount);

        int targetCents = parsedAmount.multiply(new BigDecimal("100")).intValue();

        int[] dp = new int[targetCents + 1];
        int[] lastCoinUsed = new int[targetCents + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;

        for (Coin coin : CoinUtil.getCoinDetails()) {
            int coinCents = coin.getValue().multiply(new BigDecimal("100")).intValue();
            for (int i = coinCents; i <= targetCents; i++) {
                if (dp[i - coinCents] + 1 < dp[i]) {
                    dp[i] = dp[i - coinCents] + 1;
                    lastCoinUsed[i] = coinCents;
                }
            }
        }

        Map<BigDecimal, Integer> result = new LinkedHashMap<>();
        int remainingCents = targetCents;
        while (remainingCents > 0) {
            int coinUsedCents = lastCoinUsed[remainingCents];
            BigDecimal coinUsed = new BigDecimal(coinUsedCents).divide(new BigDecimal("100"));
            result.put(coinUsed, result.getOrDefault(coinUsed, 0) + 1);
            remainingCents -= coinUsedCents;
        }

        return CoinChangeResponseDto.builder().coinChangeMap(result).build();
    }

    /**
     * The following method validates the parsed amount if it is valid or not.
     * @param amount amount to be validated
     * @return parsedAmount
     */
    private static BigDecimal validateAndGetParsedAmount(String amount) {
        BigDecimal parsedAmount;
        try {
            parsedAmount = new BigDecimal(amount);
        } catch (Exception ex) {
            log.error("Failed to parse because of invalid format for amount: {}", amount);
            throw new AmountParseException("Failed to parse invalid amount");
        }
        if (parsedAmount.compareTo(BigDecimal.ZERO) < 0) {
            log.error("Amount has to be greater than zero. Current amount is: {}", amount);
            throw new InvalidAmountException("Amount value has to be greater than zero");
        }
        return parsedAmount;
    }
}
