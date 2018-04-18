package application;

import com.kuka.roboticsAPI.geometricModel.Frame;

public abstract class Ingredient {
	// Variables
	private float timeToFill = 0;
	private double amount = 0;
	protected double weightPerVolume = 1.0; // gr/ml
	protected String name = "";
	private String unitOfWeight = "gr";
	private String unitOfVolume = "ml";
	private Frame position;
	
	// Constructors
	public Ingredient(float timeToFill) {
		this.setTimeToFill(timeToFill);
	}
	
	// Setter and Getter
	public void setTimeToFill(float timeToFill) {
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
	
	public float convertTime() {
		return 1;
	}
}
