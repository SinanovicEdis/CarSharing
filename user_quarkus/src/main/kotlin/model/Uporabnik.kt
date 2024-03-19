package model

import io.quarkus.mongodb.panache.common.MongoEntity
import org.bson.types.ObjectId

@MongoEntity(database = "uporabniki")
data class Uporabnik(
    var id: ObjectId? = null,
    var uporabniskoIme: String,
    var starost: Int
) {
    // Public empty constructor
    constructor() : this(ObjectId(), "", 0)
}
