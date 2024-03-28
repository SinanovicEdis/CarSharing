package feri.um.si.ita.vaje.repository;

import feri.um.si.ita.vaje.models.Car;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import java.util.List;

@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {

    public Uni<Car> addCar(Car car) {
        return Panache.withTransaction(car::persist)
                .replaceWith(car);
    }

    public Uni<Car> updateCar(Car car) {
        return Panache.withTransaction(car::persist)
                .replaceWith(car);
    }

    public Uni<Boolean> deleteCar(Long id) {
        return Panache.withTransaction(() -> deleteById(id));
    }

    public Uni<List<Car>> listAll() {
        return findAll().list();
    }

}
