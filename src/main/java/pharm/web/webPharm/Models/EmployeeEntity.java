package pharm.web.webPharm.Models;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "employee", schema = "pharmacy", catalog = "")
public class EmployeeEntity {
    private int id;
    private String userName;
    private String fullName;
    private String photo;
    private Integer phone;
    private Date startDate;
    private int organizationId;
    private Date end_date;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "photo")
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Basic
    @Column(name = "phone")
    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "start_date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "organization_id")
    public int getOrganizationId() {
        return organizationId;
    }
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    @Basic
    @Column(name="end_date")
    public Date getEnd_date(){return end_date;}
    public void setEnd_date(Date end_date){this.end_date = end_date;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return id == that.id && organizationId == that.organizationId && Objects.equals(userName, that.userName) && Objects.equals(fullName, that.fullName) && Objects.equals(photo, that.photo) && Objects.equals(phone, that.phone) && Objects.equals(startDate, that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, fullName, photo, phone, startDate, organizationId);
    }
}
