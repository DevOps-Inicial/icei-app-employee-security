package link.grooverdev.web.api.employeesecurity.dto;

import lombok.Data;

@Data
public class SignupDto {
    private String username;
    private String email;
    private String password;
}
