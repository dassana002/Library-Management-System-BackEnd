package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.dao;

import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.entities.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffDao extends JpaRepository<StaffEntity,String> {

}
