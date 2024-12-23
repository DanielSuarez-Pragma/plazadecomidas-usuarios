package com.plazadecomidas.usuarios.application.mapper;

import com.plazadecomidas.usuarios.application.dto.UserListRequest;
import com.plazadecomidas.usuarios.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserListRequestMapper {
    @Mapping(target = "id", ignore = true)
    User toUser(UserListRequest userListRequest);

}
