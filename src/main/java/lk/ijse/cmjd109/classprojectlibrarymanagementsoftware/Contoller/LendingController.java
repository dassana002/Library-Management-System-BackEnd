package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Contoller;

import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto.LendingDTO;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Service.LendingService;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.exception.BookNotFoundException;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.exception.EnoughBooksNotFoundException;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.exception.LendingNotFoundException;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.exception.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lending")
@RequiredArgsConstructor
public class LendingController {

    private LendingService lendingService;

    @Autowired
    public LendingController(LendingService lendingService) {
        this.lendingService = lendingService;
    }

    @PostMapping
    public ResponseEntity<Void> addLending(@RequestBody LendingDTO lendingDTO){

        if(lendingDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            lendingService.addLending(lendingDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);

        }catch (BookNotFoundException | MemberNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }catch (EnoughBooksNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{lendingId}")
    public ResponseEntity<Void> handoverBook(@PathVariable String lendingId){

        if(lendingId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            lendingService.handOverLending(lendingId);
            return new ResponseEntity<>(HttpStatus.CREATED);

        }catch (LendingAllreadyException | LendingNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{lendingId}")
    public ResponseEntity<Void> deleteLending(@PathVariable String lendingId){
        try {
            lendingService.deleteLending(lendingId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (BookNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping ("/{lendingId}")
    public ResponseEntity<LendingDTO> getSpecificLending(@PathVariable String lendingId){

        try {
            return new ResponseEntity<>(lendingService.getSpecificLending(lendingId),HttpStatus.OK);

        } catch (LendingNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LendingDTO>> getAllLending(){
//        lendingService.getAllLendings();
        return new ResponseEntity<>(lendingService.getAllLendings(),HttpStatus.OK);
    }
}
