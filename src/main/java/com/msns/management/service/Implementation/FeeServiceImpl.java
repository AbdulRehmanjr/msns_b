package com.msns.management.service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msns.management.model.Fee;
import com.msns.management.repository.FeeRepository;
import com.msns.management.service.FeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FeeServiceImpl implements FeeService{

    @Autowired
    private FeeRepository feeRepo;


    /**
     * The createFee function saves a Fee object to the feeRepo and returns the saved Fee object if
     * successful.
     * 
     * @param fee The parameter "fee" is an object of type "Fee" that represents the fee structure to
     * be created.
     * @return The method is returning a Fee object.
     */
    @Override
    public Fee createFee(Fee fee) {
        Fee response = this.feeRepo.save(fee);
        if(response !=null){
            log.info("Fee Structure saved.");
            return response;
        }
        return null;
    }

    @Override
    public List<Fee> getAllFee() {
        log.info("Fetching All Fee structures");
        return this.feeRepo.findAll();
    }

    /**
     * The function retrieves a fee structure by its ID from a repository and returns it, or null if it
     * doesn't exist.
     * 
     * @param feeId The feeId parameter is an integer that represents the unique identifier of a fee
     * structure.
     * @return The method is returning an object of type Fee.
     */
    @Override
    public Fee getFeeById(int feeId) {
        Fee response = this.feeRepo.findById(feeId).orElse(null);
        if(response!=null){
            log.info("Fetched fee structure By Id {}",feeId);
            return response;
        }
        return null;
    }

    /**
     * The function updates a fee structure and returns the updated fee.
     * 
     * @param fee The fee object that needs to be updated.
     * @return The method is returning an object of type Fee.
     */
    @Override
    public Fee updateFee(Fee fee) {
       Fee response = this.feeRepo.save(fee);
        if(response !=null){
            log.info("Fee Structure Updated.");
            return response;
        }
        return null;
    }
    
}
