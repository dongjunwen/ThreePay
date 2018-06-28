package com.three.pay.paymentrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(
exclude = {DataSourceAutoConfiguration.class,JpaRepositoriesAutoConfiguration.class, HibernateJpaAutoConfiguration.class}
)
@ComponentScan("com.three.pay")
public class PaymentRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentRestApplication.class, args);
	}
}
