package org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.dao.UserDao;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.dto.UserDTO;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.entity.UserEntity;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.service.UserService;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.utility.EntitiyDTOConvertion;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.utility.UtilityData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final EntitiyDTOConvertion entitiyDTOConvertion;

    @Override
    public void save(UserDTO user) {
        user.setUserID(UtilityData.generateUserId());
        userDao.save(entitiyDTOConvertion.toUserEntity(user));
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserEntity> userEntities = userDao.findAll();
        return entitiyDTOConvertion.toUserDTOList(userEntities);
    }

    @Override
    public void update(UserDTO user, String userId) {
        Optional<UserEntity> foundUser = userDao.findById(userId);

        if (!foundUser.isPresent()) {
            throw new RuntimeException();
        }

        foundUser.get().setName(user.getName());
        foundUser.get().setEmail(user.getEmail());
        foundUser.get().setPassword(user.getPassword());
        foundUser.get().setRole(user.getRole());

    }

    @Override
    public void delete(String userId) {
        Optional<UserEntity> foundUser = userDao.findById(userId);

        if (!foundUser.isPresent()) {
            throw new RuntimeException();
        }

        userDao.deleteById(userId);
    }

    @Override
    public UserDTO getUser(String userId) {
        Optional<UserEntity> foundUser = userDao.findById(userId);

        if (!foundUser.isPresent()) {
            throw new RuntimeException();
        }

        return entitiyDTOConvertion.toUserDTO(userDao.getReferenceById(userId));
    }
}
