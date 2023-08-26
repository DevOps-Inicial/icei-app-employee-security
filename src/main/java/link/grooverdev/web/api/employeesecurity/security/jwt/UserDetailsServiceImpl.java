package link.grooverdev.web.api.employeesecurity.security.jwt;

import link.grooverdev.web.api.employeesecurity.repository.GUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final GUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var user = userRepository.findFirstByEmail(email);

        if (user == null) throw new UsernameNotFoundException("User not found", null);
        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
