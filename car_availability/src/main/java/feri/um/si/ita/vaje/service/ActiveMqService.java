package feri.um.si.ita.vaje.service;

import feri.um.si.ita.vaje.models.Car;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

public class ActiveMqService {
    @Inject
    @Channel("car_availability")
    Emitter<String> borrowingEmitter;

    public void sendMessageToActiveMQ(Car car, String message) {
        if(car == null) {
            borrowingEmitter.send(message);
        }
        else {
            String targetedMessage = String.format("Car: %s, Message: %s", car.toString(), message);
            borrowingEmitter.send(targetedMessage);
        }
    }
}
