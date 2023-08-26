package link.grooverdev.web.api.employeesecurity.controller;

import jakarta.validation.Valid;
import link.grooverdev.web.api.employeesecurity.dto.SignupDto;
import link.grooverdev.web.api.employeesecurity.entity.GUser;
import link.grooverdev.web.api.employeesecurity.service.IGUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@Slf4j
public class SignupController {

    private final IGUserService userService;
    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody @Valid SignupDto signupDto) {

        try{
            GUser createdUser = userService.createUser(signupDto);

            if (createdUser == null) {

                return new ResponseEntity<>("User not created", HttpStatus.BAD_REQUEST);
            }
            log.debug("User created successfully");
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }
        catch (DataAccessException daex) {
            log.error("Error al obtener un registro " + daex.getMostSpecificCause());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
