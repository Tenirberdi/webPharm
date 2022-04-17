package pharm.web.webPharm.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pharm.web.webPharm.Models.DeliveredEntity;
import pharm.web.webPharm.Models.MedicineEntity;
import pharm.web.webPharm.Models.SoldEntity;

import java.util.List;

public interface SoldRepo extends CrudRepository<SoldEntity, Integer> {

    @Query(value="SELECT * FROM `sold` as s JOIN `medicine` as m on s.medicine_id = m.id WHERE m.organization_id = ? ", nativeQuery = true)
    List<SoldEntity> getAll(int org_id);


}
