package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Contoller;

import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Dto.MemberDTO;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.Service.MemberService;
import lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MembersController {

    private final MemberService memberService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addMember(@RequestBody MemberDTO memberDTO){
        memberService.saveMember(memberDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<Void> updateMember(@PathVariable String memberId, @RequestBody MemberDTO memberDTO){

        try {
            memberService.updateMember(memberId, memberDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (MemberNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable String memberId){

        try {
            memberService.deleteMember(memberId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (MemberNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{memberId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberDTO> getSelectedMember(@PathVariable String memberId){

        try {
             return new ResponseEntity<>(
                     memberService.getSelectedMember(memberId),
                     HttpStatus.OK);
         } catch (MemberNotFoundException e) {
             e.printStackTrace();
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

         } catch (Exception e) {
             e.printStackTrace();
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MemberDTO>> getAllMembers(){
        return new ResponseEntity<>(
                memberService.getAllMembers(),
                HttpStatus.OK);
    }
}
