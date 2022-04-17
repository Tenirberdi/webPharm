package pharm.web.webPharm.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pharm.web.webPharm.Exceptions.QuantityError;
import pharm.web.webPharm.Models.DeliveredEntity;
import pharm.web.webPharm.Models.EmployeeEntity;
import pharm.web.webPharm.Models.OrderMedicineEntity;
import pharm.web.webPharm.Models.OrganizationEntity;
import pharm.web.webPharm.Repositories.*;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

@Service
public class DelivService {


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

    @Autowired
    private DeliveredRepo deliveredRepo;


    public List<OrderMedicineEntity> getOrderedList(){
        return orderMedicineRepo.getAll(getOrganizationId());
    }

    public void deliver(int medicine_id, int quantity) throws QuantityError, ParseException {
        OrderMedicineEntity order = orderMedicineRepo.getOrderByMedicineId(medicine_id, getOrganizationId());
        if(order == null){
            throw new QuantityError("Such medicine was not ordered");
        }

        deliveredRepo.deliver_minus(quantity,medicine_id);
        deliveredRepo.deliver_plus(quantity, medicine_id);
        deliveredRepo.deliver_report(medicine_id, getCurrentUsersUserID(), quantity, getCurrentDate());

    }

    public List<DeliveredEntity> getDelivered(){
        return deliveredRepo.getDelivered(getCurrentUsersUserID());
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
