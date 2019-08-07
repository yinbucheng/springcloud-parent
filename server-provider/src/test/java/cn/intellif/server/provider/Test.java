package cn.intellif.server.provider;

/**
 * @author ：yinchong
 * @create ：2019/7/9 19:00
 * @description：
 * @modified By：
 * @version:
 */
public class Test {

    @org.junit.Test
    public void test(){
        String content ="http://127.0.0.1:7001/test/testForm?name=yinchong&age=20";
        String temp = content.split("\\?")[0];
        System.out.println(temp);
    }
}
