package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @PostMapping("/set_password")
    public ResponseEntity<NewPassword> setPassword(@RequestBody NewPassword newPassword) {
        NewPassword password = userService.setPassword(newPassword);
        return new ResponseEntity<>(password, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUser() {
        User user = userService.getUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("me")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @PatchMapping("image")
    public ResponseEntity<User> UpdateUserImage(@RequestBody User user) {
        User updateUserImage = userService.UpdateUserImage(user);
        return new ResponseEntity<>(updateUserImage, HttpStatus.OK);
    }

    @PatchMapping(value = "me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUserImage(@RequestParam
                                                  MultipartFile image) {

        userService.updateUserImage(image);
        return ResponseEntity.ok().build();
    }
}
