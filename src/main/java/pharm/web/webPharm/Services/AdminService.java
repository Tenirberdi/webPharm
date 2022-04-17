package pharm.web.webPharm.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pharm.web.webPharm.DTO.usersDTO;
import pharm.web.webPharm.Models.EmployeeEntity;
import pharm.web.webPharm.Models.OrganizationEntity;
import pharm.web.webPharm.Repositories.EmployeeRepo;
import pharm.web.webPharm.Repositories.OrganizationRepo;
import pharm.web.webPharm.Repositories.RoleRepo;
import pharm.web.webPharm.Repositories.UsrRepo;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    UsrRepo usrRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    OrganizationRepo organizationRepo;


    public List<usersDTO> getUsers(){
        return employeeRepo.getUsers(getOrganizationId());
    }

    public void register(String login, String pass, String fullName, int phone, String photo, String role) throws ParseException {
        usrRepo.insertUser(login, pass, 1);
        roleRepo.addRole(login, role);
        employeeRepo.inserUser(login, fullName, photo, phone, getCurrentDate(), getOrganizationId());
        System.out.println("registered");

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

    public void delete(int id){
        String login = employeeRepo.findById(id).get().getUserName();
        employeeRepo.delete(login);
        roleRepo.delete(login);
        usrRepo.delete(login);
    }

}
