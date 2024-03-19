package si.um.feri.ita.vaje.drivings.vao;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import si.um.feri.ita.vaje.drivings.Constants;
import si.um.feri.ita.vaje.drivings.dto.post.PostDrivingData;

@Entity
@Table(name = "DrivingData")
public class DrivingData {
	
	public DrivingData() {
	}

	public DrivingData(PostDrivingData m, Car p) {
		this.value=m.distance();
		this.distance=m.distance();
		this.subject= Constants.MeasurementSubjects.PRODUCT.getValue();
		this.car =p;
	}
	
	public si.um.feri.ita.vaje.drivings.dto.DrivingData toDto() {
		return new si.um.feri.ita.vaje.drivings.dto.DrivingData(
			id,
			Constants.JSON_DATE_FORMAT.format(created),
			(car !=null)? car.getId():-1,
			value,
			isOk
		);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private double value;

	private double distance;

	private LocalDateTime created=LocalDateTime.now();

	private long createdLong=System.currentTimeMillis();

	private int subject=Constants.MeasurementSubjects.PRODUCT.getValue();

	private boolean isOk=true;

	@ManyToOne
	private Car car;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public long getCreatedLong() {
		return createdLong;
	}

	public void setCreatedLong(long createdLong) {
		this.createdLong = createdLong;
	}

	public int getSubject() {
		return subject;
	}

	public void setSubject(int subject) {
		this.subject = subject;
	}
	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean ok) {
		isOk = ok;
	}

	public Car getProduct() {
		return car;
	}

	public void setProduct(Car car) {
		this.car = car;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Measurement{" +
				"id=" + id +
				", value=" + value +
				", created=" + created +
				", createdLong=" + createdLong +
				", subject=" + subject +
				", distance='" + distance + '\'' +
				", isOk=" + isOk +
				", product=" + car +
				'}';
	}

}
