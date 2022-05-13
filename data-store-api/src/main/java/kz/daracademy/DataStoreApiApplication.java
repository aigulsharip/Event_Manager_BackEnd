package kz.daracademy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DataStoreApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataStoreApiApplication.class, args);
    }

}
