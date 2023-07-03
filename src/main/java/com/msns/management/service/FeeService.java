package com.msns.management.service;

import java.util.List;

import com.msns.management.model.Fee;

public interface FeeService {
    
    /**
     * The function creates a new fee object and returns it.
     * 
     * @param fee The parameter "fee" is of type Fee, which means it is an object of the Fee class.
     * @return a Fee object.
     */
    Fee createFee(Fee fee);

    /**
     * The function getAllFee() returns a list of Fee objects.
     * 
     * @return A List of Fee objects is being returned.
     */
    List<Fee> getAllFee();

    /**
     * The function "getFeeById" returns a Fee object based on the given feeId.
     * 
     * @param feeId An integer representing the unique identifier of the fee.
     * @return The method getFeeById is returning an object of type Fee.
     */
    Fee getFeeById(int feeId);

    /**
     * The function updateFee takes a Fee object as input and returns an updated Fee object.
     * 
     * @param fee The parameter "fee" is of type "Fee", which means it is an object or instance of the
     * class "Fee".
     * @return a Fee object.
     */
    Fee updateFee(Fee fee);
}
