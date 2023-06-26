package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

import java.io.IOException;

@Service
public interface AdsService {
    ResponseWrapperAds getAds(String search);

    ResponseWrapperComment getComments(Integer adPk);

    Comment addComments(Integer adPk, Comment comment);

    FullAds getFullAd(Integer id);

    void removeAds(Integer id);

    Ads updateAds(Integer id, CreateAds createAds);

    void deleteComments(Integer adId, Integer commentId);

    Comment updateComments(Integer adId, Integer commentId, Comment comment);

    ResponseWrapperAds getAdsMe();

    void updateAdsImage(MultipartFile image, Integer id);

    Ads addAds(CreateAds properties, MultipartFile image) throws IOException;

}
