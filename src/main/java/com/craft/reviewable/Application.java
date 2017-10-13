package com.craft.reviewable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	// protected Application(DataSource dataSource, JpaProperties properties,
	// ObjectProvider<JtaTransactionManager> jtaTransactionManager,
	// ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers)
	// {
	// super(dataSource, properties, jtaTransactionManager,
	// transactionManagerCustomizers);
	// }

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// @Override
	// protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
	// return new HibernateJpaVendorAdapter();
	// }
	//
	// @Override
	// protected Map<String, Object> getVendorProperties() {
	// return Collections.singletonMap("hibernate.dialect",
	// "org.hibernate.dialect.MongoDBDialect");
	// }

}
