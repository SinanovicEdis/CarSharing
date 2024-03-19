package si.um.feri.ita.vaje;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class UporabnikServiceTest {
    private static Uporabnik.CreateUporabnikRequest uporabnikRequest;

    @BeforeAll
    public static void setup() {
        uporabnikRequest = Uporabnik.CreateUporabnikRequest.newBuilder()
                .setUporabniskoIme("Test")
                .setStarost(99)
                .build();
    }

    @Test
    public void testCreateUporabnik() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9001)
                .usePlaintext()
                .build();

        var stub = UporabnikServiceGrpc.newBlockingStub(channel);

        Uporabnik.UporabnikResponse response = stub.createUporabnik(uporabnikRequest);

        assertEquals("Test", response.getUporabniskoIme());
        assertEquals(99, response.getStarost());

        channel.shutdown();
    }
}
