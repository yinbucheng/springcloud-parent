package cn.intellif.server.consumer.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：yinchong
 * @create ：2019/8/7 9:00
 * @description：
 * @modified By：
 * @version:
 */
@SuppressWarnings("all")
@Configuration
public class TomcatConfig {

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                ((TomcatServletWebServerFactory) factory).addConnectorCustomizers(new TomcatConnectorCustomizer() {
                    @Override
                    public void customize(Connector connector) {
                        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
                        //定制keepalivetimeout,设置30秒内没有请求则服务自动断开keepalive连接
                        protocol.setKeepAliveTimeout(30000);
                        //当客户端发送超过10000个请求则自动断开keepalive链接
                        protocol.setMaxKeepAliveRequests(10000);
                    }
                });
            }
        };
    }

}
