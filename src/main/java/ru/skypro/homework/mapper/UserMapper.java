package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "image", source = "id", qualifiedByName = "addUserImage")
    User toDto(UserEntity entity);
    @Mapping(target = "image", ignore = true)
    UserEntity toEntity(User dto);
    @Named("addUserImage")
    static String addImage(Integer id) {
        return "/users/image/" + id;
    }
}
