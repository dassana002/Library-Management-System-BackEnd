package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto.LibStaffDTO;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto.Role;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Service.StaffService;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Utility.EntityDTOConversion;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Utility.UtilityData;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.anotations.TransactionalService;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.dao.StaffDao;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.entities.StaffEntity;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.exception.BookNotFoundException;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.exception.StaffNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@TransactionalService // @Service annotation combined with @Transactional annotation (CUSTOM Annotation)
@RequiredArgsConstructor
public class StaffServiceIIMPL implements StaffService {

    private final StaffDao staffDao;
    private final EntityDTOConversion entityDTOConversion;


    @Override
    public void saveStaffMember(LibStaffDTO staffDTO) {
        staffDTO.setStaffId(UtilityData.generateStaffId());
        staffDTO.setJoinDate(UtilityData.generateTodayDate());

        staffDao.save(entityDTOConversion.toStaffEntity(staffDTO));
    }

    @Override
    public void updateStaffMember(String staffId, LibStaffDTO staffDTO) {
        Optional<StaffEntity> foundStaff = staffDao.findById(staffId);

        if (!foundStaff.isPresent()) {
            throw new BookNotFoundException("Staff not found");
        }

        //foundStaff.get().setStaffId(staffDTO.getStaffId());
        foundStaff.get().setFirstName(staffDTO.getFirstName());
        foundStaff.get().setLastName(staffDTO.getLastName());
        foundStaff.get().setEmail(staffDTO.getEmail());
        foundStaff.get().setJoinDate(staffDTO.getJoinDate());
        foundStaff.get().setLastUpdated(UtilityData.generateTodayDate());
        foundStaff.get().setRole(staffDTO.getRole());
    }

    @Override
    public void deleteStaffMember(String staffId) {
        Optional<StaffEntity> founder = staffDao.findById(staffId);

        if (!founder.isPresent()) {
            throw new StaffNotFoundException("StaffId not found");
        }

        staffDao.deleteById(staffId);
    }

    @Override
    public LibStaffDTO getSelectedStaffMember(String staffId) {
        Optional<StaffEntity> foundStaff = staffDao.findById(staffId);

        if (!foundStaff.isPresent()) {
            throw new StaffNotFoundException("StaffId not found");
        }

        return entityDTOConversion.toLibStaffDTO(staffDao.getReferenceById(staffId));
    }

    @Override
    public List<LibStaffDTO> getAllStaffMembers() {
        return entityDTOConversion.toLibStaffDTOList(staffDao.findAll());
    }
}
