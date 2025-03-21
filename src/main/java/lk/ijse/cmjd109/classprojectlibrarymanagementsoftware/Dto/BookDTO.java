package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto;

import lombok.*;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class BookDTO implements Serializable {
    private String bookId;
    private String title;
    private String publisher;
    private String isbn;
    private String author;
    private String edition;
    private Double price;
    private Integer totalQty;
    private Integer avilableQty;
    private LocalDate lastUpdatedDate;
    private Time lastUpdatedTime;
}
