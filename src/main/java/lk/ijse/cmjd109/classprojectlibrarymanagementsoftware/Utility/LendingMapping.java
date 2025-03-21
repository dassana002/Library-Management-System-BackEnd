package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Utility;

import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto.LendingDTO;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.entities.BookEntity;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.entities.LendingEntity;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.entities.MemberEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class LendingMapping {

    //Lending-----------------------> not used ModelMapper

    public static LendingDTO toLendingDTO(LendingEntity lendingEntity) {
        var lendingDTO = new LendingDTO();

        lendingDTO.setLendingId(lendingEntity.getLendingId());
        lendingDTO.setBook(lendingEntity.getBook().getBookId());
        lendingDTO.setMember(lendingEntity.getMember().getMemberId());
        lendingDTO.setReturnDate(lendingEntity.getReturnDate());
        lendingDTO.setLendingDate(lendingEntity.getLendingDate());
        lendingDTO.setFineAmount(lendingEntity.getFineAmount());
        lendingDTO.setOverDue(lendingEntity.getOverDue());
        lendingDTO.setIsActive(lendingEntity.getIsActive());

        return lendingDTO;
    }
    public static LendingEntity toLendingEntity(LendingDTO lendingDTO, BookEntity bookEntity, MemberEntity memberEntity) {
        var lendingEntity = new LendingEntity();

        lendingEntity.setLendingId(lendingDTO.getLendingId());
        lendingEntity.setBook(bookEntity);
        lendingEntity.setMember(memberEntity);
        lendingEntity.setReturnDate(lendingDTO.getReturnDate());
        lendingEntity.setLendingDate(lendingDTO.getLendingDate());
        lendingEntity.setFineAmount(lendingDTO.getFineAmount());
        lendingEntity.setOverDue(lendingDTO.getOverDue());
        lendingEntity.setIsActive(lendingDTO.getIsActive());

        return lendingEntity;
    }

    public static List<LendingDTO> toLendingDTOList(List<LendingEntity> lendingEntities) {
        var lendingDTOList = new ArrayList<LendingDTO>();

        for (LendingEntity lendingEntity : lendingEntities) {
            lendingDTOList.add(new LendingDTO(
                    lendingEntity.getLendingId(),
                    lendingEntity.getBook().getBookId(),
                    lendingEntity.getMember().getMemberId(),
                    lendingEntity.getLendingDate(),
                    lendingEntity.getReturnDate(),
                    lendingEntity.getIsActive(),
                    lendingEntity.getOverDue(),
                    lendingEntity.getFineAmount()
            ));
        }

        return lendingDTOList;
    }
}
