package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.service.ImageService;

@RestController
@RequestMapping("image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
}
