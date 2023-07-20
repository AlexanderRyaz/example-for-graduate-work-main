package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

import java.io.IOException;

@Service
public interface AdsService {
    ResponseWrapperAds getAds(String search);

    ResponseWrapperComment getComments(Integer adPk, String email);

    Comment addComments(Integer adPk, Comment comment, String email);

    FullAds getFullAd(Integer id, String email);

    void removeAds(Integer id, String email);

    Ads updateAds(Integer id, CreateAds createAds, String email);

    void deleteComments(Integer adId, Integer commentId, String email);

    Comment updateComments(Integer adId, Integer commentId, Comment comment, String email);

    ResponseWrapperAds getAdsMe(String email);

    void updateAdsImage(MultipartFile image, Integer id);

    Ads addAds(CreateAds properties, MultipartFile image, String name) throws IOException;

    byte[] getAdsImage(Integer id);
}
