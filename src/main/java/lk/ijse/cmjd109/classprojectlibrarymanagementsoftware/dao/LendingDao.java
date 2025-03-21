package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.dao;

import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.entities.LendingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LendingDao extends JpaRepository<LendingEntity,String> {
    @Query("SELECT l FROM LendingEntity l JOIN FETCH l.book JOIN FETCH l.member")
    List<LendingEntity> findAllLendings();



}
