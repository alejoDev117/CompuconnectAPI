package co.edu.uco.compuconnect.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("co.edu.uco.compuconnect")
public class CompuconnectApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompuconnectApiApplication.class, args);
	}

}
