package si.um.feri.ita.vaje.drivings.rest.health;

import java.net.InetAddress;
import java.util.logging.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple "echo" rest
 */
@CrossOrigin
@RestController
public class InfoController {

    private static final Logger log = Logger.getLogger(InfoController.class.toString());

    @GetMapping("/info")
    public ResponseEntity<String> getServiceInfo() {
    	String location="??";
    	
    	try {
            location=InetAddress.getLocalHost().getHostName()+":"+InetAddress.getLocalHost().getHostAddress();
            log.info("Listening on: " + location);
		} catch (Exception e) {
            log.info("Unable to get local address: "+e.getMessage());
		}

        return ResponseEntity.ok(location+"->"+this.getClass().toString());
    }
    
}
