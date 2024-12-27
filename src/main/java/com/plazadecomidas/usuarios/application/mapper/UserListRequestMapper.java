package com.plazadecomidas.usuarios.application.mapper;

import com.plazadecomidas.usuarios.application.dto.UserListRequest;
import com.plazadecomidas.usuarios.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserListRequestMapper {
    // Método personalizado para mapear el objeto
    default User toUser(UserListRequest userListRequest) {
        // Crear el objeto User
        User user = new User();
        // Asignar los valores manualmente
        user.setFirstName(userListRequest.getFirstName());
        user.setLastName(userListRequest.getLastName());
        user.setDocumentNumber(userListRequest.getDocumentNumber());
        user.setPhone(userListRequest.getPhone());
        user.setBirthDate(userListRequest.getBirthDate());
        user.setEmail(userListRequest.getEmail());
        user.setPassword(userListRequest.getPassword());
        user.setRoleId(userListRequest.getRole());

        return user;
    }

}
