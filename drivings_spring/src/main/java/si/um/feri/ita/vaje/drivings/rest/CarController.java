package si.um.feri.ita.vaje.drivings.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import si.um.feri.ita.vaje.drivings.dao.CarRepository;
import si.um.feri.ita.vaje.drivings.vao.Car;

/**
 * CRUD operations on Car
 */
@CrossOrigin
@RestController
public class CarController {

	private static final Logger log = Logger.getLogger(CarController.class.toString());

	public CarController(CarRepository dao) {
		this.dao = dao;
	}

	private CarRepository dao;

	static List<si.um.feri.ita.vaje.drivings.dto.Car> translateCarListToDtoList(Iterable<Car> list) {
		List<si.um.feri.ita.vaje.drivings.dto.Car> ret=new ArrayList<>();
		for (Car sc :list)
			ret.add(sc.toDto());
		return ret;
	}

	@GetMapping("/cars")
	public @ResponseBody Iterable<si.um.feri.ita.vaje.drivings.dto.Car> getAllCars() {
		return translateCarListToDtoList(dao.findAll());
	}

	@GetMapping("/cars/{id}")
	public @ResponseBody ResponseEntity<si.um.feri.ita.vaje.drivings.dto.Car> getAllCarsById(@PathVariable("id") int id) {
		//validate
		Optional<Car> val=dao.findById(id);
		if (val.isEmpty()) {
			log.info("/cars/"+id+" ; Car not found!");
			return new ResponseEntity("car-not-found",HttpStatus.NOT_ACCEPTABLE);
		}
		return ResponseEntity.ok(val.get().toDto());
	}

	@PostMapping("/cars")
	public ResponseEntity<si.um.feri.ita.vaje.drivings.dto.Car> postCar(@RequestBody si.um.feri.ita.vaje.drivings.dto.Car pc) {
		Car vao=new Car(pc);
		dao.save(vao);
		return ResponseEntity.ok(vao.toDto());
	}

	@PutMapping("/cars/{id}")
	public ResponseEntity<si.um.feri.ita.vaje.drivings.dto.Car> putCar(@PathVariable("id") int id, @RequestBody si.um.feri.ita.vaje.drivings.dto.Car v) {
		//validate
		Optional<Car> val=dao.findById(id);
		if (val.isEmpty()) {
			log.info("/cars/"+id+" ; Car not found!");
			return new ResponseEntity("car-not-found",HttpStatus.NOT_ACCEPTABLE);
		}

		Car vao=val.get();
		vao.updateFrom(v);
		dao.save(vao);
		return ResponseEntity.ok(vao.toDto());
	}

	@DeleteMapping("/cars/{id}")
	public ResponseEntity<String> deleteCar(@PathVariable("id") int id) {
		//validate
		Optional<Car> val=dao.findById(id);
		if (val.isEmpty()) {
			log.info("/cars/"+id+" ; Car not found!");
			return new ResponseEntity("car-not-found",HttpStatus.NOT_ACCEPTABLE);
		}
		Car vao=val.get();
		dao.delete(vao);
		return ResponseEntity.ok("deleted");
	}

}