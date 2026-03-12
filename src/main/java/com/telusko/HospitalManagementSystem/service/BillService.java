package com.telusko.HospitalManagementSystem.service;

import com.telusko.HospitalManagementSystem.model.Bill;
import com.telusko.HospitalManagementSystem.repository.AppointmentRepository;
import com.telusko.HospitalManagementSystem.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService
{
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Bill> getAllBills()
    {
        try
        {
            return billRepository.findAll();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Bill getBillById(Long id)
    {
        try
        {
            Optional<Bill> bill = billRepository.findById(id);

            if(bill.isPresent())
            {
                return bill.get();
            }
            else
            {
                throw new RuntimeException("Bill with id " + id + " not found");
            }
        }
        catch (Exception e) {
            throw e;
        }
    }

    public Bill createBill(Bill bill)
    {
        try
        {
            //Before creating a bill we must check that appointment exist or not

            if(!appointmentRepository.existsById(bill.getAppointmentId()))
            {
                throw new RuntimeException("Bill can not created bcz appointment with this id not exist");
            }

            //And bill already exist for that appointment
            if(billRepository.existsByAppointmentId(bill.getAppointmentId()))
            {
                throw new RuntimeException("Bill already exists for this appointment.");
            }

            Bill savedBill = billRepository.save(bill);
            return savedBill;

        }
        catch (Exception e) {
            throw e;
        }
    }


    public void deleteBill(Long id)
    {
        try
        {
            if(billRepository.existsById(id))
            {
                billRepository.deleteById(id);
            }
            else
            {
                throw new RuntimeException("Bill with id" + id + "not exist");
            }

        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public Bill updateBill(Long id ,  Bill updatedBill)
    {
        try
        {
            Optional<Bill> existingBill = billRepository.findById(id);

            if(existingBill.isPresent())
            {
                Bill newBill = existingBill.get();

                // validate appointment again
                if(!appointmentRepository.existsById(updatedBill.getAppointmentId()))
                {
                    throw new RuntimeException("Appointment does not exist.");
                }

                newBill.setAppointmentId(updatedBill.getAppointmentId());
                newBill.setAmount(updatedBill.getAmount());
                newBill.setStatus(updatedBill.getStatus());

                return billRepository.save(newBill);
            }
            else
            {
                throw new RuntimeException("Bill with id " + id + " does not exist");
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
