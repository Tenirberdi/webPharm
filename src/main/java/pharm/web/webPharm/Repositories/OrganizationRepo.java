package pharm.web.webPharm.Repositories;

import org.springframework.data.repository.CrudRepository;
import pharm.web.webPharm.Models.DeliveredEntity;
import pharm.web.webPharm.Models.OrganizationEntity;

public interface OrganizationRepo extends CrudRepository<OrganizationEntity, Integer> {
}
