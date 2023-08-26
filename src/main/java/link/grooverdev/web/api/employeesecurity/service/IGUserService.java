package link.grooverdev.web.api.employeesecurity.service;

import link.grooverdev.web.api.employeesecurity.dto.SignupDto;
import link.grooverdev.web.api.employeesecurity.entity.GUser;

import java.util.List;

public interface IGUserService {
    GUser createUser(SignupDto signupDto);
    List<GUser> getAll();
}
