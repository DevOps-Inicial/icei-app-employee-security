package link.grooverdev.web.api.employeesecurity.repository;

import link.grooverdev.web.api.employeesecurity.entity.GUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GUserRepository extends JpaRepository<GUser, Long> {
    Optional<GUser> findByUsername(String username);
    Optional<GUser> findByUserId(long id);
    List<GUser> findAllByEnabledOrderByUserIdAsc(boolean enabled);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
