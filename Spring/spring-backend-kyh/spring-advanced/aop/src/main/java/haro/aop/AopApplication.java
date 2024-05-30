package haro.aop;

import haro.aop.order.aop.AspectV1;
import haro.aop.order.aop.AspectV2;
import haro.aop.order.aop.AspectV3;
import haro.aop.order.aop.AspectV4Pointcut;
import haro.aop.order.aop.AspectV5Order;
import haro.aop.order.aop.AspectV6Advice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Slf4j
//@Import(AspectV1.class)
//@Import(AspectV2.class)
//@Import(AspectV3.class)
//@Import(AspectV4Pointcut.class)
//@Import({AspectV5Order.LogAspect.class, AspectV5Order.TxAspect.class})
@Import(AspectV6Advice.class)
@SpringBootApplication
public class AopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopApplication.class, args);
	}
}
