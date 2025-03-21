package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Contoller;

import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto.LibStaffDTO;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Service.StaffService;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.exception.StaffNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
@RequiredArgsConstructor
public class LibStaffController {

    private final StaffService staffService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addStaffMember(@RequestBody LibStaffDTO staffDTO){
        staffService.saveStaffMember(staffDTO);
        //return new ResponseEntity<>(HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{staffId}")
    public ResponseEntity<Void> updateStaffMember(@PathVariable String staffId, @RequestBody LibStaffDTO staffDTO){

        try {
            staffService.updateStaffMember(staffId, staffDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        } catch (StaffNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{staffId}")
    public ResponseEntity<Void> deleteMember(@PathVariable String staffId){

        try{
            staffService.deleteStaffMember(staffId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (StaffNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{staffId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LibStaffDTO> getSelectedStaffMember(@PathVariable String staffId){

        try {
            return new ResponseEntity<>(
                    staffService.getSelectedStaffMember(staffId),
                    HttpStatus.OK);
        } catch (StaffNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LibStaffDTO>> getAllStaffMembers(){
//        return new ResponseEntity<>(
//                staffService.getAllStaffMembers(),
//                HttpStatus.OK);
//
        return ResponseEntity.ok(staffService.getAllStaffMembers());
    }
}
