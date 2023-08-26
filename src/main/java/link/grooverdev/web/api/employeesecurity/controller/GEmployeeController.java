package link.grooverdev.web.api.employeesecurity.controller;

import jakarta.validation.Valid;
import link.grooverdev.web.api.employeesecurity.dto.GEmployeeDto;
import link.grooverdev.web.api.employeesecurity.entity.GEmployee;
import link.grooverdev.web.api.employeesecurity.service.IGEmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:46000")
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@Slf4j
public class GEmployeeController {

    private final IGEmployeeService employeeService;
    private final ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/employees")
    public ResponseEntity<List<GEmployeeDto>> findAllEmployees() {
        try {
            var employees = employeeService.findAll().stream()
                    .map(allEmp->modelMapper.map(allEmp, GEmployeeDto.class)).collect(Collectors.toList());
            log.debug("Listado completo de registros");
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }
        catch (DataAccessException daex) {
            log.error("Error al obtener el listado de registros " + daex.getMostSpecificCause());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/enabled-employees/{enabled}")
    public ResponseEntity<List<GEmployeeDto>> findAllEnabledEmployees(@PathVariable boolean enabled) {

        try {
            var enabledEmployees = employeeService.findAllEnabled(true).stream()
                    .map(enabEmployees->modelMapper.map(enabEmployees, GEmployeeDto.class)).collect(Collectors.toList());
            log.debug("Listado de registros habilitados");
            return new ResponseEntity<>(enabledEmployees, HttpStatus.OK);
        }
        catch (DataAccessException daex) {
            log.error("Error al obtener el listado de habilitados " + daex.getMostSpecificCause());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<GEmployeeDto> findEmployeeByID(@Valid @PathVariable String id) {

        try {
            var singleEmployee = employeeService.findByEmployeeID(id);
            var singleEmployeeResponse = modelMapper.map(singleEmployee.get(), GEmployeeDto.class);
            log.debug("Un registro");
            return new ResponseEntity<>(singleEmployeeResponse, HttpStatus.OK);
        }
        catch (DataAccessException daex) {
            log.error("Error al obtener un registro " + daex.getMostSpecificCause());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/employees")
    public ResponseEntity<GEmployeeDto> saveEmployee(@Valid @RequestBody GEmployeeDto nGEmployeeDto) {
        try {
            var employeeRequest = modelMapper.map(nGEmployeeDto, GEmployee.class);
            var savedEmployee = employeeService.save(employeeRequest);
            var employeeResponse = modelMapper.map(savedEmployee, GEmployeeDto.class);
            log.debug("Registro guardado exitosamente");
            return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
        }
        catch (DataAccessException daex) {
            log.error("Error al guardar el registro " + daex.getMostSpecificCause());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<GEmployeeDto> updateEmployee(@Valid @PathVariable String id, @Valid @RequestBody GEmployeeDto uGEmployeeDto) {

        try {
            var employeeRequest = modelMapper.map(uGEmployeeDto, GEmployee.class);
            var updatedEmployee = employeeService.update(id, employeeRequest);
            var employeeResponse = modelMapper.map(updatedEmployee, GEmployeeDto.class);
            log.debug("Registro actualizado exitosamente");
            return new ResponseEntity<>(employeeResponse, HttpStatus.ACCEPTED);
        }
        catch (DataAccessException daex) {
            log.error("Error al actualizar el registro " + daex.getMostSpecificCause());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/employees/{id}")
    public ResponseEntity<GEmployeeDto> deleteEmployeeByID(@Valid @PathVariable String id) {

        try {
            var deletedEmployee = employeeService.delete(id);
            var employeeResponse = modelMapper.map(deletedEmployee, GEmployeeDto.class);
            log.debug("Registro eliminado");
            return new ResponseEntity<>(employeeResponse, HttpStatus.NO_CONTENT);
        }
        catch (DataAccessException daex) {
            log.error("Error al eliminar el registro " + daex.getMostSpecificCause());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
