package application.object;

import com.kuka.roboticsAPI.geometricModel.Frame;

public class Ingredient {
	// Variables
	private double timeToFill = 0;
	private double amount = 0;

	protected double weightPerVolume; // gr/ml
	protected String name;
	private String unitOfWeight;
	private String unitOfVolume;
	protected float velocity;
	private Frame position;
	
	// Constructors
	public Ingredient() {
		this.weightPerVolume = 1.0;
		this.name = "";
		this.unitOfWeight = "gr";
		this.unitOfVolume = "ml";
		this.velocity = 1;
	}
	
	public Ingredient(String name, double timeToFill, double amount) {
		this.name = name;
		this.timeToFill = timeToFill;
		this.amount = amount;
	//	this.weightPerVolume = ingre.weightPerVolume;
		//this.unitOfVolume = ingre.unitOfVolume;
		//this.unitOfWeight = ingre.unitOfWeight;
		//this.velocity = ingre.velocity;
		//this.amount = ingre.amount;
		//this.timeToFill = ingre.timeToFill;
	}

	public Ingredient(double timeToFill, double amount) {
		this.setTimeToFill(timeToFill);
		this.setAmount(amount);
	}
	
	// Setter and Getter
	public void setTimeToFill(double timeToFill) {
		this.timeToFill = timeToFill;
	}
	
	public double getTimeToFill() {
		return this.timeToFill;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getWeightPerVolume() {
		return weightPerVolume;
	}

	public String getUnitOfWeight() {
		return unitOfWeight;
	}

	public void setUnitOfWeight(String unitOfWeight) {
		this.unitOfWeight = unitOfWeight;
	}

	public String getUnitOfVolume() {
		return unitOfVolume;
	}

	public void setUnitOfVolume(String unitOfVolume) {
		this.unitOfVolume = unitOfVolume;
	}

	public Frame getPosition() {
		return position;
	}

	public void setPosition(Frame position) {
		this.position = position;
	}
	
	public String getName() {
		return this.name;
	}
	
	// Methods
	public double convertWeight() {
		return this.weightPerVolume * this.amount;
	}
	
	/*public void convertTime() {
		this.timeToFill = (float) (this.amount / this.velocity);
	}
	
	public void convertAmount() {
		this.amount = this.velocity * this.timeToFill;
	}*/
}
