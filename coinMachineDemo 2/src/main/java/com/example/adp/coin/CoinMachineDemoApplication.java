package com.example.adp.coin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class CoinMachineDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoinMachineDemoApplication.class, args);
	}

}
