package com.tenprj.multimoduleadminboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class MultimoduleAdminBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultimoduleAdminBoardApplication.class, args);
	}

}
