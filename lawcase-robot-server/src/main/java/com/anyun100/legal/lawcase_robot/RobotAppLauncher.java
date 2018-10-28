package com.anyun100.legal.lawcase_robot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.anyun100.legal.lawcase_robot.service.LegalCaseStoreImpl;
import com.anyun100.legal.lawcase_robot.service.PageProperties;
import com.anyun100.legal.lawcase_robot.service.StorageProperties;
import com.anyun100.legal.lawcase_robot.service.StorageService;

@SpringBootApplication
@MapperScan("com.anyun100.legal.lawcase_robot.mapper")
@EnableConfigurationProperties({StorageProperties.class, PageProperties.class})
public class RobotAppLauncher {

	public static void main(String[] args) {
		SpringApplication.run(RobotAppLauncher.class, args);
	}
	
	@Bean
    CommandLineRunner init(StorageService storageService, LegalCaseStoreImpl legalCaseStore) {
        return (args) -> {
            storageService.init();
            legalCaseStore.initStore();
        };
    }
}
