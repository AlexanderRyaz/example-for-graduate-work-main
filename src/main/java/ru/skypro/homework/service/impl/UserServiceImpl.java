package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void setPassword(NewPassword newPassword) {

    }

    @Override
    public User getUser(String name) {
        UserEntity user = userRepository.findByEmail(name)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        return userMapper.toDto(user);
    }

    @Override
    public User updateUser(User user, String name) {
        UserEntity u = userRepository.findByEmail(name)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        if (user.getPhone() != null) {
            u.setPhone(user.getPhone());
        }
        if (user.getFirstName() != null) {
            u.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            u.setLastName(user.getLastName());
        }
        UserEntity updatedUser = userRepository.save(u);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void updateUserImage(MultipartFile image, String name) throws IOException {
        UserEntity user = userRepository.findByEmail(name)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        user.setImage(image.getBytes());
        userRepository.save(user);
    }

    @Override
    public byte[] getUserImage(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Пользователь не найден")).getImage();
    }
}
