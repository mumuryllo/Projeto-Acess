package main.config;
import main.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("dev")
public class TestConfigSQL {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String valor;

    @Bean
    public boolean instanciaDb(){
        if (valor.equals("create")){
            this.dbService.instanciaDb();
        }
        return false;
    }

}
