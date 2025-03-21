package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto.MemberDTO;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Service.MemberService;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Utility.EntityDTOConversion;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Utility.UtilityData;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.anotations.TransactionalService;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.dao.MemberDao;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.entities.MemberEntity;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@TransactionalService // @Service annotation combined with @Transactional annotation (CUSTOM Annotation)
@RequiredArgsConstructor
public class MemberServiceIMPL implements MemberService {

    private final MemberDao memberDao;
    private final EntityDTOConversion entityDTOConversion;

    @Override
    public void saveMember(MemberDTO memberDTO) {
//        System.out.println(memberDTO.getFirstname() + " " + memberDTO.getLastname());

        memberDTO.setMemberId(UtilityData.generateMemberId());
        memberDTO.setMemberShipDate(UtilityData.generateTodayDate());

        memberDao.save(entityDTOConversion.toMemberEntity(memberDTO));
    }

    @Override
    public MemberDTO getSelectedMember(String memberId) {
        Optional<MemberEntity> foundMember = memberDao.findById(memberId);

        if (!foundMember.isPresent()) {
            throw new MemberNotFoundException("Member not found");
        }

        return entityDTOConversion.toMemberDTO(memberDao.getReferenceById(memberId));
    }

    @Override
    public void deleteMember(String memberId) {
        Optional<MemberEntity> foundMember = memberDao.findById(memberId);

        if (!foundMember.isPresent()) {
            throw new MemberNotFoundException("Member not found");
        }

        memberDao.deleteById(memberId);
    }

    @Override
    public void  updateMember(String memberId, MemberDTO memberDTO) {
        Optional<MemberEntity> foundMember = memberDao.findById(memberId);

        if (!foundMember.isPresent()) {
            throw new MemberNotFoundException("Member not found");
        }

        foundMember.get().setFirstName(memberDTO.getFirstname());
        foundMember.get().setLastName(memberDTO.getLastname());
        foundMember.get().setEmail(memberDTO.getEmail());
        foundMember.get().setMemberShipDate(memberDTO.getMemberShipDate());
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        return entityDTOConversion.toMemberDTOList(memberDao.findAll());
    }
}
