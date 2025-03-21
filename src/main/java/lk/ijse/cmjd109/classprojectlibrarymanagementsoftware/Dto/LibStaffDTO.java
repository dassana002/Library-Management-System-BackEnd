package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LibStaffDTO implements Serializable {
    private String staffId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate joinDate;
    private LocalDate lastUpdated;
    private Role role;
}
