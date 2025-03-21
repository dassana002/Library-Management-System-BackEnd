package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Service.Impl;

import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto.LendingDTO;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Service.LendingService;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Utility.LendingMapping;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Utility.UtilityData;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.anotations.TransactionalService;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.dao.BookDao;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.dao.LendingDao;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.dao.MemberDao;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@TransactionalService  // @Service annotation combiend with @Transactional annotation (CUSTOM Annotation)
@RequiredArgsConstructor
public class LendingServiceIMPL implements LendingService {

    private final LendingDao lendingDao;
    private final MemberDao memberDao;
    private final BookDao bookDao;

    @Value("${perDayFine}")
    private Double perDayFine;

    @Override
    public void addLending(LendingDTO lendingDTO) {
        lendingDTO.setLendingId(UtilityData.generateLendingId());

        String book = lendingDTO.getBook();
        String member = lendingDTO.getMember();

        //Availability of the book
        var bookEntity =  bookDao.findById(book).orElseThrow(()->
                new BookNotFoundException("Book not found"));

        // Membership validation
        var memberEntity = memberDao.findById(member).orElseThrow(()->
                new MemberNotFoundException("Member not found"));


        //chack the avilQty
        if(bookDao.avlQty(book) > 0){
            //Books are available

            lendingDTO.setLendingId(UtilityData.generateLendingId());
            lendingDTO.setLendingDate(UtilityData.generateTodayDate());
            lendingDTO.setReturnDate(UtilityData.generateReturnDate());

            lendingDTO.setIsActive(true);
            lendingDTO.setOverDue(0L);
            lendingDTO.setFineAmount(0.00);

            lendingDao.save(LendingMapping.toLendingEntity(lendingDTO,bookEntity,memberEntity)); //--------> static LendingMapping
            bookDao.deductBookQtyBasedOnLending(book); //-------> Qty eka adukranagannawa

        }else {
            throw new EnoughBooksNotFoundException("Not enough books to proceed");
        }

    }
//
//    @Override
//    public void handOverLending(String lendingID) {
//       System.out.println("Handing over lending: " + lendingID);
//
//        var foundLending =
//                lendingDao.findById(lendingID).orElseThrow(() -> new LendingNotFoundException("Lending record not found"));
//
//        System.out.println("lending is "+foundLending.getIsActive());
//        if (foundLending != null && foundLending.getIsActive()) {
//
//            throw new LendingAllreadyException("This book is already lent out!");
//        }
//
//
//        var returnDate = foundLending.getReturnDate();
//        var overdue = calcOverdue(returnDate); //overdue day count
//        var fineAmount = calcFineAmount(overdue);
//
//        foundLending.setIsActive(false);
//        foundLending.setOverDue(overdue);
//        foundLending.setFineAmount(fineAmount);
//
//        bookDao.addBookAfterHandover(foundLending.getBook().getBookId());
//    }

    public void handOverLending(String lendingID) {
        System.out.println("Handing over lending: " + lendingID);

        var foundLending = lendingDao.findById(lendingID)
                .orElseThrow(() -> new LendingNotFoundException("Lending record not found"));

        System.out.println("lending is "+foundLending.getIsActive());

        if (foundLending.getIsActive()) {
            throw new LendingAllreadyException("This book is already lent out!");
        }

        var returnDate = foundLending.getReturnDate();
        var overdue = calcOverdue(returnDate);
        var fineAmount = calcFineAmount(overdue);

        foundLending.setIsActive(false);
        foundLending.setOverDue(overdue);
        foundLending.setFineAmount(fineAmount);

        bookDao.addBookAfterHandover(foundLending.getBook().getBookId());
    }

    @Override
    public void deleteLending(String lendingID) {
        lendingDao.findById(lendingID).orElseThrow(()->
                new LendingNotFoundException("Lending record not found"));

        lendingDao.deleteById(lendingID);
    }

    @Override
    public LendingDTO getSpecificLending(String lendingID) {
        var lendingEntity = lendingDao.findById(lendingID).orElseThrow(()->
                new LendingNotFoundException("Lending record not found"));

        return LendingMapping.toLendingDTO(lendingEntity);
    }

    @Override
    public List<LendingDTO> getAllLendings() {
        return LendingMapping.toLendingDTOList(lendingDao.findAllLendings());
    }

    private Long calcOverdue(LocalDate returnDate) {
        var today = UtilityData.generateTodayDate();

        if(returnDate.isBefore(today)){
            return ChronoUnit.DAYS.between(returnDate,today);
        }
        return 0L;
    }

    private Double calcFineAmount(Long overdue) {
        //double perDayFine = 5.00;
        return overdue * perDayFine;
    }
}
