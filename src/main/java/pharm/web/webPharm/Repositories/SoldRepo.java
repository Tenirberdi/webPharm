package pharm.web.webPharm.Repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pharm.web.webPharm.DTO.EarningDTO;
import pharm.web.webPharm.DTO.EarningPharmacistDTO;
import pharm.web.webPharm.Models.DeliveredEntity;
import pharm.web.webPharm.Models.MedicineEntity;
import pharm.web.webPharm.Models.SoldEntity;

import java.util.List;

public interface SoldRepo extends CrudRepository<SoldEntity, Integer> {

    @Query(value="SELECT * FROM `sold` as s JOIN `medicine` as m on s.medicine_id = m.id WHERE m.organization_id = ? ", nativeQuery = true)
    List<SoldEntity> getAll(int org_id);

    @Query(value="SELECT sum(s.quantity*m.price) as cash, DATE_FORMAT(s.sold_date, '%m-%Y') as 'date' FROM `sold` as s JOIN `medicine` as m on m.id = s.medicine_id GROUP BY year(s.sold_date), month(s.sold_date);", nativeQuery = true)
    List<EarningDTO> getEarningPerMonth();

    @Query(value="SELECT sum(s.quantity*m.price) as cash, DATE_FORMAT(s.sold_date, '%m-%Y') as 'date' FROM `sold` as s JOIN `medicine` as m on m.id = s.medicine_id where year(s.sold_date) = ? GROUP BY year(s.sold_date), month(s.sold_date)\n", nativeQuery = true)
    List<EarningDTO> getEarningPerYear(int year);

    @Query(value = "SELECT s.medicine_id as id, m.name , sum(s.quantity) as quantity, DATE_FORMAT(s.sold_date, '%m-%Y') as 'date' from `sold` as s JOIN `medicine` as m on m.id = s.medicine_id GROUP BY s.medicine_id, year(s.sold_date), month(s.sold_date) order by quantity desc" , nativeQuery = true)
    List<EarningPharmacistDTO> getTopMedicinesPerMonth();

    @Query(value = "SELECT s.medicine_id as id, m.name , sum(s.quantity) as quantity, DATE_FORMAT(s.sold_date, '%m-%Y') as 'date' from `sold` as s JOIN `medicine` as m on m.id = s.medicine_id where year(s.sold_date) = ? GROUP BY s.medicine_id, year(s.sold_date), month(s.sold_date) order by quantity desc" , nativeQuery = true)
    List<EarningPharmacistDTO> getTopMedicinesPerYear(int year);

    @Query(value="SELECT s.pharmacist_id as id, e.full_name as name ,sum(s.quantity) as quantity , DATE_FORMAT(s.sold_date, '%m-%Y') as 'date' FROM `sold` as s JOIN employee as e on e.id = s.pharmacist_id GROUP BY s.pharmacist_id, year(s.sold_date), month(s.sold_date) order by year(s.sold_date), month(s.sold_date), quantity desc", nativeQuery = true)
    List<EarningPharmacistDTO> getTopSellersPerMonth();

    @Query(value="SELECT s.pharmacist_id as id, e.full_name as name ,sum(s.quantity) as quantity , DATE_FORMAT(s.sold_date, '%m-%Y') as 'date' FROM `sold` as s JOIN employee as e on e.id = s.pharmacist_id where year(s.sold_date) = ? GROUP BY s.pharmacist_id, year(s.sold_date), month(s.sold_date) order by year(s.sold_date), month(s.sold_date), quantity desc", nativeQuery = true)
    List<EarningPharmacistDTO> getTopSellersPerYear(int year);

    @Modifying
    @Query(value="ALTER TABLE sold DISABLE KEYS" , nativeQuery = true)
    void disableKeys();

    @Modifying
    @Query(value="ALTER TABLE sold ENABLE KEYS;" , nativeQuery = true)
    void enableKeys();
}
