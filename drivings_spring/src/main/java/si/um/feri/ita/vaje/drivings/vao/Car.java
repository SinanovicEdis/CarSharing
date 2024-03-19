package si.um.feri.ita.vaje.drivings.vao;

import javax.persistence.*;

@Entity
@Table(name = "Car")
public class Car {

	public Car() {
	}

	public Car(si.um.feri.ita.vaje.drivings.dto.Car dto) {
		setBrand(dto.model());
		setBrand(dto.brand());
		setYear(dto.year());
	}

	public void updateFrom(si.um.feri.ita.vaje.drivings.dto.Car dto) {
		setModel(dto.model());
		setBrand(dto.brand());
		setYear(dto.year());
	}

	public si.um.feri.ita.vaje.drivings.dto.Car toDto() {
		return new si.um.feri.ita.vaje.drivings.dto.Car(
				getId(),
				getModel(),
				getBrand(),
				getYear());
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int id;

	private String brand;
	private String model;
	private int year;
	private String color;
	private int price;

	public int getId() {
		return id;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public int getYear() {
		return year;
	}

	public String getColor() {
		return color;
	}

	public int getPrice() {
		return price;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Car{" +
				"id=" + id +
				", brand='" + brand + '\'' +
				", model='" + model + '\'' +
				", year=" + year +
				", color='" + color + '\'' +
				", price=" + price +
				'}';
	}

}
