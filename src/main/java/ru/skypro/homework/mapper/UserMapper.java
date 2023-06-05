package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Mapper
public interface UserMapper {
    User toEntity(UserEntity entity);

    UserEntity toDto(User dto);
}
