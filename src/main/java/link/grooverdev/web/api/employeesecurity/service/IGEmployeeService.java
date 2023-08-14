package link.grooverdev.web.api.employeesecurity.service;

import link.grooverdev.web.api.employeesecurity.entity.GEmployee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IGEmployeeService {
    List<GEmployee> findAll();
    List<GEmployee> findAllEnabled(boolean enabled);
    Optional<GEmployee> findByEmployeeID(String id);
    GEmployee save(GEmployee newGEmployee);
    GEmployee update(String id, GEmployee updGEmployee);
    GEmployee delete(String id);
}
