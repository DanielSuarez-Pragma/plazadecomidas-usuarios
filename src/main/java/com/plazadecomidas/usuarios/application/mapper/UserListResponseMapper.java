package com.plazadecomidas.usuarios.application.mapper;

import com.plazadecomidas.usuarios.application.dto.RoleDto;
import com.plazadecomidas.usuarios.application.dto.UserListResponse;
import com.plazadecomidas.usuarios.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {RoleDtoMapper.class})
public interface UserListResponseMapper {
    UserListResponse toResponse(User user, RoleDto roleDto);

    default List<UserListResponse> toResponseList(List<User> userList) {
        return userList.stream().map(user -> {
            UserListResponse response = new UserListResponse();
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setDocumentNumber(user.getDocumentNumber());
            response.setPhone(user.getPhone());
            response.setBirthDate(user.getBirthDate());
            response.setEmail(user.getEmail());
            response.setRoleId(user.getRoleId() != null ? user.getRoleId() : null);
            return response;
        }).toList();
    }
}
