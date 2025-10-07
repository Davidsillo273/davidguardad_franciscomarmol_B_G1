package davidguardado_franciscomarmol_B_G1.prueba_final;

import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.query.hql.spi.DotIdentifierConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PruebaFinalApplication {

    public static void main(String[] args) {
        //Codigo para cargar los valores del archivo .env sobre el archivo application.properties
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
        );
        SpringApplication.run(PruebaFinalApplication.class, args);
    }
}

