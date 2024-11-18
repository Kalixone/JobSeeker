package com.cvenjoyer.cv_enjoyer.service.impl;

import com.cvenjoyer.cv_enjoyer.dto.UserRegistrationRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.UserRegistrationResponseDto;
import com.cvenjoyer.cv_enjoyer.exceptions.RegistrationException;
import com.cvenjoyer.cv_enjoyer.mapper.UserMapper;
import com.cvenjoyer.cv_enjoyer.model.Role;
import com.cvenjoyer.cv_enjoyer.model.User;
import com.cvenjoyer.cv_enjoyer.repository.RoleRepository;
import com.cvenjoyer.cv_enjoyer.repository.UserRepository;
import com.cvenjoyer.cv_enjoyer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

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
        user.setEnglishLevel(requestDto.englishLevel());
        user.setFrameworks(requestDto.frameworks());
        user.setExperienceLevel(requestDto.experienceLevel());
        user.setProgrammingLanguages(requestDto.programmingLanguages());
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }
}
