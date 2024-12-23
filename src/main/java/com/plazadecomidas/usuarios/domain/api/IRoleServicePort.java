package com.plazadecomidas.usuarios.domain.api;

import com.plazadecomidas.usuarios.domain.model.Role;

import java.util.List;

public interface IRoleServicePort {
    Role saveRole(Role role);
    List<Role> getAllRoles();
    Role getRole(Long id);
    void deleteRole(Long id);
}
