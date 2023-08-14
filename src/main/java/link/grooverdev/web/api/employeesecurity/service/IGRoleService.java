package link.grooverdev.web.api.employeesecurity.service;

import link.grooverdev.web.api.employeesecurity.entity.GRole;

import java.util.List;
import java.util.Optional;

public interface IGRoleService {
    List<GRole> findAll();
    List<GRole> findAllEnabled(boolean enabled);
    Optional<GRole> findByRoleID(long id);
    GRole save(GRole newGRole);
    GRole update(long id, GRole updGRole);
    GRole delete(long id);
}
