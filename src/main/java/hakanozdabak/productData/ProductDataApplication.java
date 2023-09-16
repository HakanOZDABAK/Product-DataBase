package hakanozdabak.productData;

import hakanozdabak.productData.business.jwt.AuthenticationService;
import hakanozdabak.productData.business.requests.RegisterRequest;
import hakanozdabak.productData.core.utilities.exceptions.BusinessException;
import hakanozdabak.productData.core.utilities.exceptions.ProblemDetails;
import hakanozdabak.productData.core.utilities.exceptions.ValidationProblemDetails;
import hakanozdabak.productData.entities.concretes.Role;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@SpringBootApplication
@OpenAPIDefinition
@RestControllerAdvice
@SecurityScheme(
		name = "bearerAuth",
		scheme = "bearer",
		bearerFormat = "JWT",
		type = SecuritySchemeType.HTTP,
		in = SecuritySchemeIn.HEADER
)
public class ProductDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductDataApplication.class, args);
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails handleBusinessException(BusinessException businessException){
		ProblemDetails problemDetails = new ProblemDetails();
		problemDetails.setMessage(businessException.getMessage());

		return problemDetails;

	}
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails handleBusinessException(MethodArgumentNotValidException methodArgumentNotValidException){
		ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
		validationProblemDetails.setMessage("Validation Exception");
		validationProblemDetails.setValidationErrors(new HashMap<String,String>());
		for (FieldError fieldError: methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
			validationProblemDetails.getValidationErrors().put(fieldError.getField(),fieldError.getDefaultMessage());
		}
		return validationProblemDetails;

	}
	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}

	@Bean
	public CommandLineRunner commandLineRunner(AuthenticationService authenticationService)
	{
     return args -> {
		 var admin = RegisterRequest.builder()
				 .firstName("Admin")
				 .lastName("Admin")
				 .email("admin@mail.com")
				 .password("admin")
				 .role(Role.ADMIN)
				 .build();
		 System.out.println("Admin Token: " + authenticationService.register(admin).getToken());

	 };

	}
}
