package link.grooverdev.web.api.employeesecurity.repository;

import link.grooverdev.web.api.employeesecurity.entity.GEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GEmployeeRepository extends JpaRepository<GEmployee, String> {

    Optional<GEmployee> findByEmployeeId(String id);
    List<GEmployee> findAllByEnabled(boolean enabled);
}
