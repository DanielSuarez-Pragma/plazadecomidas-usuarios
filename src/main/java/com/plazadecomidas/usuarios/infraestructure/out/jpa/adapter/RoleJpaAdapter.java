package com.plazadecomidas.usuarios.infraestructure.out.jpa.adapter;

import com.plazadecomidas.usuarios.domain.model.Role;
import com.plazadecomidas.usuarios.domain.spi.IRolePersistencePort;
import com.plazadecomidas.usuarios.infraestructure.exception.NoDataFoundException;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.entity.RoleEntity;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.mapper.RoleEntityMapper;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {
    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Override
    public Role getRoleById(Long id) {
        return roleEntityMapper.toRole(roleRepository.findById(id).orElseThrow(NoDataFoundException::new));
    }

    @Override
    public Role saveRole(Role role) {
        return roleEntityMapper.toRole(roleRepository.save(roleEntityMapper.toEntity(role)));
    }

    @Override
    public List<Role> getAllRoles() {
        List<RoleEntity> roleEntityList = roleRepository.findAll();
        if (roleEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        return roleEntityMapper.toRoleList(roleEntityList);
    }
}
