package com.cvenjoyer.cv_enjoyer.repository;

import com.cvenjoyer.cv_enjoyer.model.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleRepositoryTest {
    private static final String ROLE_NAME_USER = "USER";
    private static final String ROLE_NAME_ADMIN = "ADMIN";

    @Autowired
    private RoleRepository roleRepository;

    @Test
    @DisplayName("Verify findByName() works for USER role")
    @Sql(scripts = {
            "classpath:database/roles/delete-roles-from-roles_table.sql",
            "classpath:database/roles/add-roles-to-roles_table.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/roles/delete-roles-from-roles_table.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findByRoleName_ShouldReturnRole_WhenRoleExists() {
        Optional<Role> userRole = roleRepository.findByName(Role.RoleName.valueOf(ROLE_NAME_USER));
        assertThat(userRole).isPresent();
        assertThat(userRole.get().getName()).isEqualTo(Role.RoleName.USER);
    }

    @Test
    @DisplayName("Verify findByName() works for ADMIN role")
    @Sql(scripts = {
            "classpath:database/roles/delete-roles-from-roles_table.sql",
            "classpath:database/roles/add-roles-to-roles_table.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/roles/delete-roles-from-roles_table.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findByRoleName_ShouldReturnRole_WhenAdminRoleExists() {
        Optional<Role> adminRole = roleRepository.findByName(Role.RoleName.valueOf(ROLE_NAME_ADMIN));
        assertThat(adminRole).isPresent();
        assertThat(adminRole.get().getName()).isEqualTo(Role.RoleName.ADMIN);
    }
}
