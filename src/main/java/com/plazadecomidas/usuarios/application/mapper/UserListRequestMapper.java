package com.plazadecomidas.usuarios.application.mapper;

import com.plazadecomidas.usuarios.application.dto.users.UserListRequest;
import com.plazadecomidas.usuarios.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserListRequestMapper {

    default User toUser(UserListRequest userListRequest) {
        if ( userListRequest == null ) {
            return null;
        }

        User user = new User();
        user.setFirstName(userListRequest.getFirstName());
        user.setLastName(userListRequest.getLastName());
        user.setDocumentNumber(userListRequest.getDocumentNumber());
        user.setPhone(userListRequest.getPhone());
        user.setBirthDate(userListRequest.getBirthDate());
        user.setEmail(userListRequest.getEmail());
        user.setPassword(userListRequest.getPassword());
        return user;
    }
}
