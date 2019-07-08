package cn.intellif.server.provider;

import cn.bucheng.feginrpc.utils.BeanFactoryUtils;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableApolloConfig
@EnableFeignClients
@EnableSwagger2
@MapperScan(basePackages = {"cn.intellif.server.provider.dao"})
public class BootstrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootstrapApplication.class, args);
    }

    //下面是集成swigger2
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("cn"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("spring boot 中使用swigger2").description("provider").termsOfServiceUrl("www.baidu.com").version("1.0.1").build();
    }


    public static void invokeTest() {
        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        ((MockHttpServletRequest) request).setMethod("GET");
        ((MockHttpServletRequest) request).setRequestURI("/test2");
        ((MockHttpServletRequest) request).setServletPath("/test2");
        DispatcherServlet dispatcherServlet = BeanFactoryUtils.getBean(DispatcherServlet.class);
        try {
            Method doDispatch = DispatcherServlet.class.getDeclaredMethod("doDispatch", HttpServletRequest.class, HttpServletResponse.class);
            doDispatch.setAccessible(true);
            doDispatch.invoke(dispatcherServlet, request, response);
            String contentAsString = ((MockHttpServletResponse) response).getContentAsString();
            System.out.println(contentAsString);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
