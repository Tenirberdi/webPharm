package pharm.web.webPharm.DTO;

import java.sql.Date;

public interface usersDTO {
    int getId();
    String getFull_name();
    String getRole();
    Date getStart_date();
    Date getEnd_date();
    int getPhone();
    String getPhoto();
}
