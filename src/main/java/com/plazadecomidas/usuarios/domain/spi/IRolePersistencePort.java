package com.plazadecomidas.usuarios.domain.spi;

import com.plazadecomidas.usuarios.domain.model.Role;

public interface IRolePersistencePort {
    Role findRoleByName(String roleName);
}
