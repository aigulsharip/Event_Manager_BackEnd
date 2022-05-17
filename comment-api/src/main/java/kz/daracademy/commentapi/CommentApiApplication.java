package kz.daracademy.commentapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CommentApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentApiApplication.class, args);
	}

}
