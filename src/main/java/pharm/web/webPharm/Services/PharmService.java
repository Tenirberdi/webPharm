package pharm.web.webPharm.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pharm.web.webPharm.Exceptions.QuantityError;
import pharm.web.webPharm.Models.*;
import pharm.web.webPharm.Repositories.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@Service
public class PharmService {

    @Autowired
    private MedicineRepo medicineRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private SoldRepo soldRepo;

    @Autowired
    private OrderMedicineRepo orderMedicineRepo;

    @Autowired
    private OrganizationRepo organizationRepo;

    public List<MedicineEntity> getAllMedicines(){
        List<MedicineEntity> list =  medicineRepo.getAll(getOrganizationId());
        return list;
    }

    public MedicineEntity getSingleMedicine(int id){
        MedicineEntity medicine = medicineRepo.findById(id).get();

        return medicine;
    }

    public void addMedicine(MedicineEntity medicine){
        medicine.setOrganizationId(getOrganizationId());
        medicineRepo.save(medicine);
    }

    public List<OrderMedicineEntity> getOrderedList(){
        return (List<OrderMedicineEntity>) orderMedicineRepo.getAll(getOrganizationId());
    }

    public void updateMedicine(MedicineEntity medicine){
        medicineRepo.save(medicine);
    }

    public void deleteMedicine(int id){
        medicineRepo.deleteById(id);
    }

    public void orderMedicine(OrderMedicineEntity order) throws ParseException {
        OrderMedicineEntity o = orderMedicineRepo.getOrderByMedicineId(order.getMedicineId(), getOrganizationId());
        if(o == null){
            order.setPharmacistId(getCurrentUsersUserID());
            order.setOrderedDate(getCurrentDate());

            orderMedicineRepo.save(order);
        }else {
            o.setQuantity(o.getQuantity() + order.getQuantity());
            o.setPharmacistId(getCurrentUsersUserID());
            o.setOrderedDate(getCurrentDate());
            orderMedicineRepo.save(o);
        }


    }

    public List<OrderMedicineEntity> getOrderedMedicines(){
        return orderMedicineRepo.getAll(getOrganizationId());
    }

    public void deleteOrder(int id){
        orderMedicineRepo.deleteById(id);
    }

    public void sellMedicine(SoldEntity sold) throws ParseException, QuantityError {

        int quantity = medicineRepo.findById(sold.getMedicineId()).get().getId();

        if(quantity == 0 || quantity < sold.getQuantity() ){
            throw new QuantityError("There is not enough quantity to complete a operation");
        }


        medicineRepo.updateQuantity(sold.getQuantity(),sold.getMedicineId());

        sold.setSoldDate(getCurrentDate());
        sold.setPharmacistId(getCurrentUsersUserID());
        soldRepo.save(sold);

    }

    public List<MedicineEntity> getSearchResult(String str){
        List<MedicineEntity> result = medicineRepo.getSearchResult(getOrganizationId(), str);
        return result;
    }

    public List<SoldEntity> getSoldMedicines(){
        return soldRepo.getAll(getOrganizationId());
    }
//************************************************************************************************
    public Date getCurrentDate() throws ParseException {
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);

        return date;
    }


    public String getCurrentUsersUserName(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    public int getCurrentUsersUserID(){
        EmployeeEntity user = employeeRepo.getId(getCurrentUsersUserName()).get(0);
        return user.getId();
    }

    public int getOrganizationId(){
        List<EmployeeEntity> empList = employeeRepo.getOrgId(getCurrentUsersUserName());
        return empList.get(0).getOrganizationId();
    }

    public OrganizationEntity getOrganization(){
        OrganizationEntity org = organizationRepo.findById(getOrganizationId()).get();
        return org;
    }

    public EmployeeEntity getCurrentUser(){
        EmployeeEntity user  = employeeRepo.findById(getCurrentUsersUserID()).get();
        return user;
    }


}
