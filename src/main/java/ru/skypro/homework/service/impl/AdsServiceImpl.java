package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdsEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.AdsService;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {
    private final AdsRepository adsRepository;
    private final CommentRepository commentRepository;
    private final AdsMapper adsMapper;
    private final CommentMapper commentMapper;

    @Override
    public ResponseWrapperAds getAds() {
        return null;
    }

    @Override
    public ResponseWrapperComment getComments(String ad_pk) {
        return null;
    }

    @Override
    public Comment addComments(Integer ad_pk, Comment comment) {
        AdsEntity adsEntity = adsRepository.findById(ad_pk)
                .orElseThrow(() -> new NotFoundException("Обьявление не найдено"));
        CommentEntity commentEntity = commentMapper.toEntity(comment);
        commentEntity.setAds(adsEntity);
        CommentEntity saveComment = commentRepository.save(commentEntity);
        return commentMapper.toDto(saveComment);
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
        AdsEntity entity = adsMapper.createAdsToEntity(properties);
        entity.setImage(image.getOriginalFilename());
        AdsEntity saveEntity = adsRepository.save(entity);
        return adsMapper.toDto(saveEntity);
    }

}
