package service

import io.grpc.Status
import io.grpc.stub.StreamObserver
import io.quarkus.grpc.GrpcService
import jakarta.inject.Inject
import model.Uporabnik
import org.bson.types.ObjectId
import repository.UporabnikRepository
import si.um.feri.ita.vaje.UporabnikServiceGrpc
import si.um.feri.ita.vaje.Uporabnik.*;

@GrpcService
class UporabnikService : UporabnikServiceGrpc.UporabnikServiceImplBase() {

    @Inject
    private lateinit var uporabnikRepository: UporabnikRepository

    override fun createUporabnik(
        request: CreateUporabnikRequest,
        responseObserver: StreamObserver<UporabnikResponse>
    ) {
        var uporabnik = Uporabnik(
            uporabniskoIme = request.uporabniskoIme,
            starost = request.starost
        );

        uporabnikRepository.persist(uporabnik);

        responseObserver.onNext(
            UporabnikResponse.newBuilder()
                .setId(uporabnik.id.toString())
                .setUporabniskoIme(uporabnik.uporabniskoIme)
                .setStarost(uporabnik.starost)
                .build()
        )
        responseObserver.onCompleted()
    }

    override fun getUporabnik(
        request: GetUporabnikRequest,
        responseObserver: StreamObserver<UporabnikResponse>
    ) {
        try {
            val objectId = ObjectId(request.id) // Convert String to ObjectId
            val uporabnik = uporabnikRepository.findById(objectId)
            if (uporabnik != null) {
                val response = UporabnikResponse.newBuilder()
                    .setId(uporabnik.id.toString())
                    .setUporabniskoIme(uporabnik.uporabniskoIme)
                    .setStarost(uporabnik.starost)
                    .build()
                responseObserver.onNext(response)
            } else {
                responseObserver.onError(Status.NOT_FOUND.asRuntimeException())
            }
        } catch (e: IllegalArgumentException) {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Invalid ID format").asRuntimeException())
        }
        responseObserver.onCompleted()
    }

    override fun deleteUporabnik(
        request: GetUporabnikRequest,
        responseObserver: StreamObserver<UporabnikResponse>
    ) {
        try {
            val objectId = ObjectId(request.id) // Convert String to ObjectId
            val uporabnik = uporabnikRepository.findById(objectId)
            if (uporabnik != null) {
                uporabnikRepository.delete(uporabnik)
                val response = UporabnikResponse.newBuilder()
                    .setId(uporabnik.id.toString())
                    .setUporabniskoIme(uporabnik.uporabniskoIme)
                    .setStarost(uporabnik.starost)
                    .build()
                responseObserver.onNext(response)
            } else {
                responseObserver.onError(Status.NOT_FOUND.asRuntimeException())
            }
        } catch (e: IllegalArgumentException) {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Invalid ID format").asRuntimeException())
        }
        responseObserver.onCompleted()
    }

    override fun updateUporabnik(request: UpdateUporabnikRequest, responseObserver: StreamObserver<UporabnikResponse>) {
        try {
            val uporabnik = uporabnikRepository.findById(ObjectId(request.id))
            if (uporabnik != null) {
                uporabnik.uporabniskoIme = request.uporabniskoIme
                uporabnik.starost = request.starost.toInt()
                uporabnikRepository.update(uporabnik)
                val response = UporabnikResponse.newBuilder()
                    .setId(uporabnik.id.toString())
                    .setUporabniskoIme(uporabnik.uporabniskoIme)
                    .setStarost(uporabnik.starost)
                    .build()
                responseObserver.onNext(response)
            } else {
                responseObserver.onError(Status.NOT_FOUND.asRuntimeException())
            }
        } catch (e: IllegalArgumentException) {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Invalid ID format").asRuntimeException())
        }
        responseObserver.onCompleted()
    }
}