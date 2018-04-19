package application;

public class Orange extends Ingredient {
	
	public Orange(float timeToFill, double amount) {
		super(timeToFill, amount);
		this.name = "Orange Juice";
		this.weightPerVolume = 1.05;
		this.velocity = 1;
	}
}