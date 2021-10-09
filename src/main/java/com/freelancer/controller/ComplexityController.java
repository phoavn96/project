package com.freelancer.controller;

import com.freelancer.model.Complexity;
import com.freelancer.model.ResponseObject;
import com.freelancer.service.ComplexityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/complexities")
public class ComplexityController {
    @Autowired
    private ComplexityService complexityService;

    //get All/SEARCH
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ResponseObject> search(@RequestParam String keysearch, @PathVariable int page,
                                                 @PathVariable int size) {
        ResponseObject result = complexityService.search(keysearch, page, size);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //Get 1 by id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable Long id) {

        ResponseObject result = complexityService.getById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);

    }

    //Create
    @PostMapping
    public ResponseEntity<ResponseObject> add(@RequestBody Complexity complexity) {
        ResponseObject result = complexityService.save(complexity);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@RequestBody Complexity complexity, @PathVariable Long id) {

            ResponseObject result = complexityService.update(complexity,id);
            return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable Long id) {
        ResponseObject result = complexityService.delete(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
