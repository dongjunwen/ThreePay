package com.three.pay.paymentrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.three.pay")
public class PaymentRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentRestApplication.class, args);
	}
}
