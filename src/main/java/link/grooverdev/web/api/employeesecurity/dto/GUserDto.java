package link.grooverdev.web.api.employeesecurity.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class GUserDto {

    private String userId;
    private String username;
    private String email;
    private String password;
    private String createdByUser;
    private LocalDateTime createdDate;
    private String lastModifiedByUser;
    private LocalDateTime lastModifiedDate;
    private long numberOfModification;
}
