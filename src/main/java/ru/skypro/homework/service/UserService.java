package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;

import java.io.IOException;

@Service
public interface UserService {
    void setPassword(NewPassword newPassword);
    User getUser(String name);
    User updateUser(User user, String name);
    void updateUserImage(MultipartFile image, String name) throws IOException;
}
