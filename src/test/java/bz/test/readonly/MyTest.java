package bz.test.readonly;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = {
        TestConfig.class
})
class MyTest {

    @Test
    void fail() {
       System.out.println("launch");
    }
}
