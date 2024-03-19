package si.um.feri.ita.vaje.drivings.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import si.um.feri.ita.vaje.drivings.dao.DrivingDataRepository;
import si.um.feri.ita.vaje.drivings.vao.DrivingData;

@CrossOrigin
@RestController
public class DrivingDataHistoryController {

	private static final Logger log = Logger.getLogger(DrivingDataHistoryController.class.toString());

	public DrivingDataHistoryController(DrivingDataRepository dao) {
		this.dao = dao;
	}

	private DrivingDataRepository dao;

	@Value("${history.dayslimit}")
	private int envHistoryDayslimit;

	@GetMapping("/history")
	public @ResponseBody Iterable<si.um.feri.ita.vaje.drivings.dto.DrivingData> getHistory() {
		long history = System.currentTimeMillis() - envHistoryDayslimit * 3_600_000 * 24;
		List<si.um.feri.ita.vaje.drivings.dto.DrivingData> ret = new ArrayList<>();
		for (DrivingData m : dao.findByCreatedLongGreaterThan(history)) {
			ret.add(m.toDto());
		}
		return ret;
	}

}