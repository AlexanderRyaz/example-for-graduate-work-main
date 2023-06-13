package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.entity.AdsEntity;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface AdsMapper {
    @Mapping(target = "author", source = "author.id")
    Ads toDto(AdsEntity entity);
    @Mapping(target = "author", ignore = true)
    AdsEntity toEntity(Ads dto);

    AdsEntity createAdsToEntity(CreateAds createAds);
}
