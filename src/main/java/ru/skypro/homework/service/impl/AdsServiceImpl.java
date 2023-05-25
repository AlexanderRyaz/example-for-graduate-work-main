package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdsService;

@Service
public class AdsServiceImpl implements AdsService {
    @Override
    public ResponseWrapperAds getAds() {
        return null;
    }

    @Override
    public ResponseWrapperComment getComments(String ad_pk) {
        return null;
    }

    @Override
    public Comment addComments(String ad_pk, Comment comment) {
        return null;
    }

    @Override
    public FullAds getFullAd(Integer id) {
        return null;
    }

    @Override
    public void removeAds(Integer id) {

    }

    @Override
    public CreateAds updateAds(Integer id, CreateAds createAds) {
        return null;
    }

    @Override
    public Comment getComments(Integer id, String ad_pk) {
        return null;
    }

    @Override
    public void deleteComments(Integer id, String ad_pk) {

    }

    @Override
    public Comment updateComments(Integer id, String ad_pk, Comment comment) {
        return null;
    }

    @Override
    public ResponseWrapperAds getAdsMe(Boolean authenticated, String authority, Object credentials, Object details, Object principal) {
        return null;
    }
}
