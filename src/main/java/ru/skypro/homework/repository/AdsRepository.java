package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.entity.AdsEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdsRepository extends JpaRepository<AdsEntity, Integer> {
    @Query("select new ru.skypro.homework.dto.FullAds(a.author.firstName," +
            "a.author.lastName,a.description,a.author.email," +
            "a.author.phone,a.pk,a.price,a.title ) from AdsEntity as a join a.author where a.pk = :adsId and a.author.email = :email")
    FullAds findFullAdsByIdAndEmail(@Param("adsId") Integer adsId, @Param("email") String email);

    List<AdsEntity> findAllByTitleContainsOrDescriptionContainsOrderByCreatedAtDesc(String title, String description);

    void deleteByPk(Integer pk);

    Optional<AdsEntity> findByPkAndAuthor_Email(Integer pk, String email);

    Optional<AdsEntity> findByPk(Integer pk);

    List<AdsEntity> findAllByAuthor_Email(String email);
}
