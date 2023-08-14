package link.grooverdev.web.api.employeesecurity.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    public JwtResponse(String accessToken, long id, String username, String email, List<String> roles) {

        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
    public String getTokenType() {
        return type;
    }
    public void setTokenType(String tokenType){
        this.type = tokenType;
    }
}
