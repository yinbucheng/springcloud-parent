package cn.bucheng.serversecurity.jdbcream;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @ClassName JdbcReamSecurityTest
 * @Author buchengyin
 * @Date 2019/4/23 19:39
 **/
public class JdbcReamSecurityTest {

    DruidDataSource dataSource = new DruidDataSource();

    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/yinchong?useUnicode=true&characterEncoding=utf8&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("introcks1234");
    }


    @Test
    public void test() {
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setPermissionsLookupEnabled(true);
        jdbcRealm.setDataSource(dataSource);
//        jdbcRealm.setAuthenticationQuery();
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(jdbcRealm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("yinchong", "admin");
        subject.login(token);
        System.out.println(subject.isAuthenticated());
        subject.checkRoles("admin");
        subject.checkPermission("user:delete");
        subject.logout();
        System.out.println(subject.isAuthenticated());
    }
}
