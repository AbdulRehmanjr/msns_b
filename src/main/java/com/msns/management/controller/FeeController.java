package com.msns.management.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msns.management.model.Fee;
import com.msns.management.service.FeeService;



@RestController
@RequestMapping("/fee")
public class FeeController {

    @Autowired
    private FeeService feeService;

    /**
     * This function creates a new fee and returns a response entity with the created fee if
     * successful, or a bad request status if unsuccessful.
     * 
     * @param fee The "fee" parameter is an object of type "Fee" that is received in the request body.
     * @return The method is returning a ResponseEntity object.
     */
    @PostMapping("/save")
    ResponseEntity<?> createFee(@RequestBody Fee fee){
        Fee response = this.feeService.createFee(fee);
        if(response!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /**
     * The function returns all fees as a response entity, with a status of CREATED if successful or
     * BAD_REQUEST if unsuccessful.
     * 
     * @return The method is returning a ResponseEntity object.
     */
    @GetMapping("/all")
    ResponseEntity<?> getAllFees(){
        List<Fee> response = this.feeService.getAllFee();
        if(response!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /**
     * This function retrieves a fee by its ID and returns a response entity with the fee if found, or
     * a bad request status if not found.
     * 
     * @param feeId The feeId is a path variable that represents the unique identifier of a fee.
     * @return The method is returning a ResponseEntity object.
     */
    @GetMapping("/{feeId}")
    ResponseEntity<?> getFeeById(@PathVariable int feeId){
        Fee response = this.feeService.getFeeById(feeId);
        if(response!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    /**
     * The function updates a fee and returns a response entity with the updated fee if successful, or
     * a bad request status if unsuccessful.
     * 
     * @param fee The "fee" parameter is an object of type "Fee" that is received in the request body.
     * @return The method is returning a ResponseEntity object.
     */
    @PatchMapping("/update")
    ResponseEntity<?> updateFee(@RequestBody Fee fee){
        Fee response = this.feeService.updateFee(fee);
        if(response!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
