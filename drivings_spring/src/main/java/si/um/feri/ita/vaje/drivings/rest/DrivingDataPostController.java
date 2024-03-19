package si.um.feri.ita.vaje.drivings.rest;

import java.util.Optional;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import si.um.feri.ita.vaje.drivings.dao.DrivingDataRepository;
import si.um.feri.ita.vaje.drivings.dao.CarRepository;
import si.um.feri.ita.vaje.drivings.dto.post.PostDrivingData;
import si.um.feri.ita.vaje.drivings.dto.post.PostDrivingDataResponse;
import si.um.feri.ita.vaje.drivings.vao.Car;
import si.um.feri.ita.vaje.drivings.vao.DrivingData;

/**
 * Posting a new measurement
 */
@CrossOrigin
@RestController
public class DrivingDataPostController {

	private static final Logger log = Logger.getLogger(DrivingDataPostController.class.toString());

	public DrivingDataPostController(DrivingDataRepository dao, CarRepository carDao) {
		this.dao = dao;
		this.carDao = carDao;
	}

	private DrivingDataRepository dao;
	
	private CarRepository carDao;
	
	@PostMapping("/driving_data")
	public ResponseEntity<PostDrivingDataResponse> postProductMeasurement(@RequestBody PostDrivingData m) {
		//validate
		Optional<Car> val= carDao.findById(m.id());
		if (val.isEmpty()) {
			log.info("/driving_data sent: "+m+"; Product not found!");
			return new ResponseEntity("car-not-found",HttpStatus.NOT_ACCEPTABLE);
		}
		Car car =val.get();

		DrivingData vao=new DrivingData(m, car);
		boolean ok=true;

		//action?
		if (car.getYear() > 1999) {
			log.info("/driving_data sent: "+m+"; product: "+ car +"; ACTION NEEDED-Too young");
			ok=false;
		}
		if (car.getYear() <= 1999) {
			log.info("/driving_data sent: "+m+"; product: "+ car +"; ACTION NEEDED-Oldtimer");
			ok=false;
		}

		//save
		vao.setOk(ok);
		dao.save(vao);

		//response
	    return ResponseEntity.ok(new PostDrivingDataResponse(ok?"ok":"not_ok"));
	}
	
}