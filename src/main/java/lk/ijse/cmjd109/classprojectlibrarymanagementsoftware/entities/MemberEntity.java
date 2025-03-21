package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "member")
public class MemberEntity {

    @Id
    private String memberId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate memberShipDate;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<LendingEntity> lendings;
}