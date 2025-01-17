package com.plazadecomidas.usuarios.application.mapper;

import com.plazadecomidas.usuarios.application.dto.users.UserListResponse;
import com.plazadecomidas.usuarios.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {RoleDtoMapper.class})
public interface UserListResponseMapper {

    RoleDtoMapper INSTANCE = Mappers.getMapper(RoleDtoMapper.class);

    default UserListResponse toResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserListResponse userListResponse = new UserListResponse();
        userListResponse.setId(user.getId());
        userListResponse.setFirstName(user.getFirstName());
        userListResponse.setLastName(user.getLastName());
        userListResponse.setDocumentNumber(user.getDocumentNumber());
        userListResponse.setBirthDate(user.getBirthDate());
        userListResponse.setEmail(user.getEmail());
        userListResponse.setRole(INSTANCE.toRoleDto(user.getRole()));


        return userListResponse;
    }


    default List<UserListResponse> toResponseList(List<User> userList) {
        return userList.stream().map(user -> {
            UserListResponse response = new UserListResponse();
            response.setId(user.getId());
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setDocumentNumber(user.getDocumentNumber());
            response.setPhone(user.getPhone());
            response.setBirthDate(user.getBirthDate());
            response.setEmail(user.getEmail());
            response.setRole(INSTANCE.toRoleDto(user.getRole()));
            return response;
        }).toList();
    }
}
