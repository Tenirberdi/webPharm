package pharm.web.webPharm.Repositories;

import org.springframework.data.repository.CrudRepository;
import pharm.web.webPharm.Models.AdminEntity;

public interface AdminRepo extends CrudRepository<AdminEntity, Integer> {
}
