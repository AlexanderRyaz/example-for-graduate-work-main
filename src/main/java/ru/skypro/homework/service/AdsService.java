package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

@Service
public interface AdsService {
    ResponseWrapperAds getAds();

    ResponseWrapperComment getComments(String ad_pk);

    Comment addComments(Integer ad_pk, Comment comment);

    FullAds getFullAd(Integer id);

    void removeAds(Integer id);

    CreateAds updateAds(Integer id, CreateAds createAds);

    Comment getComments(Integer id, String ad_pk);

    void deleteComments(Integer adId, Integer commentId);

    Comment updateComments(Integer adId, Integer commentId, Comment comment);

    ResponseWrapperAds getAdsMe();

    void updateAdsImage(MultipartFile image, Integer id);

    Ads addAds(CreateAds properties, MultipartFile image);

}
