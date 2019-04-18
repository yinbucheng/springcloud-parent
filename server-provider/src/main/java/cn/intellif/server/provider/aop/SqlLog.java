package cn.intellif.server.provider.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.Connection;

/**
 * 添加sql语句执行拦截
 *
 * @ClassName SqlLog
 * @Author buchengyin
 * @Date 2019/4/18 13:48
 **/
@Aspect
@Component
public class SqlLog {

        @Around("execution(* *..*.getConnection())")
    public Object decorateGetConnection(ProceedingJoinPoint point) {
        try {
            Object proceed = point.proceed();
            if (proceed instanceof Connection) {
                Connection conn = (Connection) proceed;
                LogConnection connection = new LogConnection(conn);
                return conn;
            }
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new RuntimeException("error");
        }
    }
}
