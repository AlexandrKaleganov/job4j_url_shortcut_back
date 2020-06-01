package ru.akaleganov.job4j_url_shortcut.repository;

import org.apache.log4j.Logger;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.job4j_url_shortcut.domain.Role;
import ru.akaleganov.job4j_url_shortcut.domain.User;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;


@DisplayName("тестирование: модель пользователи")
@TestPropertySource(locations = "classpath:application-h2.properties")
@SpringBootTest
class UserRepositoryTest {
    private static final Logger LOGGER = Logger.getLogger(UserRepositoryTest.class);
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("тестирование: добавление пользователия в  бд")
    public void addUser() {
        Role role = this.roleRepository.save(new Role("ADMIN2"));
        assertThat(role.getId() != null, Is.is(true));
        LOGGER.info("role = " + role.toString());
        User user = new User();
        user.setLogin("test");
        user.setPwd("pwd");
        user.setRoles(Collections.singletonList(role));
        User usersResult = this.userRepository.save(user);
        assertThat(usersResult.getId() != null, Is.is(true));
    }

}