package si.um.feri.ita.vaje.drivings.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public record DrivingData(
	int id,
	String date,
	int carId,
	double distance,
	boolean isOk
	) {}
