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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {
    private final AdsRepository adsRepository;
    private final CommentRepository commentRepository;
    private final AdsMapper adsMapper;
    private final CommentMapper commentMapper;

    @Override
    public ResponseWrapperAds getAds() {

        ResponseWrapperAds ads = new ResponseWrapperAds();
        List<AdsEntity> all = adsRepository.findAll();
        ads.setCount(all.size());
        ads.setResults(all.stream().map(adsMapper::toDto).collect(Collectors.toList()));
        return ads;
    }

    @Override
    public ResponseWrapperComment getComments(Integer adPk) {
        ResponseWrapperComment comment = new ResponseWrapperComment();
        List<CommentEntity> all = commentRepository.findAllByAds_Pk(adPk);
        comment.setCount(all.size());
        comment.setResults(all.stream().map(commentMapper::toDto).collect(Collectors.toList()));
        return comment;
    }

    @Override
    public Comment addComments(Integer adPk, Comment comment) {
        AdsEntity adsEntity = adsRepository.findById(adPk)
                .orElseThrow(() -> new NotFoundException("Обьявление не найдено"));
        CommentEntity commentEntity = commentMapper.toEntity(comment);
        commentEntity.setAds(adsEntity);
        CommentEntity saveComment = commentRepository.save(commentEntity);
        return commentMapper.toDto(saveComment);
    }

    @Override
    public FullAds getFullAd(Integer id) {
        return adsRepository.findFullAdsById(id);
    }

    @Override
    public void removeAds(Integer id) {
        adsRepository.deleteById(id);

    }

    @Override
    public Ads updateAds(Integer id, CreateAds createAds) {
        AdsEntity entity = adsRepository.findById(id).orElseThrow(() -> new NotFoundException("Обьявление не найдено"));
        if (createAds.getDescription() != null && !createAds.getDescription().isBlank()) {
            entity.setDescription(createAds.getDescription());
        }
        if (createAds.getTitle() != null && !createAds.getTitle().isBlank()) {
            entity.setTitle(createAds.getTitle());
        }
        if (createAds.getPrice() != null) {
            entity.setPrice(createAds.getPrice());
        }
        AdsEntity updatedEntity = adsRepository.save(entity);
        return adsMapper.toDto(updatedEntity);
    }

    @Override
    public void deleteComments(Integer adId, Integer commentId) {
        commentRepository.deleteByPkAndAds_Pk(commentId, adId);
    }

    @Override
    public Comment updateComments(Integer adId, Integer commentId, Comment comment) {

        CommentEntity commentEntity = commentRepository.findByPkAndAds_Pk(commentId, adId).orElseThrow(() ->
                new NotFoundException("Коментарий не найден"));
        if (comment.getText() != null && !comment.getText().isBlank()) {
            commentEntity.setText(comment.getText());
        }
        CommentEntity updateEntity = commentRepository.save(commentEntity);
        return commentMapper.toDto(updateEntity);
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
