package link.grooverdev.web.api.employeesecurity.repository;

import link.grooverdev.web.api.employeesecurity.dto.GUserDto;
import link.grooverdev.web.api.employeesecurity.entity.GUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GUserRepository extends JpaRepository<GUser,String> {
    GUserDto save(GUserDto userDto);
    GUser findFirstByEmail(String email);
}
