package chap10.setup;

import chap07.user_registerer.User;
import org.junit.jupiter.api.BeforeEach;

public class WrongSetUpTest {

    @BeforeEach
    void setUp() {
        changeService = new ChangeUserService(memoryRepository);
        memoryRepository.save(new User("id", "name", "pw"));
    }

}
