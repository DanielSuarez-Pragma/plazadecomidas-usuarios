package com.plazadecomidas.usuarios.domain.usecase;

import com.plazadecomidas.usuarios.domain.api.IRoleServicePort;
import com.plazadecomidas.usuarios.domain.model.Role;
import com.plazadecomidas.usuarios.domain.spi.IRolePersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleUseCase implements IRoleServicePort {

    private final IRolePersistencePort rolePersistencePort;

    public RoleUseCase(IRolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public Role saveRole(Role role) {
        return rolePersistencePort.saveRole(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return rolePersistencePort.getAllRoles();
    }

    @Override
    public Role getRole(Long id) {
        return rolePersistencePort.getRoleById(id);
    }

    @Override
    public void deleteRole(Long id) {
        rolePersistencePort.deleteRoleById(id);
    }
}
