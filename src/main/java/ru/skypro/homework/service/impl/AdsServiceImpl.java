package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdsEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.NotAuthorizedException;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {
    private final AdsRepository adsRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final AdsMapper adsMapper;
    private final CommentMapper commentMapper;

    @Override
    public ResponseWrapperAds getAds(String search) {

        ResponseWrapperAds ads = new ResponseWrapperAds();
        if (search != null && !search.isBlank()) {
            List<AdsEntity> all = adsRepository.findAllByTitleContainsOrDescriptionContainsOrderByCreatedAtDesc(search, search);
            ads.setCount(all.size());
            ads.setResults(all.stream().map(adsMapper::toDto).collect(Collectors.toList()));
        } else {
            List<AdsEntity> all = adsRepository.findAll();
            ads.setCount(all.size());
            ads.setResults(all.stream().map(adsMapper::toDto).collect(Collectors.toList()));
        }
        return ads;
    }

    @Override
    public ResponseWrapperComment getComments(Integer adPk, String email) {
        adsRepository.findByPkAndAuthor_Email(adPk, email)
                .orElseThrow(() -> new NotFoundException("Обьявление не найдено"));

        ResponseWrapperComment comment = new ResponseWrapperComment();
        List<CommentEntity> all = commentRepository.findAllByAds_Pk(adPk);
        comment.setCount(all.size());
        comment.setResults(all.stream().map(commentMapper::toDto).collect(Collectors.toList()));
        return comment;
    }

    @Override
    public Comment addComments(Integer adPk, Comment comment, String email) {
        AdsEntity adsEntity = adsRepository.findById(adPk)
                .orElseThrow(() -> new NotFoundException("Обьявление не найдено"));
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotAuthorizedException("Пользователь не найден"));
        CommentEntity commentEntity = commentMapper.toEntity(comment);
        commentEntity.setAds(adsEntity);
        commentEntity.setAuthor(user);
        CommentEntity saveComment = commentRepository.save(commentEntity);
        return commentMapper.toDto(saveComment);
    }

    @Override
    public FullAds getFullAd(Integer id, String email) {
        return adsRepository.findFullAdsByIdAndEmail(id, email);
    }

    @Override
    public void removeAds(Integer id, String email) {
        AdsEntity entity = adsRepository.findByPk(id).orElseThrow(() -> new NotFoundException("Обьявление не найдено"));
        if (!entity.getAuthor().getEmail().equals(email)) {
            throw new NotAuthorizedException("Пользователь не имеет права удалять это обьявление");
        }
        adsRepository.deleteByPk(id);

    }

    @Override
    public Ads updateAds(Integer id, CreateAds createAds, String email) {
        AdsEntity entity = adsRepository.findByPk(id)
                .orElseThrow(() -> new NotFoundException("Обьявление не найдено"));
        if (!entity.getAuthor().getEmail().equals(email)) {
            throw new NotAuthorizedException("Пользователь не имеет прав редактирования это обьявление");
        }
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
    public void deleteComments(Integer adId, Integer commentId, String email) {
        adsRepository.findByPk(adId).orElseThrow(() -> new NotFoundException("Обьявление не найдено"));
        CommentEntity entity = commentRepository.findByPkAndAds_Pk(commentId, adId)
                .orElseThrow(() -> new NotFoundException("Комментарий не найден"));
        if (!entity.getAuthor().getEmail().equals(email)) {
            throw new NotAuthorizedException("Пользователь не имеет права удалять этот комментарий");
        }
        commentRepository.deleteByPkAndAds_PkAndAuthor_Email(commentId, adId, email);
    }

    @Override
    public Comment updateComments(Integer adId, Integer commentId, Comment comment, String email) {

        CommentEntity commentEntity = commentRepository.findByPkAndAds_Pk(commentId, adId)
                .orElseThrow(() ->
                        new NotFoundException("Коментарий не найден"));
        if (!commentEntity.getAuthor().getEmail().equals(email)) {
            throw new NotAuthorizedException("Пользователь не может обновить этот коментарий");
        }
        if (comment.getText() != null && !comment.getText().isBlank()) {
            commentEntity.setText(comment.getText());
        }
        CommentEntity updateEntity = commentRepository.save(commentEntity);
        return commentMapper.toDto(updateEntity);
    }


    @Override
    public ResponseWrapperAds getAdsMe(String email) {
        ResponseWrapperAds ads = new ResponseWrapperAds();
        List<AdsEntity> all = adsRepository.findAllByAuthor_Email(email);
        ads.setCount(all.size());
        ads.setResults(all.stream().map(adsMapper::toDto).collect(Collectors.toList()));
        return ads;

    }

    @Override
    public void updateAdsImage(MultipartFile image, Integer id) {

    }

    @Override
    public Ads addAds(CreateAds properties, MultipartFile image) throws IOException {
        AdsEntity entity = adsMapper.createAdsToEntity(properties);
        entity.setImage(image.getBytes());
        AdsEntity saveEntity = adsRepository.save(entity);
        return adsMapper.toDto(saveEntity);
    }

}
