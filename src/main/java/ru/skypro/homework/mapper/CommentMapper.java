package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CommentMapper {
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorImage", source = "author.image")
    Comment toDto(CommentEntity entity);

    @Mapping(target = "author", ignore = true)
    CommentEntity toEntity(Comment dto);
}
