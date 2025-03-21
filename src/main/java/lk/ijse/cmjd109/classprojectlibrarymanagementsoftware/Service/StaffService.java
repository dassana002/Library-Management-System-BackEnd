package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Service;

import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto.LibStaffDTO;

import java.util.List;

public interface StaffService {
    void saveStaffMember(LibStaffDTO staffDTO);

    void updateStaffMember(String staffId, LibStaffDTO staffDTO);

    void deleteStaffMember(String staffId);

    LibStaffDTO getSelectedStaffMember(String staffId);

    List<LibStaffDTO> getAllStaffMembers();
}
