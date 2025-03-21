package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto.BookDTO;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Service.BookService;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Utility.EntityDTOConversion;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Utility.UtilityData;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.anotations.TransactionalService;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.dao.BookDao;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.entities.BookEntity;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.exception.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@TransactionalService  // @Service annotation combined with @Transactional annotation (CUSTOM Annotation)
@RequiredArgsConstructor
public class BookServiceIMPL implements BookService {

    private final BookDao bookDao;
    private final EntityDTOConversion entityDTOConversion;

    @Override
    public void saveBook(BookDTO book) {
       book.setBookId(UtilityData.generateBookId());
       book.setLastUpdatedDate(UtilityData.generateTodayDate());
       book.setLastUpdatedTime(UtilityData.generateCreatedTime());

//       BookEntity bookEntity = entityDTOConversion.toBookEntity(book);
//       bookDao.save(bookEntity);
       bookDao.save(entityDTOConversion.toBookEntity(book));

    }

    @Override
    public void deleteBook(String bookId) {
        Optional<BookEntity> foundBook = bookDao.findById(bookId);

        if(!foundBook.isPresent()){
            throw new BookNotFoundException("Book not found");
        }

        bookDao.deleteById(bookId);
    }

    @Override
    public void updateBook(String id, BookDTO book) {
        Optional<BookEntity> foundBook = bookDao.findById(id);
        System.out.println("Book"+ foundBook.get().getBookId());

        if(!foundBook.isPresent()){
            throw new BookNotFoundException("Book not found");
        }

        foundBook.get().setTitle(book.getTitle());
        foundBook.get().setPublisher(book.getPublisher());
        foundBook.get().setIsbn(book.getIsbn());
        foundBook.get().setAuthor(book.getAuthor());
        foundBook.get().setEdition(book.getEdition());
        foundBook.get().setPrice(book.getPrice());
        foundBook.get().setAvilableQty(book.getAvilableQty());
        foundBook.get().setTotalQty(book.getTotalQty());
        foundBook.get().setLastUpdatedDate(UtilityData.generateTodayDate());
        foundBook.get().setLastUpdatedTime(UtilityData.generateCreatedTime());

    }

    @Override
    public BookDTO getBook(String bookId) {
        Optional<BookEntity> foundBook = bookDao.findById(bookId);

        if(!foundBook.isPresent()){
            throw new BookNotFoundException("Book not found");
        }

        return entityDTOConversion.toBookDTO(bookDao.getReferenceById(bookId));
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<BookEntity> allBooks = bookDao.findAll();
        return entityDTOConversion.toBookDTOList(allBooks);
    }
}
