package pharm.web.webPharm.Models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "sold", schema = "pharmacy", catalog = "")
public class SoldEntity {
    private int id;
    private int medicineId;
    private int pharmacistId;
    private int quantity;
    private Date soldDate;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "medicine_id")
    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    @Basic
    @Column(name = "pharmacist_id")
    public int getPharmacistId() {
        return pharmacistId;
    }

    public void setPharmacistId(int pharmacistId) {
        this.pharmacistId = pharmacistId;
    }

    @Basic
    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "sold_date")
    public Date getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(Date soldDate) {
        this.soldDate = soldDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoldEntity that = (SoldEntity) o;
        return id == that.id && medicineId == that.medicineId && pharmacistId == that.pharmacistId && quantity == that.quantity && Objects.equals(soldDate, that.soldDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, medicineId, pharmacistId, quantity, soldDate);
    }
}
