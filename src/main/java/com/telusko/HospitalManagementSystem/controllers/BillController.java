package com.telusko.HospitalManagementSystem.controllers;

import com.telusko.HospitalManagementSystem.model.Bill;
import com.telusko.HospitalManagementSystem.model.Patient;
import com.telusko.HospitalManagementSystem.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bills")
public class BillController
{
    @Autowired
    private BillService billService;

    @GetMapping()
    public List<Bill> getAllBills()
    {
        return billService.getAllBills();
    }

    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable Long id)
    {
        return billService.getBillById(id);
    }

    @PostMapping()
    public Bill createBill(@RequestBody Bill bill)
    {
        return billService.createBill(bill);
    }

    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable Long id)
    {
        billService.deleteBill(id);
    }

    @PutMapping("/{id}")
    public Bill updateBill(@PathVariable Long id , @RequestBody Bill updatedBill)
    {
        return billService.updateBill(id , updatedBill);
    }
}
