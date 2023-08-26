package link.grooverdev.web.api.employeesecurity.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import link.grooverdev.web.api.employeesecurity.dto.AuthenticationDto;
import link.grooverdev.web.api.employeesecurity.dto.AuthenticationResponse;
import link.grooverdev.web.api.employeesecurity.security.service.IAuthenticationService;
import link.grooverdev.web.api.employeesecurity.security.jwt.UserDetailsServiceImpl;
import link.grooverdev.web.api.employeesecurity.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final IAuthenticationService authService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public AuthenticationResponse createAuthewnticationToken(@RequestBody @Valid AuthenticationDto authenticationDto,
                                                             HttpServletResponse response)
            throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDto.getEmail(), authenticationDto.getPassword()));
        }
        catch (BadCredentialsException bcex) {
            throw new BadCredentialsException("Incorrect username or passsword");
        }
        catch (DisabledException dex) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
            return null;
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDto.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return new AuthenticationResponse(jwt);
    }
}
