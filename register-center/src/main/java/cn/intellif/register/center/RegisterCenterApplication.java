package cn.intellif.register.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
//@EnableApolloConfig(value = {"application"})
public class RegisterCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterCenterApplication.class, args);
    }

}
