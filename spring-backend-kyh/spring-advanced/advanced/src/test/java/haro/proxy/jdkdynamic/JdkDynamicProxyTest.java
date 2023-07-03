package haro.proxy.jdkdynamic;

import haro.proxy.jdkdynamic.code.AImpl;
import haro.proxy.jdkdynamic.code.AInterface;
import haro.proxy.jdkdynamic.code.BImpl;
import haro.proxy.jdkdynamic.code.BInterface;
import haro.proxy.jdkdynamic.code.TimeInvocationHandler;
import java.lang.reflect.Proxy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class JdkDynamicProxyTest {
    @Test
    void dynamicA() {
        AInterface target = new AImpl();

        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        AInterface proxy = (AInterface) Proxy.newProxyInstance(
                AInterface.class.getClassLoader(),
                new Class[]{AInterface.class},
                handler);

        proxy.call(); // handler.invoke() 자동 호출

        log.info("targetClass={}", target.getClass());
        log.info("proxy={}", proxy.getClass());
    }

    @Test
    void dynamicB() {
        BInterface target = new BImpl();

        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        BInterface proxy = (BInterface) Proxy.newProxyInstance(
                BInterface.class.getClassLoader(),
                new Class[]{BInterface.class},
                handler);

        proxy.call(); // handler.invoke() 자동 호출

        log.info("targetClass={}", target.getClass());
        log.info("proxy={}", proxy.getClass());
    }
}
