package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.dao;

import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends JpaRepository<BookEntity, String> {

    //JPQL
    @Query("SELECT SUM(b.avilableQty) FROM BookEntity b WHERE b.bookId = :bookId")
    int avlQty(@Param("bookId") String bookId);

    @Modifying
    @Query("UPDATE BookEntity b  SET b.avilableQty = b.avilableQty +1 WHERE b.bookId = :bookId")
    void addBookAfterHandover(@Param("bookId") String bookId);


    @Modifying
    @Query("UPDATE BookEntity b  SET b.avilableQty = b.avilableQty -1 WHERE b.bookId = :bookId AND b.avilableQty > 0")
    void deductBookQtyBasedOnLending(@Param("bookId") String bookId);

}
