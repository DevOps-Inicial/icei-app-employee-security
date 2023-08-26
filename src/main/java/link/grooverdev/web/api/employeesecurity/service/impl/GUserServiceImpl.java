package link.grooverdev.web.api.employeesecurity.service.impl;

import link.grooverdev.web.api.employeesecurity.dto.SignupDto;
import link.grooverdev.web.api.employeesecurity.entity.GUser;
import link.grooverdev.web.api.employeesecurity.repository.GUserRepository;
import link.grooverdev.web.api.employeesecurity.service.IGUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GUserServiceImpl implements IGUserService {

    private final GUserRepository userRepository;
    @Override
    @Transactional
    public GUser createUser(SignupDto signupDto) {

        GUser user = new GUser( signupDto.getUsername(), signupDto.getEmail(), new BCryptPasswordEncoder().encode(signupDto.getPassword()));
        user.setUsername(signupDto.getUsername());
        user.setEmail(signupDto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GUser> getAll() {
        return userRepository.findAll();
    }
}
