package link.grooverdev.web.api.employeesecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class AuthenticationResponse {

    private String jwtToken;
}
