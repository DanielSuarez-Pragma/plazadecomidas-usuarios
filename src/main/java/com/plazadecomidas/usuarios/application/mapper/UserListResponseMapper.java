package com.plazadecomidas.usuarios.application.mapper;

import com.plazadecomidas.usuarios.application.dto.UserListResponse;
import com.plazadecomidas.usuarios.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {RoleDtoMapper.class})
public interface UserListResponseMapper {

    RoleDtoMapper INSTANCE = Mappers.getMapper(RoleDtoMapper.class);

    @Mapping(target = "firstName", source = "firstName")
    UserListResponse toResponse(User user);


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
