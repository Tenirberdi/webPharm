package pharm.web.webPharm.Repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import pharm.web.webPharm.Models.DeliveredEntity;

import java.sql.Date;
import java.util.List;

@Transactional
public interface DeliveredRepo extends CrudRepository<DeliveredEntity, Integer> {

    @Modifying
    @Query(value="UPDATE `order_medicine` SET `order_medicine`.`quantity` = `order_medicine`.`quantity` - ?1 WHERE `order_medicine`.`medicine_id` = ?2" , nativeQuery = true)
    void deliver_minus(int quantity, int medicine_id);

    @Modifying
    @Query(value="UPDATE `medicine` SET `medicine`.`quantity` = `medicine`.`quantity` + ?1 WHERE `medicine`.`id` = ?2", nativeQuery = true)
    void deliver_plus(int quantity, int medicine_id);

    @Modifying
    @Query(value="INSERT INTO `delivered`( `medicine_id`, `deliveryman_id`, `quantity`, `delivered_date`) VALUES (?1,?2, ?3, ?4)", nativeQuery = true)
    void deliver_report(int medicine_id, int deliveryman_id,int quantity, Date date );

    @Query(value="SELECT * FROM `delivered` WHERE `delivered`.`deliveryman_id` = ?1", nativeQuery = true)
    List<DeliveredEntity> getDelivered(int id);
}
