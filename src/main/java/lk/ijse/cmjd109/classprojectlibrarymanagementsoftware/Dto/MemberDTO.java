package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTO implements Serializable {
    private String memberId;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDate memberShipDate;

}
