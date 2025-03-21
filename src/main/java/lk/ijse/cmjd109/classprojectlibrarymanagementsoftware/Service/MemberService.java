package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Service;

import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto.MemberDTO;

import java.util.List;

public interface MemberService {

    void saveMember(MemberDTO memberDTO);

    MemberDTO getSelectedMember(String memberId);

    void deleteMember(String memberId);

    void  updateMember(String memberId, MemberDTO memberDTO);

    List<MemberDTO> getAllMembers();
}
