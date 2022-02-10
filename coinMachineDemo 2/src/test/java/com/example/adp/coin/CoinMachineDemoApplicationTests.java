package com.example.adp.coin;

import com.example.adp.coin.service.CoinService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class CoinMachineDemoApplicationTests {

	@Autowired
	private CoinService service;

	@Test
	public void test_ValidateFailiure() {
		Assertions.assertFalse(service.validate("ABCD"));
	}

	@Test
	public void test_ValidateHappyPath() {
		Assertions.assertTrue(service.validate("15"));
	}

	@Test
	public void test_getCoinsHappyPath() {
		Map<String,Integer> coinsMap=service.getCoins("41");
		Assertions.assertNotNull(coinsMap);
	}

	@Test
	public void test_getCoinsNonHappyPath() {
		Map<String,Integer> coinsMap=service.getCoins("42");
		Assertions.assertTrue(coinsMap.isEmpty());
	}

}
