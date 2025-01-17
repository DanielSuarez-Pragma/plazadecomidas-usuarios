package com.plazadecomidas.usuarios.infraestructure.out.jpa.mapper;

import com.plazadecomidas.usuarios.domain.model.User;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

    RoleEntityMapper INSTANCE = Mappers.getMapper(RoleEntityMapper.class);

    default User toUser(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        User user = new User();
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setDocumentNumber(userEntity.getDocumentNumber());
        user.setPhone(userEntity.getPhone());
        user.setBirthDate(userEntity.getBirthDate());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());
        user.setRole(INSTANCE.toRole(userEntity.getRole()));
        return user;
    }


    UserEntity toEntity(User user);
    default List<User> toUserList(List<UserEntity> userEntityList) {
        return userEntityList.stream().map(user -> {
            User response = new User();
            response.setId(user.getId());
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setDocumentNumber(user.getDocumentNumber());
            response.setPhone(user.getPhone());
            response.setBirthDate(user.getBirthDate());
            response.setEmail(user.getEmail());
            response.setRole(INSTANCE.toRole(user.getRole()));
            return response;
        }).toList();
    }

}