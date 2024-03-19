package repository

import io.quarkus.mongodb.panache.PanacheMongoRepository
import jakarta.enterprise.context.ApplicationScoped
import model.Uporabnik

@ApplicationScoped
class UporabnikRepository : PanacheMongoRepository<Uporabnik>