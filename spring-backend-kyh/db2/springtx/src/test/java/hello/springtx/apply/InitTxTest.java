package hello.springtx.apply;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@SpringBootTest
public class InitTxTest {

    @Autowired Haro haro;

    @Test
    void go() {
        // 초기화 코드는 스프링이 초기화 시점에 호출한다.
        // haro.initV1();
    }

    @TestConfiguration
    static class InitTxTestConfig {

        @Bean
        Haro haro() {
            return new Haro();
        }
    }

    @Slf4j
    static class Haro {

        @Transactional
        @PostConstruct
        public void initV1() {
            boolean isActive = TransactionSynchronizationManager.isSynchronizationActive();
            log.info("Haro init @PostConstruct tx active={}", isActive);
        }

        @EventListener(ApplicationReadyEvent.class)
        @Transactional
        public void initV2() {
            boolean isActive = TransactionSynchronizationManager.isSynchronizationActive();
            log.info("Haro init ApplicationReadyEvent tx active={}", isActive);
        }
    }
}
