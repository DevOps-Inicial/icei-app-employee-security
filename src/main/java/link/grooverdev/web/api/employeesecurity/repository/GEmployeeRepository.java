package link.grooverdev.web.api.employeesecurity.repository;

import link.grooverdev.web.api.employeesecurity.entity.GEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GEmployeeRepository extends JpaRepository<GEmployee, Long> {

    Optional<GEmployee> findByEmployeeId(long id);
    List<GEmployee> findAllByEnabled(boolean enabled);
}
