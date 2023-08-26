package link.grooverdev.web.api.employeesecurity.dto;

import lombok.Data;

@Data
public class AuthenticationDto {
    private String email;
    private String password;
}
