package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.Component;
import hello.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import hello.proxy.pureproxy.decorator.code.RealComponent;
import hello.proxy.pureproxy.decorator.code.MessangerDecorator;
import hello.proxy.pureproxy.decorator.code.TimeDecorator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

    @Test
    void noDecorator() {
        Component realComponent = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
        client.execute();
    }

    @Test
    void decorator1() {
        Component realComponent = new RealComponent();
        MessangerDecorator messangerDecorator = new MessangerDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messangerDecorator);
        client.execute();

    }

    @Test
    void decorator2() {
        Component realComponent = new RealComponent();
        MessangerDecorator messangerDecorator = new MessangerDecorator(realComponent);
        TimeDecorator timeDecorator = new TimeDecorator(messangerDecorator);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);
        client.execute();

    }
}
