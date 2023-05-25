package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;

@Service
public interface UserService {
    NewPassword setPassword(NewPassword newPassword);
    User getUser();
    User updateUser(User user);
    User UpdateUserImage(User user);

    void updateUserImage(MultipartFile image);
}
