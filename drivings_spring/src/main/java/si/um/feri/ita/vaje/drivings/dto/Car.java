package si.um.feri.ita.vaje.drivings.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public record Car(
		int id,
		String model,
		String brand,
		int year) {
	public Car(Car dto) {
		this(dto.id(), dto.model(), dto.brand(), dto.year());
	}
}
