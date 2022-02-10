package com.example.adp.coin.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CoinService {

        Map<String, Integer> getCoins(String amt);

        boolean validate(String amt);
}
