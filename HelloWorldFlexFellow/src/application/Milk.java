package application;

public class Milk extends Ingredient {

	public Milk(float timeToFill) {
		super(timeToFill);
		this.name = "Milk";
		this.weightPerVolume = 1.04;
	}
}
