package pharm.web.webPharm.Repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pharm.web.webPharm.Models.DeliveredEntity;
import pharm.web.webPharm.Models.OrderMedicineEntity;
import pharm.web.webPharm.Models.SoldEntity;

import java.util.List;

public interface OrderMedicineRepo extends CrudRepository<OrderMedicineEntity, Integer> {
    @Query(value = "SELECT * FROM order_medicine s join medicine as m on s.medicine_id = m.id WHERE m.organization_id = ?1", nativeQuery = true)
    List<OrderMedicineEntity> getAll(int id);

    @Query(value="select * from order_medicine as o join `medicine` as m on o.medicine_id = m.id where o.medicine_id = ?1 and m.organization_id = ?2", nativeQuery = true)
    OrderMedicineEntity getOrderByMedicineId(int id, int org_id);



}
