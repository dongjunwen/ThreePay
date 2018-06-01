package com.three.pay.paymentweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.three.pay")
public class PaymentWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentWebApplication.class, args);
	}
}
