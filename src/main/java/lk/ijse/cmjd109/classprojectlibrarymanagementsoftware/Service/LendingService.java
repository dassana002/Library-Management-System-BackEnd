package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Service;

import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto.LendingDTO;

import java.util.List;

public interface LendingService {
    void addLending(LendingDTO lendingDTO);
    void handOverLending(String lendingID);
    void deleteLending(String lendingID);
    LendingDTO getSpecificLending(String lendingID);
    List<LendingDTO> getAllLendings();
}
