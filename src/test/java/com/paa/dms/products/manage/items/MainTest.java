package com.paa.dms.products.manage.items;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class MainTest {
    @Test
    void contextLoads() {
        assertDoesNotThrow(() -> Main.main(new String[] {}));
    }

}