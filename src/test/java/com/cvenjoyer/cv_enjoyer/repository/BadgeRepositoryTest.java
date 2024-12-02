package com.cvenjoyer.cv_enjoyer.repository;

import com.cvenjoyer.cv_enjoyer.model.Badge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BadgeRepositoryTest {
    private static final String BADGE_NAME = "Java Guru";

    @Autowired
    private BadgeRepository badgeRepository;

    @Test
    @DisplayName("Verify findByName() returns badge when it exists")
    @Sql(scripts = {
            "classpath:database/badges/delete-badges-from-users.sql",
            "classpath:database/badges/add-badges-to-badges_table.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/badges/delete-badges-from-users.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findByName_ShouldReturnBadge_WhenBadgeExists() {
        Badge byName = badgeRepository.findByName(BADGE_NAME);
        assertThat(byName).isNotNull();
        assertThat(byName.getName()).isEqualTo(BADGE_NAME);
    }

    @Test
    @DisplayName("Verify findByName() returns null when badge does not exist")
    @Sql(scripts = {
            "classpath:database/badges/delete-badges-from-users.sql",
            "classpath:database/badges/add-badges-to-badges_table.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/badges/delete-badges-from-users.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findByName_ShouldReturnNull_WhenBadgeDoesNotExist() {
        Badge byName = badgeRepository.findByName("NonExistentBadge");
        assertThat(byName).isNull();
    }
}
