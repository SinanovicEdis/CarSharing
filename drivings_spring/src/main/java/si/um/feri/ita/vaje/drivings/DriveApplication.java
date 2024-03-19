package si.um.feri.ita.vaje.drivings;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import si.um.feri.ita.vaje.drivings.dao.CarRepository;
import si.um.feri.ita.vaje.drivings.dao.DrivingDataRepository;

import java.util.logging.Logger;

/**
 * Entry point
 */
@SpringBootApplication
public class DriveApplication {
	private static final Logger log = Logger.getLogger(DriveApplication.class.toString());

	public static void main(String[] args) {
		SpringApplication.run(DriveApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CarRepository daoProd, DrivingDataRepository daoMeas) {
		return args -> {
			log.info("Ready, Set, Go!");
			new DriveRestServiceInit().populateTestDataIfNotPresent(daoProd,daoMeas);
		};
	}
}