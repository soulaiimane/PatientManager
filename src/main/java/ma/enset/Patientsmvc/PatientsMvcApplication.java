package ma.enset.Patientsmvc;

import ma.enset.Patientsmvc.entities.Patient;
import ma.enset.Patientsmvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientsMvcApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(PatientRepository patientRepository){
		return args -> {
			patientRepository.save(new Patient(null,"ahmed",new Date(),false,120));
			patientRepository.findAll().forEach(p->{
				System.out.println(p.getId());
				System.out.println(p.getNom());
			});
		};


	}

}

