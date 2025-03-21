package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.dao;

import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.entities.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao extends JpaRepository<MemberEntity, String> {
}
