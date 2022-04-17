package pharm.web.webPharm.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "usr", schema = "pharmacy", catalog = "")
public class UsrEntity {
    private String userName;
    private String userPassword;
    private byte enabled;

    @Id
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_password")
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "enabled")
    public byte getEnabled() {
        return enabled;
    }

    public void setEnabled(byte enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsrEntity usrEntity = (UsrEntity) o;
        return enabled == usrEntity.enabled && Objects.equals(userName, usrEntity.userName) && Objects.equals(userPassword, usrEntity.userPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userPassword, enabled);
    }
}
