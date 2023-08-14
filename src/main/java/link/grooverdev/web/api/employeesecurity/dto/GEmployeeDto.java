package link.grooverdev.web.api.employeesecurity.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class GEmployeeDto implements Serializable{

    private static final long serialUID = 1L;

    String employeeId;
    String firstName;
    String lastName;
    String email;
    String address;
    String cellPhone;
    boolean enabled;
    String createdByUser;
    LocalDateTime createdDate;
    String lastModifiedByUser;
    LocalDateTime lastModifiedDate;
    long numberOfModification;
}
