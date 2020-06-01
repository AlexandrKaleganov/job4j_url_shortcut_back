package ru.akaleganov.job4j_url_shortcut.service.util;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("тестирование: RandomGenerator")
@TestPropertySource(locations = "classpath:application-h2.properties")
@SpringBootTest
class RandomGeneratorTest {
    @Autowired
    RandomGenerator randomGenerator;

    @Test
    void generateLogin() {
    }

    @Test
    void generatePassword() {
    }

    @Test
    @DisplayName("тестирование генерации урл")
    void generateNewOrigin() {
        HashSet<String> hashSet = new HashSet<>(101, 1);
        for (int i = 0; i < 100; i++) {
           hashSet.add(this.randomGenerator.generateNewOrigin());
        }
        assertThat(hashSet.size(),
                Is.is(100));
    }
}
