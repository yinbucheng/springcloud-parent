package cn.intellif.server.consumer.aop;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

/**
 * @author ：yinchong
 * @create ：2019/7/8 9:29
 * @description：
 * @modified By：
 * @version:
 */
//@Component
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        String url = template.url();
        String body = template.bodyTemplate();
        System.out.println(url+"_"+body);
    }
}
