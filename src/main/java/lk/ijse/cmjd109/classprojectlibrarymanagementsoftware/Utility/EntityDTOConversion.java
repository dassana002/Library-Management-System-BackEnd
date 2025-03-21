package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Utility;

import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto.BookDTO;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto.LendingDTO;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto.LibStaffDTO;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto.MemberDTO;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.entities.BookEntity;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.entities.LendingEntity;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.entities.MemberEntity;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.entities.StaffEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.print.Book;
import java.util.List;

@Component

public class EntityDTOConversion {

    private final ModelMapper modelMapper;

    @Autowired
    public EntityDTOConversion(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    //Book
    public BookDTO toBookDTO(BookEntity book) {
        return modelMapper.map(book, BookDTO.class);
    }

    public BookEntity toBookEntity(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, BookEntity.class);
    }

    public List<BookDTO> toBookDTOList(List<BookEntity> books) {
        return modelMapper.map(books, new TypeToken<List<BookDTO>>(){}.getType());
    }

    //Staff
    public StaffEntity toStaffEntity(LibStaffDTO staffDTO) {
        return modelMapper.map(staffDTO, StaffEntity.class);
    }

    public LibStaffDTO toLibStaffDTO(StaffEntity staff) {
        return modelMapper.map(staff, LibStaffDTO.class);
    }

    public List<LibStaffDTO> toLibStaffDTOList(List<StaffEntity> staffs) {
        return modelMapper.map(staffs, new TypeToken<List<LibStaffDTO>>(){}.getType());
    }

    //Member
    public MemberEntity toMemberEntity(MemberDTO memberDTO) {
        return modelMapper.map(memberDTO, MemberEntity.class);
    }

    public MemberDTO toMemberDTO(MemberEntity member) {
        return modelMapper.map(member, MemberDTO.class);
    }

    public List<MemberDTO> toMemberDTOList(List<MemberEntity> members) {
        return modelMapper.map(members, new TypeToken<List<MemberDTO>>(){}.getType());
    }

    //User
//    public UserEntity toUserEntity(UserDTO userDTO) {
//        return modelMapper.map(userDTO, UserEntity.class);
//    }
//
//    public UserDTO toUserDTO(UserEntity user) {
//        return modelMapper.map(user, UserDTO.class);
//    }
}
