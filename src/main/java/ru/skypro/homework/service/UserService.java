package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;

@Service
public interface UserService {
    NewPassword setPassword(NewPassword newPassword);
    User getUser();
    User updateUser(User user);
    User UpdateUserImage(User user);
}
