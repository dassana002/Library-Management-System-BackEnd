package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Service;

import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto.BookDTO;

import java.util.List;


public interface BookService {
    void saveBook(BookDTO book);
    void deleteBook(String bookId);
    void updateBook(String id,BookDTO book);
    BookDTO getBook(String bookId);
    List<BookDTO> getAllBooks();
}
