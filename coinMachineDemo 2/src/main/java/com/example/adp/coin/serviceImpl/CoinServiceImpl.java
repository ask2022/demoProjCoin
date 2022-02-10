package com.example.adp.coin.serviceImpl;

import com.example.adp.coin.service.CoinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
public class CoinServiceImpl implements CoinService {

    @Value("#{${coins}}")
    private Map<Double,Integer> coinsMap;


    @Override
    public Map<String, Integer> getCoins(String amt) {
        log.info("In getCoins");
        Map<String,Integer> coinsToBeDispatched=this.getOptimalCoins(Double.valueOf(amt));
        return coinsToBeDispatched;
    }

    private Map<String, Integer> getOptimalCoins(Double inputAmt) {
       log.info("getOptimalCoins");
        Map<String,Integer> dispatchCoins=new HashMap();
        if(checkAvailability(inputAmt,coinsMap)) {
            for (Map.Entry<Double, Integer> entry : coinsMap.entrySet()) {
                Double key=entry.getKey();
                // Find denominations
                while (inputAmt >= key && coinsMap.get((key))>0)
                {
                    BigDecimal a=BigDecimal.valueOf(inputAmt);
                    BigDecimal b=BigDecimal.valueOf(key);
                    BigDecimal c=a.subtract(b);
                    inputAmt = c.doubleValue();
                    if(dispatchCoins.containsKey(String.valueOf(key))) {
                        dispatchCoins.put(String.valueOf(key), dispatchCoins.get(String.valueOf(key)) + 1);
                    }else {
                        dispatchCoins.put(String.valueOf(key), 1);
                    }

                    coinsMap.put(key,coinsMap.get(key)-1);
                }
            }

            log.info("dispatchCoins Map "+dispatchCoins);
            log.info("Inventory map "+coinsMap);
        }else{
            log.info("No Coins Available");
        }
        return dispatchCoins;
    }


    @Override
    public boolean validate(String strNum) {
        if (!StringUtils.hasText(strNum)) {
            log.error("validation failed");
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
            if(d==0.0) {
                log.error("validation failed");
                return false;
            }

        } catch (NumberFormatException nfe) {
            log.error("validation failed for exception "+ nfe.getMessage());
            return false;
        }
        log.info("validation success");
        return true;
    }


    private boolean checkAvailability(Double inputAmt, Map<Double, Integer> invCoins) {
        log.info("In checkAvailability");
        AtomicReference<Double> total= new AtomicReference<>(0.0);
        invCoins.forEach((k, val) -> total.set(total.get() + (val * Double.valueOf(k))));

        if(total.get()>=inputAmt)
            return true;

        return false;
    }
    }
