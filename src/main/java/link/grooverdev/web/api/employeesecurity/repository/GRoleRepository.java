package link.grooverdev.web.api.employeesecurity.repository;

import link.grooverdev.web.api.employeesecurity.entity.ERole;
import link.grooverdev.web.api.employeesecurity.entity.GRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GRoleRepository extends JpaRepository<GRole, Long> {

    Optional<GRole> findByName(ERole roleName);
    Optional<GRole> findByRoleId(long id);
    List<GRole> findAllByEnabledOrderByRoleIdAsc(boolean enabled);
}
