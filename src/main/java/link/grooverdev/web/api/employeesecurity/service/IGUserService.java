package link.grooverdev.web.api.employeesecurity.service;

import link.grooverdev.web.api.employeesecurity.entity.GUser;

import java.util.List;
import java.util.Optional;

public interface IGUserService {
    List<GUser> findAll();
    List<GUser> findAllEnabled(boolean enabled);
    Optional<GUser> findByUserID(long id);
    GUser save(GUser newGUser);
    GUser update(long id, GUser updGUser);
    GUser delete(long id);
}
