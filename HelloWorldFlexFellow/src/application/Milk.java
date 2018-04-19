package application;

public class Milk extends Ingredient {

	public Milk(float timeToFill, double amount) {
		super(timeToFill, amount);
		this.name = "Milk";
		this.weightPerVolume = 1.04;
		this.velocity = 1;
	}
}
