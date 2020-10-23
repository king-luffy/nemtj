package com.nemt.nemtj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nemt.nemtj.dao")
public class NemtjApplication {

	public static void main(String[] args) {
		SpringApplication.run(NemtjApplication.class, args);
	}

}
