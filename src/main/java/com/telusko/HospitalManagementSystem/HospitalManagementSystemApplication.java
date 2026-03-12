package com.telusko.HospitalManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HospitalManagementSystemApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(HospitalManagementSystemApplication.class, args);


	}

	@Bean
	//RestTemplate object Spring container मध्ये store कर.
//	मग तो dependency injection ने services मध्ये वापरता येतो.
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
}
