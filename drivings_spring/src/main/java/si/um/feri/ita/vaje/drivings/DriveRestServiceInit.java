package si.um.feri.ita.vaje.drivings;

import si.um.feri.ita.vaje.drivings.dao.DrivingDataRepository;
import si.um.feri.ita.vaje.drivings.dao.CarRepository;
import si.um.feri.ita.vaje.drivings.dto.post.PostDrivingData;
import si.um.feri.ita.vaje.drivings.vao.DrivingData;
import si.um.feri.ita.vaje.drivings.vao.Car;
import java.util.logging.Logger;

/**
 * Initializing DB with some Products and Measurements
 */
public class DriveRestServiceInit {

    private static final Logger log = Logger.getLogger(DriveRestServiceInit.class.toString());

    public void populateTestDataIfNotPresent(
            CarRepository daoProd,
            DrivingDataRepository daoMeas
    ) {
        if (daoProd.count()>0) {
            log.info("populateTestData - skipped.");
            return;
        }
        log.info("populateTestData initiated.");

        Car p1=new Car();
        p1.setBrand("Volkswagen");
        p1.setModel("Golf");
        p1.setYear(2015);
        p1.setColor("Black");
        p1.setPrice(15000);
        daoProd.save(p1);

        Car p2=new Car();
        p2.setBrand("Audi");
        p2.setModel("A4");
        p2.setYear(2018);
        p2.setColor("Black");
        p2.setPrice(25000);
        daoProd.save(p2);

        PostDrivingData pm1=new PostDrivingData(p1.getId(),12500);
        daoMeas.save(new DrivingData(pm1,p1));

        PostDrivingData pm2=new PostDrivingData(p2.getId(),10000);
        daoMeas.save(new DrivingData(pm2,p2));
    }
}
