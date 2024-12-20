package com.plazadecomidas.usuarios.domain.spi;

import com.plazadecomidas.usuarios.domain.model.Role;

import java.util.List;

public interface IRolePersistencePort {
    Role getRoleById(Long id);
    Role saveRole(Role role);
    List<Role> getAllRoles();
    void deleteRoleById(Long id);
}
