package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class StaffEntity {

    @Id
    private String staffId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate joinDate;
    private LocalDate lastUpdated;
    private Role role;
}
