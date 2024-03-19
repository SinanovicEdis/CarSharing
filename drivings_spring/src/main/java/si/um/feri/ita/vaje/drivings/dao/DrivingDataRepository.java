package si.um.feri.ita.vaje.drivings.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import si.um.feri.ita.vaje.drivings.vao.Car;
import si.um.feri.ita.vaje.drivings.vao.DrivingData;

public interface DrivingDataRepository extends CrudRepository<DrivingData, Integer> {

	Long countByCar(Car p);

	List<DrivingData> findByCreatedLongGreaterThan(long createdLong);
}