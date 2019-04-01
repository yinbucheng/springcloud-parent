package cn.intellif.server.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
//@EnableApolloConfig
@EnableDiscoveryClient
public class ServerZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerZuulApplication.class, args);
    }

}
