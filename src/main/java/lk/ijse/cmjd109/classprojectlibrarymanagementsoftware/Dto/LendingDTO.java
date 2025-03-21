package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class LendingDTO implements Serializable {
    private String lendingId;
    private String book;
    private String member;
    private LocalDate lendingDate;
    private LocalDate returnDate;
    private Boolean isActive;
    private Long overDue;
    private Double fineAmount;
}
