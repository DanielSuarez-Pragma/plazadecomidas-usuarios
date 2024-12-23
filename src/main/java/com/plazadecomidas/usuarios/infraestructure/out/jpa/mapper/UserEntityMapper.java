package com.plazadecomidas.usuarios.infraestructure.out.jpa.mapper;

import com.plazadecomidas.usuarios.domain.model.User;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.entity.RoleEntity;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

    default UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }
        // Crear la entidad manualmente
        UserEntity entity = new UserEntity();
        // Mapear los campos
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setDocumentNumber(user.getDocumentNumber());
        entity.setPhoneNumber(user.getPhone()); // Asegúrate de este campo
        entity.setDateOfBirth(user.getBirthDate());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(user.getRoleId()); // Asigna el ID al RoleEntity
        entity.setRole(roleEntity); // Asigna el objeto RoleEntity
        return entity;
    }


    // Método personalizado para convertir UserEntity a User
    default User toUser(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new User(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getDocumentNumber(),
                userEntity.getPhoneNumber(),
                userEntity.getDateOfBirth(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getRole().getId()
        );
    }

    List<User> toUserList(List<UserEntity> userEntityList);
}