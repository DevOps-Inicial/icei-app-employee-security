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

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@Slf4j
public class GEmployeeController {

    private final IGEmployeeService employeeService;
    private final ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/all-employees")
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

    @PostMapping("/keep-employees")
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
}
