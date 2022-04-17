package pharm.web.webPharm.Models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "order_medicine", schema = "pharmacy", catalog = "")
public class OrderMedicineEntity {
    private int id;
    private int medicineId;
    private int pharmacistId;
    private int quantity;
    private Date orderedDate;

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
    @Column(name = "ordered_date")
    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderMedicineEntity that = (OrderMedicineEntity) o;
        return id == that.id && medicineId == that.medicineId && pharmacistId == that.pharmacistId && quantity == that.quantity && Objects.equals(orderedDate, that.orderedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, medicineId, pharmacistId, quantity, orderedDate);
    }
}
