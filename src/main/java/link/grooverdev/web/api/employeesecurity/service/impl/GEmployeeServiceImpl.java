package link.grooverdev.web.api.employeesecurity.service.impl;

import link.grooverdev.web.api.employeesecurity.entity.GEmployee;
import link.grooverdev.web.api.employeesecurity.repository.GEmployeeRepository;
import link.grooverdev.web.api.employeesecurity.service.IGEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GEmployeeServiceImpl implements IGEmployeeService {

    private final GEmployeeRepository employeeRepository;
    @Override
    @Transactional(readOnly = true)
    public List<GEmployee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GEmployee> findAllEnabled(boolean enabled) {
        return employeeRepository.findAllByEnabled(enabled);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GEmployee> findByEmployeeID(String id) {
        return employeeRepository.findByEmployeeId(id);
    }

    @Override
    @Transactional
    public GEmployee save(GEmployee newGEmployee) {
        return employeeRepository.save(newGEmployee);
    }

    @Override
    @Transactional
    public GEmployee update(String id, GEmployee updGEmployee) {

        var updatedEmployee = employeeRepository.findByEmployeeId(id);
        if (updatedEmployee.isPresent()) {
            updatedEmployee.get().setFirstName(updGEmployee.getFirstName());
            updatedEmployee.get().setLastName(updGEmployee.getLastName());
            updatedEmployee.get().setEmail(updGEmployee.getEmail());
            updatedEmployee.get().setAddress(updGEmployee.getAddress());
            updatedEmployee.get().setCellPhone(updGEmployee.getCellPhone());
            updatedEmployee.get().setEnabled(updGEmployee.isEnabled());
        }
        return employeeRepository.save(updatedEmployee.get());
    }

    @Override
    @Transactional
    public GEmployee delete(String id) {
        var deletedEmployee = employeeRepository.findByEmployeeId(id);
        deletedEmployee.ifPresent(delGEmployee ->deletedEmployee.get().setEnabled(false));
        return employeeRepository.save(deletedEmployee.get());
    }
}
