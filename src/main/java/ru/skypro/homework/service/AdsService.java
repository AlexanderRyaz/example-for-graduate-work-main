package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;

@Service
public interface AdsService {
    ResponseWrapperAds getAds();

    ResponseWrapperComment getComments(String ad_pk);

    Comment addComments(String ad_pk, Comment comment);

    FullAds getFullAd(Integer id);

    void removeAds(Integer id);

    CreateAds updateAds(Integer id, CreateAds createAds);

    Comment getComments(Integer id, String ad_pk);

    void deleteComments(Integer id, String ad_pk);

    Comment updateComments(Integer id, String ad_pk, Comment comment);

    ResponseWrapperAds getAdsMe(Boolean authenticated, String authority, Object credentials, Object details, Object principal);
}
