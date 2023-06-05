package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.AdsService;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {
    private final AdsRepository adsRepository;
    private final CommentRepository commentRepository;

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
    public void deleteComments(Integer adId, Integer commentId) {

    }

    @Override
    public Comment updateComments(Integer adId, Integer commentId, Comment comment) {
        return null;
    }

    @Override
    public ResponseWrapperAds getAdsMe() {
        return null;
    }

    @Override
    public void updateAdsImage(MultipartFile image, Integer id) {

    }

    @Override
    public Ads addAds(CreateAds properties, MultipartFile image) {
        return null;
    }

}
