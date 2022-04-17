package pharm.web.webPharm.Models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "delivered", schema = "pharmacy", catalog = "")
public class DeliveredEntity {
    private int id;
    private int medicineId;
    private int deliverymanId;
    private int quantity;
    private Date deliveredDate;

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
    @Column(name = "deliveryman_id")
    public int getDeliverymanId() {
        return deliverymanId;
    }

    public void setDeliverymanId(int deliverymanId) {
        this.deliverymanId = deliverymanId;
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
    @Column(name = "delivered_date")
    public Date getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(Date deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveredEntity that = (DeliveredEntity) o;
        return id == that.id && medicineId == that.medicineId && deliverymanId == that.deliverymanId && quantity == that.quantity && Objects.equals(deliveredDate, that.deliveredDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, medicineId, deliverymanId, quantity, deliveredDate);
    }
}
