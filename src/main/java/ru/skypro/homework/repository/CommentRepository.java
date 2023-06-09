package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findAllByAds_Pk(Integer adsPk);

    void deleteByPkAndAds_Pk(Integer pK, Integer adsPk);
    Optional<CommentEntity> findByPkAndAds_Pk(Integer pK, Integer adsPk);

    Optional<CommentEntity> findByPkAndAds_PkAndAuthor_Email(Integer pK, Integer adsPk, String email);

    void deleteByPkAndAds_PkAndAuthor_Email(Integer pK, Integer adsPk, String email);
}
