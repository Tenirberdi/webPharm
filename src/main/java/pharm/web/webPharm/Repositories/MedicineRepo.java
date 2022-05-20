package pharm.web.webPharm.Repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import pharm.web.webPharm.Models.DeliveredEntity;
import pharm.web.webPharm.Models.EmployeeEntity;
import pharm.web.webPharm.Models.MedicineEntity;

import java.util.List;
import java.util.Optional;

@Transactional
public interface MedicineRepo extends CrudRepository<MedicineEntity, Integer> {


    @Query(value = "SELECT * FROM medicine WHERE organization_id = ? and deleted = 0", nativeQuery = true)
    List<MedicineEntity> getAll(int id);

    @Query(value = "SELECT * FROM `medicine` WHERE organization_id = ?1 and (name like '%?2%' or quantity like '%?2%' or manufacturer like '%?2%' or sale like '%?2%' or price like '%?2%')", nativeQuery = true)
    List<MedicineEntity> getSearchResult(int id, String str);

    @Modifying
    @Query(value = "UPDATE `medicine` SET `quantity`= `quantity` - ?1 WHERE id = ?2", nativeQuery = true)
    void updateQuantity(int quantity,int id);

    @Query(value="SELECT * FROM `medicine` WHERE `id` = ? and `organization_id` = ?", nativeQuery = true)
    MedicineEntity findById(Integer integer, Integer orgId);

    @Modifying
    @Query(value = "UPDATE `medicine` SET `deleted` = 1 WHERE `id` = ? and `organization_id` = ?", nativeQuery = true)
    void deleteMedById(int medId, int orgId);
}
