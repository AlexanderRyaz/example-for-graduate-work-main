package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void setPassword(NewPassword newPassword) {

    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User UpdateUserImage(User user) {
        return null;
    }

    @Override
    public void updateUserImage(MultipartFile image) {

    }
}
