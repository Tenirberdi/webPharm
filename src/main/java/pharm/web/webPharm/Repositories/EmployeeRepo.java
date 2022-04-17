package pharm.web.webPharm.Repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import pharm.web.webPharm.DTO.usersDTO;
import pharm.web.webPharm.Models.DeliveredEntity;
import pharm.web.webPharm.Models.EmployeeEntity;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Transactional
public interface EmployeeRepo extends CrudRepository<EmployeeEntity, Integer> {

    @Query(value = "SELECT * FROM employee WHERE user_name = ?", nativeQuery = true)
    List<EmployeeEntity> getOrgId(String user_name);

    @Query(value = "SELECT * FROM employee WHERE user_name = ?", nativeQuery = true)
    List<EmployeeEntity> getId(String user_name);

    @Query(value = "SELECT e.id as id, e.full_name as full_name, r.role as role, e.start_date as start_date,e.end_date as end_date, e.phone as phone, e.photo as photo FROM `employee` as e JOIN `role` as r on e.user_name = r.user_name JOIN `usr` as u on e.user_name = u.user_name where e.organization_id = ?", nativeQuery = true)
    List<usersDTO> getUsers(int org_id);

    @Modifying
    @Query(value="INSERT INTO `employee`(`user_name`, `full_name`, `photo`, `phone`, `start_date`, `organization_id`) VALUES (?1,?2,?3,?4,?5,?6)", nativeQuery = true)
    void inserUser(String login, String fullName, String photo, int phone, Date start_date,  int org_id);

    @Modifying
    @Query(value="DELETE FROM `employee` WHERE `user_name` = ?", nativeQuery = true)
    void delete(String login);
}
