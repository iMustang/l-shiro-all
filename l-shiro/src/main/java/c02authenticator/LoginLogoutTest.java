package c02authenticator;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * @title: LoginLogoutTest
 * @description:
 */
public class LoginLogoutTest {
    @Test
    public void testHelloworld() {
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:c02authenticator/shiro.ini");

        // 得到SecurityManager实例，并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        // 得到Subject，其会自动绑定到当前线程
        Subject subject = SecurityUtils.getSubject();
        // 创建用户名/密码身份验证Token
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            // 登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            // 身份验证失败
        }

        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        // 退出
        subject.logout();
    }

    /**
     * 测试MyRealm1
     */
    @Test
    public void testCustomRealm() {
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:c02authenticator/shiro-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            // 登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(true, subject.isAuthenticated());

        subject.logout();
    }

    /**
     * 测试MyRealm2
     */
    @Test
    public void testCustomMultiRealm() {
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:c02authenticator/shiro-multi-realm.ini");

        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wang", "123");

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(true, subject.isAuthenticated());

        subject.logout();
    }

    /**
     * 测试JDBC Realm
     */
    @Test
    public void testJDBCRealm() {
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:c02authenticator/shiro-jdbc-realm.ini");

        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(true, subject.isAuthenticated());

        subject.logout();
    }

    @After
    public void tearDown() {
        //退出时请解除绑定Subject到线程，否则对下次测试造成影响
        ThreadContext.unbindSubject();
    }
}
