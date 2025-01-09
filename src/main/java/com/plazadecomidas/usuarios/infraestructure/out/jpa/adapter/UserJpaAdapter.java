package com.plazadecomidas.usuarios.infraestructure.out.jpa.adapter;

import com.plazadecomidas.usuarios.domain.model.User;
import com.plazadecomidas.usuarios.domain.spi.IUserPersistencePort;
import com.plazadecomidas.usuarios.infraestructure.exception.NoDataFoundException;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.entity.UserEntity;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.mapper.UserEntityMapper;
import com.plazadecomidas.usuarios.infraestructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;


    @Override
    public void saveUser(User user) {
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public User getUser(Long id) {
        return userEntityMapper.toUser(userRepository.findById(id).orElseThrow(NoDataFoundException::new));
    }

    @Override
    public User getUserByEmail(String email) {
        return userEntityMapper.toUser(userRepository.findUserEntityByEmail(email).orElseThrow(NoDataFoundException::new));
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        if (userEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        return userEntityMapper.toUserList(userEntityList);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
