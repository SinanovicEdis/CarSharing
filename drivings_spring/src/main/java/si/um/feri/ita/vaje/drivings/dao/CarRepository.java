package si.um.feri.ita.vaje.drivings.dao;

import org.springframework.data.repository.CrudRepository;
import si.um.feri.ita.vaje.drivings.vao.Car;

public interface CarRepository extends CrudRepository<Car, Integer> {

}