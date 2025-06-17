package org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.utility;

import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.dto.ItemDTO;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.dto.RequestDTO;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.dto.UserDTO;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.entity.ItemEntity;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.entity.RequestEntity;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.entity.UserEntity;
import org.modelmapper.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class RequestMapping {

    // Request
    public static RequestEntity toRequestEntity(RequestDTO requestDTO, ItemEntity itemEntity, UserEntity userEntity) {
        var requestEntity = new RequestEntity();

        requestEntity.setRequestId(requestDTO.getRequestID());
        requestEntity.setItem(itemEntity);
        requestEntity.setUser(userEntity);
        requestEntity.setStatus(requestDTO.getStatus());
        requestEntity.setRequestDate(requestDTO.getRequestDate());

        return requestEntity;
    }

    public static RequestDTO toRequestDTO(RequestEntity requestEntity) {
        var requestDTO = new RequestDTO();

        requestDTO.setRequestID(requestEntity.getRequestId());
        requestDTO.setItem(requestEntity.getItem().getItemId());
        requestDTO.setUser(requestEntity.getUser().getUserId());
        requestDTO.setStatus(requestEntity.getStatus());
        requestDTO.setRequestDate(requestEntity.getRequestDate());

        return requestDTO;
    }


    public static List<RequestDTO> toRequestDTOList(List<RequestEntity> requestEntities) {
        var lendingDTOList = new ArrayList<RequestDTO>();

        for (var requestEntity : requestEntities) {
            lendingDTOList.add(new RequestDTO(
                requestEntity.getRequestId(),
                requestEntity.getItem().getItemId(),
                requestEntity.getUser().getUserId(),
                requestEntity.getStatus(),
                requestEntity.getRequestDate()
            ));
        }
        return lendingDTOList;
    }
}
