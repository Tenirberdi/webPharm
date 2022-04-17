package pharm.web.webPharm.Repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import pharm.web.webPharm.Models.DeliveredEntity;
import pharm.web.webPharm.Models.RoleEntity;

@Transactional
public interface RoleRepo extends CrudRepository<RoleEntity, Integer> {

    @Modifying
    @Query(value="INSERT INTO `role`(`user_name`, `role`) VALUES (?1,?2)", nativeQuery = true)
    void addRole(String login, String role);

    @Modifying
    @Query(value="DELETE FROM `role` WHERE `user_name` = ?", nativeQuery = true)
    void delete(String login);

}
