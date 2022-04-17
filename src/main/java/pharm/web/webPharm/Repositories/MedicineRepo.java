package pharm.web.webPharm.Repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import pharm.web.webPharm.Models.DeliveredEntity;
import pharm.web.webPharm.Models.EmployeeEntity;
import pharm.web.webPharm.Models.MedicineEntity;

import java.util.List;

@Transactional
public interface MedicineRepo extends CrudRepository<MedicineEntity, Integer> {


    @Query(value = "SELECT * FROM medicine WHERE organization_id = ?", nativeQuery = true)
    List<MedicineEntity> getAll(int id);

    @Query(value = "SELECT * FROM `medicine` WHERE organization_id = ?1 and (name like '%?2%' or quantity like '%?2%' or manufacturer like '%?2%' or sale like '%?2%' or price like '%?2%')", nativeQuery = true)
    List<MedicineEntity> getSearchResult(int id, String str);

    @Modifying
    @Query(value = "UPDATE `medicine` SET `quantity`= `quantity` - ?1 WHERE id = ?2", nativeQuery = true)
    void updateQuantity(int quantity,int id);

}
