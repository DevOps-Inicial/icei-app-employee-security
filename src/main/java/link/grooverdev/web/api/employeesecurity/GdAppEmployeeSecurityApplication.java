package link.grooverdev.web.api.employeesecurity;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API SWAGGER Employee Application", version = "1.0", description = "API REST Documentation for the Application Employees version 1.0"))
public class GdAppEmployeeSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(GdAppEmployeeSecurityApplication.class, args);
	}

}
