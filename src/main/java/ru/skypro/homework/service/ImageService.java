package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void updateAdsImage(Integer id, MultipartFile image);
}
