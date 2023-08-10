package com.adp.coinChange.controller;

import com.adp.coinChange.model.CoinChangeRequestDto;
import com.adp.coinChange.model.CoinChangeResponseDto;
import com.adp.coinChange.service.CoinChangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/coins")
public class CoinChangeController {

    private CoinChangeService coinChangeService;
    @Autowired
    public CoinChangeController(CoinChangeService coinChangeService){
        this.coinChangeService = coinChangeService;
    }

    @PostMapping("/change")
    public ResponseEntity<CoinChangeResponseDto> giveChange(@RequestBody String amount) {
        //The above can be a DTO object as well and preferable that. Kept amount to keep it simple for now
        log.info("Received request with amount: {}", amount);
        return ResponseEntity.ok(coinChangeService.getChange(amount));
    }
}
