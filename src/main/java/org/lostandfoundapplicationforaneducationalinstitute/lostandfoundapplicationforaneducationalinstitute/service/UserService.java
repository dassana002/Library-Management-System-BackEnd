package org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.service;

import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.dto.UserDTO;

import java.util.List;

public interface UserService {
    void save(UserDTO user);

    List<UserDTO> getAll();

    void update(UserDTO user, String userId);

    void delete(String userId);

    UserDTO getUser(String userId);
}
