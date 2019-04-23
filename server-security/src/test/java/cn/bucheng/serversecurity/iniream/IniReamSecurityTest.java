package cn.bucheng.serversecurity.iniream;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @ClassName JdbcReamSecurityTest
 * @Author buchengyin
 * @Date 2019/4/23 19:39
 **/
public class IniReamSecurityTest {

    private IniRealm iniRealm = new IniRealm("classpath:user.ini");


    @Test
    public void test() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(iniRealm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("yinchong", "123456");
        subject.login(token);
        System.out.println(subject.isAuthenticated());
        subject.checkRoles("admin");
        subject.checkPermission("user:update");
        subject.logout();
        System.out.println(subject.isAuthenticated());
    }
}
