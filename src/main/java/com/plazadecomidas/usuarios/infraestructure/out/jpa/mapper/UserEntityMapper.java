package com.plazadecomidas.usuarios.infraestructure.out.jpa.mapper;

import com.plazadecomidas.usuarios.domain.model.User;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {
    @Mapping(target = "firstName", source = "firstName")
    User toUser(UserEntity byEmail);
    UserEntity toEntity(User user);
    List<User> toUserList(List<UserEntity> userEntityList);

}