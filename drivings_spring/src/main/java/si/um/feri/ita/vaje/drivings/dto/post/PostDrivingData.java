package si.um.feri.ita.vaje.drivings.dto.post;

/**
 * id - id of a product, being measured
 * distance - a measurement
 */
public record PostDrivingData(
	int id,
	double distance
	) {}