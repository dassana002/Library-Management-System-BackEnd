package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Contoller;

import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto.BookDTO;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Service.BookService;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.entities.BookEntity;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.exception.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping(value = "/addBook", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addBook(@RequestBody BookDTO bookDTO){
        bookService.saveBook(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable String bookId){
        try {
            bookService.deleteBook(bookId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (BookNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("{bookId}")
    public ResponseEntity<Void> updateBook(@PathVariable String bookId,@RequestBody BookDTO bookDTO){
        System.out.println("bookId:"+bookId);
        try{
           bookService.updateBook(bookId,bookDTO);
           return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (BookNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
//
//    @PatchMapping(value = "/{bookId}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> updateBook(@PathVariable("bookId") String id, @RequestBody BookDTO bookDTO) {
//        try {
//            bookService.updateBook(id, bookDTO);
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        } catch (BookNotFoundException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }


    @GetMapping(value = "/{bookID}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> getBook(@PathVariable String bookID){
        try {
            return ResponseEntity.ok(bookService.getBook(bookID));
        }catch (BookNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAllBook")
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }
}
