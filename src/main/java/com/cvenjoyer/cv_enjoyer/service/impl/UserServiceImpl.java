package com.cvenjoyer.cv_enjoyer.service.impl;

import com.cvenjoyer.cv_enjoyer.dto.*;
import com.cvenjoyer.cv_enjoyer.exceptions.RegistrationException;
import com.cvenjoyer.cv_enjoyer.mapper.UserMapper;
import com.cvenjoyer.cv_enjoyer.model.Role;
import com.cvenjoyer.cv_enjoyer.model.User;
import com.cvenjoyer.cv_enjoyer.repository.RoleRepository;
import com.cvenjoyer.cv_enjoyer.repository.UserRepository;
import com.cvenjoyer.cv_enjoyer.service.JobService;
import com.cvenjoyer.cv_enjoyer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JobService jobService;

    @Override
    public UserRegistrationResponseDto register
            (UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.email()).isPresent()) {
            throw new RegistrationException("Can't register user");
        }
        User user = new User();
        user.setEmail(requestDto.email());
        user.setFirstName(requestDto.firstName());
        user.setLastName(requestDto.lastName());
        Role userRole = roleRepository.findByName(Role.RoleName.USER)
                .orElseThrow(() -> new RuntimeException("Default role USER not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(requestDto.password()));
        user.setCity(requestDto.city());
        user.setEnglishLevel(requestDto.englishLevel().toUpperCase());
        Set<String> frameworks = requestDto.frameworks().stream()
                .map(String::toUpperCase)
                .collect(Collectors.toSet());
        user.setFrameworks(frameworks);
        Set<String> experienceLevel = requestDto.experienceLevel().stream()
                .map(String::toUpperCase)
                .collect(Collectors.toSet());
        user.setExperienceLevel(experienceLevel);

        Set<String> programmingLanguages = requestDto.programmingLanguages().stream()
                .map(String::toUpperCase)
                .collect(Collectors.toSet());
        user.setProgrammingLanguages(programmingLanguages);
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    public UserDto updateFrameworks(Authentication authentication, UserUpdateFrameworksRequestDto userUpdateFrameworksRequestDto) {
        User principal = (User) authentication.getPrincipal();

        principal.getFrameworks().addAll(userUpdateFrameworksRequestDto.frameworks().stream()
                .map(String::toUpperCase)
                .collect(Collectors.toSet()));

        userRepository.save(principal);
        return userMapper.toDto(principal);
    }

    @Override
    public UserDto updateProgrammingLanguages(Authentication authentication, UserUpdateProgrammingLanguageRequestDto userUpdateProgrammingLanguageRequestDto) {
        User principal = (User) authentication.getPrincipal();

        principal.getProgrammingLanguages().addAll(userUpdateProgrammingLanguageRequestDto.programmingLanguages().stream()
                .map(String::toUpperCase)
                .collect(Collectors.toSet()));

        userRepository.save(principal);
        return userMapper.toDto(principal);
    }

    @Override
    public UserDto updateExperienceLevel(Authentication authentication, UserUpdateExperienceLevelRequestDto userUpdateExperienceLevelRequestDto) {
        User principal = (User) authentication.getPrincipal();

        principal.getExperienceLevel().addAll(userUpdateExperienceLevelRequestDto.experienceLevel().stream()
                .map(String::toUpperCase)
                .collect(Collectors.toSet()));

        userRepository.save(principal);
        return userMapper.toDto(principal);
    }

    @Override
    public UserDto updateEnglishLevel(Authentication authentication, UserUpdateEnglishLevelRequestDto userUpdateEnglishLevelRequestDto) {
        User principal = (User) authentication.getPrincipal();

        principal.setEnglishLevel(userUpdateEnglishLevelRequestDto.englishLevel());

        userRepository.save(principal);
        return userMapper.toDto(principal);
    }

    @Override
    public UserDto myProfileInfo(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return userMapper.toDto(principal);
    }

    @Override
    public UserDto updateCity(Authentication authentication, UpdateCityRequestDto updateCityRequestDto) {
        User principal = (User) authentication.getPrincipal();

        principal.setCity(updateCityRequestDto.city());
        userRepository.save(principal);

        jobService.updateDistancesForUserJobs(principal);

        return userMapper.toDto(principal);
    }

    @Override
    public UserDto resetFrameworks(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        Set<String> frameworks = principal.getFrameworks();
        frameworks.clear();
        userRepository.save(principal);
        return userMapper.toDto(principal);
    }

    @Override
    public UserDto resetProgrammingLanguages(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        Set<String> programmingLanguages = principal.getProgrammingLanguages();
        programmingLanguages.clear();
        userRepository.save(principal);
        return userMapper.toDto(principal);
    }

    @Override
    public UserDto resetExperienceLevel(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        Set<String> experienceLevel = principal.getExperienceLevel();
        experienceLevel.clear();
        userRepository.save(principal);
        return userMapper.toDto(principal);
    }

    @Override
    @Scheduled(cron = "0 59 23 * * *")
    public void dailyGoalResponse() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            Integer dailyGoal = user.getDailyGoal();

            if (dailyGoal != null) {
                if (dailyGoal == 0) {
                    System.out.println("User " + user.getEmail() + " achieved their goal!");
                } else if (dailyGoal > 0) {
                    System.out.println("User " + user.getEmail() + " did not achieve their goal.");
                }
            }

            user.setDailyGoal(null);
            userRepository.save(user);
        }
    }

    @Override
    public UserDto updateDailyGoal(Authentication authentication, UserUpdateDailyGoalRequestDto userUpdateDailyGoalRequestDto) {
        User principal = (User) authentication.getPrincipal();
        principal.setDailyGoal(userUpdateDailyGoalRequestDto.dailyGoal());
        userRepository.save(principal);
        return userMapper.toDto(principal);
    }
}
