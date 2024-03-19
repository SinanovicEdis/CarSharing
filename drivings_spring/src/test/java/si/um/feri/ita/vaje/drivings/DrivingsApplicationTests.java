package si.um.feri.ita.vaje.drivings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import si.um.feri.ita.vaje.drivings.dao.CarRepository;
import si.um.feri.ita.vaje.drivings.rest.CarController;
import si.um.feri.ita.vaje.drivings.vao.Car;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
class ApplicationTests {
    @Autowired
    private CarController carController;
    private CarRepository carRepository;

    @BeforeEach
    public void setUp() {
        carRepository = mock(CarRepository.class);
        carController = new CarController(carRepository);
    }

    @Test
    void contextLoads() {
        assertThat(carController).isNotNull();
    }

    @Test
    public void testGetAllCars() {
        // Mock data
        Iterable<si.um.feri.ita.vaje.drivings.vao.Car> cars = new ArrayList<>();
        when(carRepository.findAll()).thenReturn(cars);

        // Call the method
        Iterable<si.um.feri.ita.vaje.drivings.dto.Car> result = carController.getAllCars();

        // Verify the result
        assertEquals(((ArrayList<Car>) cars).size(), StreamSupport.stream(result.spliterator(), false).count());
    }

    @Test
    public void testGetCarsById() {
        // Mock data
        int carId = 1;
        si.um.feri.ita.vaje.drivings.vao.Car car = new si.um.feri.ita.vaje.drivings.vao.Car();
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));

        // Call the method
        ResponseEntity<si.um.feri.ita.vaje.drivings.dto.Car> result = carController.getAllCarsById(carId);

        // Verify the result
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testPostCar() {
        // Mock data
        Car p1=new Car();
        p1.setBrand("Volkswagen");
        p1.setModel("Passat");
        p1.setYear(2019);
        p1.setColor("Silver");
        p1.setPrice(13000);

        when(carRepository.save(any())).thenReturn(p1);

        // Call the method
        ResponseEntity<si.um.feri.ita.vaje.drivings.dto.Car> result = carController.postCar(p1.toDto());

        // Verify the result
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testPutCar() {
        // Mock data
        // Mock data
        Car p1=new Car();
        p1.setBrand("Volkswagen");
        p1.setModel("Passat");
        p1.setYear(2019);
        p1.setColor("Silver");
        p1.setPrice(13000);

        int carId = 1;
        si.um.feri.ita.vaje.drivings.dto.Car carDto = new si.um.feri.ita.vaje.drivings.dto.Car(p1.toDto());
        si.um.feri.ita.vaje.drivings.vao.Car existingCar = new si.um.feri.ita.vaje.drivings.vao.Car();
        when(carRepository.findById(carId)).thenReturn(Optional.of(existingCar));
        when(carRepository.save(Mockito.any())).thenReturn(existingCar);

        // Call the method
        ResponseEntity<si.um.feri.ita.vaje.drivings.dto.Car> result = carController.putCar(carId, carDto);

        // Verify the result
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testDeleteCar() {
        // Mock data
        int carId = 1;
        si.um.feri.ita.vaje.drivings.vao.Car existingCar = new si.um.feri.ita.vaje.drivings.vao.Car();
        when(carRepository.findById(carId)).thenReturn(Optional.of(existingCar));

        // Call the method
        ResponseEntity<String> result = carController.deleteCar(carId);

        // Verify the result
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}

