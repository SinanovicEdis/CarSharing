package si.um.feri.ita.vaje.drivings.dto.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Result values: ok, not_ok
 */
@JsonInclude(value = Include.NON_NULL)
public record PostDrivingDataResponse(String result) {

	public PostDrivingDataResponse() {
		this("ok");
	}

}
