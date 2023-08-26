package link.grooverdev.web.api.employeesecurity.controller;

import link.grooverdev.web.api.employeesecurity.dto.GUserDto;
import link.grooverdev.web.api.employeesecurity.service.IGUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class GUserController {
    private final IGUserService userService;
    private final ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/users")
    public ResponseEntity<List<GUserDto>> getAllUsers() {
        try {
            return new ResponseEntity<>(userService.getAll()
                    .stream().map(users->modelMapper.map(users, GUserDto.class))
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
        catch (DataAccessException daex) {
            log.error("Error al obtener el listado " + daex.getMostSpecificCause());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
