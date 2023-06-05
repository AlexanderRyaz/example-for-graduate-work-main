package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.entity.AdsEntity;

@Mapper
public interface AdsMapper {
    Ads toEntity(AdsEntity entity);

    AdsEntity toDto(Ads dto);
}
