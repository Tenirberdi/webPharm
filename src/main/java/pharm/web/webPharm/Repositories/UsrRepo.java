package pharm.web.webPharm.Repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import pharm.web.webPharm.Models.DeliveredEntity;
import pharm.web.webPharm.Models.UsrEntity;

@Transactional
public interface UsrRepo extends CrudRepository<UsrEntity, Integer> {

    @Modifying
    @Query(value="INSERT INTO `usr`(`user_name`, `user_password`, `enabled`) VALUES (?1,?2,?3)", nativeQuery = true)
    void insertUser(String login, String pass, int enabled);

    @Modifying
    @Query(value="DELETE FROM `usr` WHERE `user_name` = ?", nativeQuery = true)
    void delete(String login);
}
