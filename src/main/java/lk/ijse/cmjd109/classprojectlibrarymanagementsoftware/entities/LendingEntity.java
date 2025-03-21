package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "lending")
public class LendingEntity {

    @Id
    private String lendingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId", nullable = false)
    @ToString.Exclude
    private BookEntity book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", nullable = false)
    @ToString.Exclude
    private MemberEntity member;

    private LocalDate lendingDate;
    private LocalDate returnDate;
    private Boolean isActive;
    private Long overDue;
    private Double fineAmount;

    @Override
    public String toString() {
        return "LendingEntity{" +
                "lendingId='" + lendingId + '\'' +
                ", lendingDate=" + lendingDate +
                ", returnDate=" + returnDate +
                ", isActive=" + isActive +
                ", overDue=" + overDue +
                ", fineAmount=" + fineAmount +
                ", bookId=" + (book != null ? book.getBookId() : "null") +  // 🛑 Avoid loading full BookEntity
                ", memberId=" + (member != null ? member.getMemberId() : "null") +  // 🛑 Avoid loading full MemberEntity
                '}';
    }


}
