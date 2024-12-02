package com.cvenjoyer.cv_enjoyer.repository;

import com.cvenjoyer.cv_enjoyer.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    private static final String EMAIL = "dirk@example.com";

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Verify findByEmail() method works")
    @Sql(scripts = {
            "classpath:database/roles/delete-roles-from-roles_table.sql",
            "classpath:database/users/delete-users-from-users_table.sql",
            "classpath:database/badges/delete-badges-from-badges_table.sql",
            "classpath:database/roles/add-roles-to-roles_table.sql",
            "classpath:database/badges/add-badges-to-badges_table.sql",
            "classpath:database/users/add-users-to-users_table.sql"
    },
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/roles/delete-roles-from-roles_table.sql",
            "classpath:database/badges/delete-badges-from-badges_table.sql",
            "classpath:database/users/delete-users-from-users_table.sql"
    },
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findByEmail_ShouldReturnUser_WhenEmailExists() {
        User user = userRepository.findByEmail(EMAIL).orElseThrow();

        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo(EMAIL);
        assertThat(user.getFirstName()).isEqualTo("Dirk");
        assertThat(user.getLastName()).isEqualTo("Smith");
        assertThat(user.getPassword()).isEqualTo("password123");
        assertThat(user.getRoles()).hasSize(1);
        assertThat(user.getRoles().iterator().next().getName()).isEqualTo("USER");
        assertThat(user.getBadges()).hasSize(1);
        assertThat(user.getBadges().iterator().next().getName()).isEqualTo("Java Guru");
    }
}