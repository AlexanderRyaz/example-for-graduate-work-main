package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.entity.AdsEntity;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface AdsMapper {
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "image", source = "pk", qualifiedByName = "addAdsImage")
    Ads toDto(AdsEntity entity);

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "image", ignore = true)
    AdsEntity toEntity(Ads dto);

    AdsEntity createAdsToEntity(CreateAds createAds);

    @Named("addAdsImage")
    static String addImage(Integer id) {
        return "/ads/image/" + id;
    }
}
