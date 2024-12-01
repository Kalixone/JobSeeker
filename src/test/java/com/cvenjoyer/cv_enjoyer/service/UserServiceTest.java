package com.cvenjoyer.cv_enjoyer.service;

import com.cvenjoyer.cv_enjoyer.dto.*;
import com.cvenjoyer.cv_enjoyer.mapper.UserMapper;
import com.cvenjoyer.cv_enjoyer.model.Role;
import com.cvenjoyer.cv_enjoyer.model.User;
import com.cvenjoyer.cv_enjoyer.repository.UserRepository;
import com.cvenjoyer.cv_enjoyer.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import java.util.HashSet;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private static final Long USER_ID = 1L;
    private static final String FIRST_NAME = "Piotr";
    private static final String LAST_NAME = "Kendzior";
    private static final String EMAIL = "random@gmail.com";
    private static final String ENGLISH_LEVEL = "B2";
    private static final String UPDATED_ENGLISH_LEVEL = "C1";
    private static final Set<String> FRAMEWORKS = Set.of("Hibernate, Spring");
    private static final Set<String> UPDATED_FRAMEWORKS = Set.of("AWS");
    private static final Set<String> CLEAR_FRAMEWORKS = new HashSet<>();
    private static final Set<String> PROGRAMMING_LANGUAGES = Set.of("Python, Java");
    private static final Set<String> UPDATED_PROGRAMMING_LANGUAGES = Set.of("Kotlin");
    private static final Set<String> CLEAR_PROGRAMMING_LANGUAGES = new HashSet<>();
    private static final Set<String> EXPERIENCE_LEVEL = Set.of("JUNIOR, MIDDLE");
    private static final Set<String> UPDATED_EXPERIENCE_LEVEL = Set.of("Senior");
    private static final Set<String> CLEAR_EXPERIENCE_LEVEL = new HashSet<>();
    private static final Integer DAILY_GOAL = 5;
    private static final Integer UPDATED_DAILY_GOAL = 10;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserServiceImpl userService;


    @Test
    @DisplayName("updateFrameworks returns updated UserDto with new frameworks")
    public void updateFrameworks_ReturnsUpdatedUserDtoWithNewFrameworks() {
        Authentication authentication = mock(Authentication.class);
        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, ENGLISH_LEVEL, FRAMEWORKS, PROGRAMMING_LANGUAGES, EXPERIENCE_LEVEL, DAILY_GOAL);
        User updatedUser = createUser(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, ENGLISH_LEVEL, UPDATED_FRAMEWORKS, PROGRAMMING_LANGUAGES, EXPERIENCE_LEVEL, DAILY_GOAL);
        UserUpdateFrameworksRequestDto userUpdateFrameworksRequestDto = new UserUpdateFrameworksRequestDto(UPDATED_FRAMEWORKS);
        UserDto userDto = createUserDto(EMAIL, FIRST_NAME, LAST_NAME, null, null, ENGLISH_LEVEL, UPDATED_FRAMEWORKS, PROGRAMMING_LANGUAGES, EXPERIENCE_LEVEL);

        when(authentication.getPrincipal()).thenReturn(user);
        when(userRepository.save(user)).thenReturn(updatedUser);
        when(userMapper.toDto(updatedUser)).thenReturn(userDto);

        UserDto result = userService.updateFrameworks(authentication, userUpdateFrameworksRequestDto);

        assertThat(result).isNotNull();
        assertThat(result.frameworks()).containsExactlyInAnyOrder("AWS");
        verify(userRepository).save(user);
        verify(userMapper).toDto(updatedUser);
    }

    @Test
    @DisplayName("updateProgrammingLanguages returns updated UserDto with new programming languages")
    public void updateProgrammingLanguages_ReturnsUpdatedUserDtoWithNewProgrammingLanguages() {
        Authentication authentication = mock(Authentication.class);
        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, ENGLISH_LEVEL, FRAMEWORKS, PROGRAMMING_LANGUAGES, EXPERIENCE_LEVEL, DAILY_GOAL);
        User updatedUser = createUser(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, ENGLISH_LEVEL, FRAMEWORKS, UPDATED_PROGRAMMING_LANGUAGES, EXPERIENCE_LEVEL, DAILY_GOAL);
        UserUpdateProgrammingLanguageRequestDto userUpdateProgrammingLanguageRequestDto = new UserUpdateProgrammingLanguageRequestDto(UPDATED_PROGRAMMING_LANGUAGES);
        UserDto userDto = createUserDto(EMAIL, FIRST_NAME, LAST_NAME, null, null, ENGLISH_LEVEL, FRAMEWORKS, UPDATED_PROGRAMMING_LANGUAGES, EXPERIENCE_LEVEL);

        when(authentication.getPrincipal()).thenReturn(user);
        when(userRepository.save(user)).thenReturn(updatedUser);
        when(userMapper.toDto(updatedUser)).thenReturn(userDto);

        UserDto result = userService.updateProgrammingLanguages(authentication, userUpdateProgrammingLanguageRequestDto);

        assertThat(result).isNotNull();
        assertThat(result.programmingLanguages()).containsExactlyInAnyOrder("Kotlin");
        verify(userRepository).save(user);
        verify(userMapper).toDto(updatedUser);
    }

    @Test
    @DisplayName("updateExperienceLevel returns updated UserDto with new experience level")
    public void updateExperienceLevel_ReturnsUpdatedUserDtoWithNewExperienceLevel() {
        Authentication authentication = mock(Authentication.class);
        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, ENGLISH_LEVEL, FRAMEWORKS, PROGRAMMING_LANGUAGES, EXPERIENCE_LEVEL, DAILY_GOAL);
        User updatedUser = createUser(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, ENGLISH_LEVEL, FRAMEWORKS, PROGRAMMING_LANGUAGES, UPDATED_EXPERIENCE_LEVEL, DAILY_GOAL);
        UserUpdateExperienceLevelRequestDto userUpdateExperienceLevelRequestDto = new UserUpdateExperienceLevelRequestDto(UPDATED_EXPERIENCE_LEVEL);
        UserDto userDto = createUserDto(EMAIL, FIRST_NAME, LAST_NAME, null, null, ENGLISH_LEVEL, FRAMEWORKS, PROGRAMMING_LANGUAGES, UPDATED_EXPERIENCE_LEVEL);

        when(authentication.getPrincipal()).thenReturn(user);
        when(userRepository.save(user)).thenReturn(updatedUser);
        when(userMapper.toDto(updatedUser)).thenReturn(userDto);

        UserDto result = userService.updateExperienceLevel(authentication, userUpdateExperienceLevelRequestDto);

        assertThat(result).isNotNull();
        assertThat(result.experienceLevel()).containsExactlyInAnyOrder("Senior");
        verify(userRepository).save(user);
        verify(userMapper).toDto(updatedUser);
    }

    @Test
    @DisplayName("updateEnglishLevel returns updated UserDto with new English level")
    public void updateEnglishLevel_ReturnsUpdatedUserDtoWithNewEnglishLevel() {
        Authentication authentication = mock(Authentication.class);
        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, ENGLISH_LEVEL, FRAMEWORKS, PROGRAMMING_LANGUAGES, EXPERIENCE_LEVEL, DAILY_GOAL);
        User updatedUser = createUser(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, UPDATED_ENGLISH_LEVEL, FRAMEWORKS, PROGRAMMING_LANGUAGES, EXPERIENCE_LEVEL, DAILY_GOAL);
        UserUpdateEnglishLevelRequestDto userUpdateEnglishLevelRequestDto = new UserUpdateEnglishLevelRequestDto(UPDATED_ENGLISH_LEVEL);
        UserDto userDto = createUserDto(EMAIL, FIRST_NAME, LAST_NAME, null, null, UPDATED_ENGLISH_LEVEL, FRAMEWORKS, PROGRAMMING_LANGUAGES, UPDATED_EXPERIENCE_LEVEL);

        when(authentication.getPrincipal()).thenReturn(user);
        when(userRepository.save(user)).thenReturn(updatedUser);
        when(userMapper.toDto(updatedUser)).thenReturn(userDto);

        UserDto result = userService.updateEnglishLevel(authentication, userUpdateEnglishLevelRequestDto);

        assertThat(result).isNotNull();
        assertThat(result.englishLevel()).isEqualTo("C1");
        verify(userRepository).save(user);
        verify(userMapper).toDto(updatedUser);
    }

    @Test
    @DisplayName("updateDailyGoal returns updated UserDto with new daily goal")
    public void updateDailyGoal_ReturnsUpdatedUserDtoWithNewDailyGoal() {
        Authentication authentication = mock(Authentication.class);
        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, ENGLISH_LEVEL, FRAMEWORKS, PROGRAMMING_LANGUAGES, EXPERIENCE_LEVEL, DAILY_GOAL);
        User updatedUser = createUser(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, ENGLISH_LEVEL, FRAMEWORKS, PROGRAMMING_LANGUAGES, EXPERIENCE_LEVEL, UPDATED_DAILY_GOAL);
        UserUpdateDailyGoalRequestDto userUpdateDailyGoalRequestDto = new UserUpdateDailyGoalRequestDto(UPDATED_DAILY_GOAL);
        UserDto userDto = createUserDto(EMAIL, FIRST_NAME, LAST_NAME, null, null, ENGLISH_LEVEL, FRAMEWORKS, PROGRAMMING_LANGUAGES, EXPERIENCE_LEVEL);

        when(authentication.getPrincipal()).thenReturn(user);
        when(userRepository.save(user)).thenReturn(updatedUser);
        when(userMapper.toDto(updatedUser)).thenReturn(userDto);

        UserDto result = userService.updateDailyGoal(authentication, userUpdateDailyGoalRequestDto);

        assertThat(result).isNotNull();
        assertThat(updatedUser.getDailyGoal()).isEqualTo(UPDATED_DAILY_GOAL);
        verify(userRepository).save(user);
        verify(userMapper).toDto(updatedUser);
    }

    @Test
    @DisplayName("myProfileInfo returns UserDto with correct user data")
    public void myProfileInfo_ReturnsUserDtoWithCorrectUserData() {
        Authentication authentication = mock(Authentication.class);
        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, ENGLISH_LEVEL, FRAMEWORKS, PROGRAMMING_LANGUAGES, EXPERIENCE_LEVEL, DAILY_GOAL);
        UserDto userDto = createUserDto(EMAIL, FIRST_NAME, LAST_NAME, null, null, ENGLISH_LEVEL, FRAMEWORKS, PROGRAMMING_LANGUAGES, UPDATED_EXPERIENCE_LEVEL);

        when(authentication.getPrincipal()).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDto);

        UserDto result = userService.myProfileInfo(authentication);

        assertThat(result).isNotNull();
        verify(userMapper).toDto(user);
    }

    @Test
    @DisplayName("resetFrameworks clears frameworks and returns updated UserDto")
    public void resetFrameworks_ClearsFrameworksAndReturnsUpdatedUserDto()  {
        Authentication authentication = mock(Authentication.class);
        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, ENGLISH_LEVEL, FRAMEWORKS, PROGRAMMING_LANGUAGES, EXPERIENCE_LEVEL, DAILY_GOAL);
        User updatedUser = createUser(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, ENGLISH_LEVEL, CLEAR_FRAMEWORKS, PROGRAMMING_LANGUAGES, EXPERIENCE_LEVEL, DAILY_GOAL);
        UserDto userDto = createUserDto(EMAIL, FIRST_NAME, LAST_NAME, null, null, ENGLISH_LEVEL, CLEAR_FRAMEWORKS, PROGRAMMING_LANGUAGES, UPDATED_EXPERIENCE_LEVEL);

        when(authentication.getPrincipal()).thenReturn(user);
        when(userRepository.save(user)).thenReturn(updatedUser);
        when(userMapper.toDto(updatedUser)).thenReturn(userDto);

        UserDto result = userService.resetFrameworks(authentication);

        assertThat(result).isNotNull();
        assertThat(result.frameworks()).isEmpty();
        verify(userRepository).save(user);
        verify(userMapper).toDto(updatedUser);
    }

    @Test
    @DisplayName("resetProgrammingLanguages clears programming languages and returns updated UserDto")
    public void resetProgrammingLanguages_ClearsProgrammingLanguagesAndReturnsUpdatedUserDto() {
        Authentication authentication = mock(Authentication.class);
        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, ENGLISH_LEVEL, FRAMEWORKS, PROGRAMMING_LANGUAGES, EXPERIENCE_LEVEL, DAILY_GOAL);
        User updatedUser = createUser(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, ENGLISH_LEVEL, FRAMEWORKS, CLEAR_PROGRAMMING_LANGUAGES, EXPERIENCE_LEVEL, DAILY_GOAL);
        UserDto userDto = createUserDto(EMAIL, FIRST_NAME, LAST_NAME, null, null, ENGLISH_LEVEL, FRAMEWORKS, CLEAR_PROGRAMMING_LANGUAGES, UPDATED_EXPERIENCE_LEVEL);

        when(authentication.getPrincipal()).thenReturn(user);
        when(userRepository.save(user)).thenReturn(updatedUser);
        when(userMapper.toDto(updatedUser)).thenReturn(userDto);

        UserDto result = userService.resetProgrammingLanguages(authentication);

        assertThat(result).isNotNull();
        assertThat(result.programmingLanguages()).isEmpty();
        verify(userRepository).save(user);
        verify(userMapper).toDto(updatedUser);
    }

    @Test
    @DisplayName("resetExperienceLevel clears experience level and returns updated UserDto")
    public void resetExperienceLevel_ClearsExperienceLevelAndReturnsUpdatedUserDto() {
        Authentication authentication = mock(Authentication.class);
        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, ENGLISH_LEVEL, FRAMEWORKS, PROGRAMMING_LANGUAGES, EXPERIENCE_LEVEL, DAILY_GOAL);
        User updatedUser = createUser(USER_ID, FIRST_NAME, LAST_NAME, EMAIL, ENGLISH_LEVEL, FRAMEWORKS, PROGRAMMING_LANGUAGES, CLEAR_EXPERIENCE_LEVEL, DAILY_GOAL);
        UserDto userDto = createUserDto(EMAIL, FIRST_NAME, LAST_NAME, null, null, ENGLISH_LEVEL, FRAMEWORKS, PROGRAMMING_LANGUAGES, CLEAR_EXPERIENCE_LEVEL);

        when(authentication.getPrincipal()).thenReturn(user);
        when(userRepository.save(user)).thenReturn(updatedUser);
        when(userMapper.toDto(updatedUser)).thenReturn(userDto);

        UserDto result = userService.resetExperienceLevel(authentication);

        assertThat(result).isNotNull();
        assertThat(result.experienceLevel()).isEmpty();
        verify(userRepository).save(user);
        verify(userMapper).toDto(updatedUser);
    }


    private User createUser(Long id, String firstName, String lastName, String email,
                            String englishLevel, Set<String> frameworks, Set<String> programmingLanguages,
                            Set<String> experienceLevel, Integer dailyGoal) {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setEnglishLevel(englishLevel);
        user.setFrameworks(frameworks);
        user.setProgrammingLanguages(programmingLanguages);
        user.setExperienceLevel(experienceLevel);
        user.setDailyGoal(dailyGoal);
        return user;
    }
    private UserDto createUserDto(String email, String firstName, String lastName, String city,
                                  Set<Role> roles, String englishLevel, Set<String> frameworks,
                                  Set<String> programmingLanguages, Set<String> experienceLevel) {
        return new UserDto(email, firstName, lastName, city, roles, englishLevel, frameworks, programmingLanguages, experienceLevel);
    }
}
