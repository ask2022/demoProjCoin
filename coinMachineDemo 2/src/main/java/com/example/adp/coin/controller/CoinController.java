package com.example.adp.coin.controller;

import com.example.adp.coin.exception.CoinMachineException;
import com.example.adp.coin.service.CoinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class CoinController {

    @Autowired
    CoinService service;

    @GetMapping("/coin/{amt}")
    public ResponseEntity<?> getCoins(@PathVariable String amt){
        log.info("In getcoins controller method");
        if(service.validate(amt)){
            Map<String,Integer> coinsToBeDispatched=service.getCoins(amt);

            if(coinsToBeDispatched.isEmpty()){
                log.info("coinsToBeDispatched empty returning "+HttpStatus.NOT_FOUND);
                return new ResponseEntity<>("No Coins Available", HttpStatus.NOT_FOUND);
            }

            log.info("returning success resp "+HttpStatus.OK);
            return new ResponseEntity<>(coinsToBeDispatched, HttpStatus.OK);

        }else{
            log.error("CoinMachineException");
            throw new CoinMachineException();
        }
    }

}
