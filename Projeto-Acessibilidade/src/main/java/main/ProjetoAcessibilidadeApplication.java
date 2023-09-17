package main;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API - Acessibilidade num click", version = "1", description = "Projeto de um Dashboard administrativo para aplicar a ideia de acessibilidade"))
public class ProjetoAcessibilidadeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoAcessibilidadeApplication.class, args);
	}

}
