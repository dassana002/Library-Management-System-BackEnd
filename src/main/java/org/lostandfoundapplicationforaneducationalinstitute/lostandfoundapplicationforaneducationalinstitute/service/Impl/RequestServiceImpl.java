package org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.service.Impl;

import lombok.RequiredArgsConstructor;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.dao.ItemDao;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.dao.RequestDao;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.dao.UserDao;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.dto.RequestDTO;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.entity.ItemEntity;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.entity.RequestEntity;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.entity.UserEntity;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.service.RequestService;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.utility.EntitiyDTOConvertion;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.utility.RequestMapping;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.utility.UtilityData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final EntitiyDTOConvertion entityDTOConvertion;
    private final RequestDao requestDao;
    private final ItemDao itemDao;
    private final UserDao userDao;

    @Override
    public void saveRequest(RequestDTO requestDTO) {
        // Request Generate
        requestDTO.setRequestID(UtilityData.generateRequestId());

        // get Item and User
        String item = requestDTO.getItem();
        String user = requestDTO.getUser();

        // Item and User Validation
        var itemEntity = itemDao.findById(item).orElseThrow(() ->
                new RuntimeException("Item Not Found"));
        var userEntity = userDao.findById(user).orElseThrow(() ->
                new RuntimeException("User Not Found"));

        ///////////////////////////////////////////////

        // Request Entity Generate
        requestDTO.setRequestDate(UtilityData.generateCurrentDate());

        requestDao.save(RequestMapping.toRequestEntity(requestDTO, itemEntity, userEntity));
   }

    @Override
    public List<RequestDTO> getAll() {
        return RequestMapping.toRequestDTOList(requestDao.findAll());
    }

    @Override
    public void updateRequest(RequestDTO requestDTO, String requestId) {
        Optional<RequestEntity> foundRequest = requestDao.findById(requestId);

        if (!foundRequest.isPresent()) {
            throw new RuntimeException("Request not found");
        }

        // get Item and User
        String item = requestDTO.getItem();
        String user = requestDTO.getUser();

        // Item and User Validation
        var itemEntity = itemDao.findById(item).orElseThrow(() ->
                new RuntimeException("Item Not Found"));
        var userEntity = userDao.findById(user).orElseThrow(() ->
                new RuntimeException("User Not Found"));

        foundRequest.get().setStatus(requestDTO.getStatus());
    }

}
