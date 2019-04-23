package cn.bucheng.serversecurity;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @ClassName JdbcReamSecurityTest
 * @Author buchengyin
 * @Date 2019/4/23 19:39
 **/
public class SimpleSecurityTest {

    private SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void before() {
         simpleAccountRealm.addAccount("yinchong","test","admin","user");
    }


    @Test
    public void test() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(simpleAccountRealm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("yinchong", "test");
        subject.login(token);
        System.out.println(subject.isAuthenticated());
        subject.checkRoles("user","admin");
        subject.logout();
        System.out.println(subject.isAuthenticated());
    }
}
