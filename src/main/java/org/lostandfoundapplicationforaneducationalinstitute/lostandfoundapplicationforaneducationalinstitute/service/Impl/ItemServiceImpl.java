package org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.dao.ItemDao;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.dto.ItemDTO;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.entity.ItemEntity;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.service.ItemService;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.utility.EntitiyDTOConvertion;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.utility.UtilityData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;
    private final EntitiyDTOConvertion entitiyDTOConvertion;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        itemDTO.setItemId(UtilityData.generateItemId());
        itemDTO.setCreatedDate(UtilityData.generateCurrentDate());

        itemDao.save(entitiyDTOConvertion.toItemEntity(itemDTO));
    }

    @Override
    public List<ItemDTO> getAll() {
        List<ItemEntity> itemEntities = itemDao.findAll();
        return entitiyDTOConvertion.toItemDTOList(itemEntities);
    }

    @Override
    public void updateItem(ItemDTO itemDTO, String itemiId) {
        Optional<ItemEntity> foundItem = itemDao.findById(itemiId);

        if (!foundItem.isPresent()) {
            throw new RuntimeException();
        }

       // foundItem.get().setItemId(itemDTO.getItemId());
        foundItem.get().setName(itemDTO.getName());
        foundItem.get().setDescription(itemDTO.getDescription());
        foundItem.get().setCreatedDate(UtilityData.generateCurrentDate());
        foundItem.get().setStatus(itemDTO.getStatus());

        // Update item , @Transactional annotation used
    }

    @Override
    public void deleteItem(String itemId) {
        Optional<ItemEntity> foundItem = itemDao.findById(itemId);

        if (!foundItem.isPresent()) {
            throw new RuntimeException();
        }

        itemDao.deleteById(itemId);
    }

}
