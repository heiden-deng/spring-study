package com.hna.tech.dmpcomment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@MapperScan("com.hna.tech.dmpcomment.dao")
public class DmpCommentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmpCommentApplication.class, args);
	}

}
