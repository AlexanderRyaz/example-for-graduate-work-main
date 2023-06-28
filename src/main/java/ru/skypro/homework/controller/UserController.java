package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPassword newPassword) {
        userService.setPassword(newPassword);
        return ResponseEntity.ok().build();
    }

    @GetMapping(name = "/me", produces = {MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<User> getUser(Authentication authentication) {
        User user = userService.getUser(authentication.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping(name = "/me", produces = {MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<User> updateUser(@RequestBody User user, Authentication authentication) {
        User updateUser = userService.updateUser(user, authentication.getName());
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @PatchMapping(value = "me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUserImage(@RequestParam
                                                  MultipartFile image, Authentication authentication) throws IOException {

        userService.updateUserImage(image, authentication.getName());
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "/images/{id}/", produces = {MediaType.IMAGE_PNG_VALUE})
    public byte[] getImage(@PathVariable Integer id) {
        return userService.getUserImage(id);
    }
}
