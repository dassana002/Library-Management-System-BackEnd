package org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.controller;

import lombok.RequiredArgsConstructor;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.dto.RequestDTO;
import org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.service.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/requests")
@RequiredArgsConstructor
public class RequestContoller {

    private final RequestService requestService;

    @GetMapping("/getAll")
    public ResponseEntity<List<RequestDTO>> getAll() {
        List<RequestDTO> requestDTOS = requestService.getAll();
        return new ResponseEntity<>(requestDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveRequest(@RequestBody RequestDTO requestDTO) {
        requestService.saveRequest(requestDTO);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{requestId}")
    public ResponseEntity<Void> updateRequest(@RequestBody RequestDTO requestDTO, @PathVariable String requestId) {
        try {
            requestService.updateRequest(requestDTO, requestId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
