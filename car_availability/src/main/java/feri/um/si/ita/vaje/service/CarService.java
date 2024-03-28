package feri.um.si.ita.vaje.service;

import feri.um.si.ita.vaje.models.Car;
import feri.um.si.ita.vaje.models.CarPost;
import feri.um.si.ita.vaje.repository.CarRepository;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/availability")
public class CarService {
    private static final Logger log = Logger.getLogger(CarService.class.getName());

    @Inject
    CarRepository CarRepository;

    @Inject
    ActiveMqService activeMqService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Car> addCarAvailability(CarPost v) {
        Car car = new Car();
        car.setName(v.name);
        car.setIsAvailable(v.isAvailable);

        Logger.getLogger(CarService.class.getName()).info("Adding car: " + car.getName());

        activeMqService.sendMessageToActiveMQ(car, "A new car availability info has been added");

        return CarRepository.addCar(car);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Car>> getCarsAvailability() {
        log.info("Getting cars");
        activeMqService.sendMessageToActiveMQ(null, "Getting all cars availability info");
        return CarRepository.listAll()
                .onItem()
                .transform(cars -> cars.stream().map(car -> {
                    Car v = new Car();
                    v.setId(car.getId());
                    v.setName(car.getName());
                    v.setIsAvailable(car.getIsAvailable());
                    return v;
                }).collect(Collectors.toList()));
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    //@Transactional
    public Uni<Boolean> deleteCarAvailability(@PathParam("id") Long id) {
        log.info("Deleting car: " + id);
        Car car = (Car) CarRepository.findById(id);
        activeMqService.sendMessageToActiveMQ(car, "A car availability info has been deleted");
        return CarRepository.deleteCar(id);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Car> addCarAvailabilityById(@PathParam("id") Long id) {
        log.info("Getting car: " + id);
        Car car = (Car) CarRepository.findById(id);
        activeMqService.sendMessageToActiveMQ(car, "Getting car availability info by id");
        return CarRepository.findById(id);
    }
}
