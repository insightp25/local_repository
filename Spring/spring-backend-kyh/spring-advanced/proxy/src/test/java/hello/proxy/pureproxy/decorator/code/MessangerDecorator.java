package hello.proxy.pureproxy.decorator.code;

import hello.proxy.pureproxy.decorator.code.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessangerDecorator implements Component {

    private Component component;

    public MessangerDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("MessageDecorator 실행");

        // data -> *****data*****
        String result = component.operation();
        String decoResult = "*****" + result + "*****";
        log.info("MessangeDecorator 꾸미기 적용 전={}, 적용 후={}", result, decoResult);
        return decoResult;
    }
}
