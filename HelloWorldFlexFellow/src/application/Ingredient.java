package application;

import com.kuka.roboticsAPI.geometricModel.Frame;

public class Ingredient {
	// Variables
	private float timeToFill = 0;
	private double amount = 0;
	private double weightPerVolume = 1.0;
	private String name = "";
	private String unitOfWeight = "ml";
	private String unitOfVolume = "gr";
	private Frame position;
	
	public Ingredient(String name, double weightPerVolume, float timeToFill) {
		this.setTimeToFill(timeToFill);
		this.setName(name);
		this.weightPerVolume = weightPerVolume;
	}
	
	public Ingredient(String name, double weightPerVolume, double amount) {
		this.setAmount(amount);
		this.setName(name);
		this.weightPerVolume = weightPerVolume;
	}
	
	public Ingredient(String name, double weightPerVolume, double amount, String unitOfWeight, String unitOfVolume) {
		this.setAmount(amount);
		this.setUnitOfWeight(unitOfWeight);
		this.setUnitOfVolume(unitOfVolume);
		this.setName(name);
		this.weightPerVolume = weightPerVolume;
	}
	
	public Ingredient(String name, double weightPerVolume, float timeToFill, String unitOfWeight, String unitOfVolume) {
		this.setTimeToFill(timeToFill);
		this.setUnitOfWeight(unitOfWeight);
		this.setUnitOfVolume(unitOfVolume);
		this.setName(name);
		this.weightPerVolume = weightPerVolume;
	}
	
	public Ingredient(String name, double weightPerVolume, double amount, float timeToFill) {
		this.setAmount(amount);
		this.setTimeToFill(timeToFill);
		this.setName(name);
		this.weightPerVolume = weightPerVolume;
	}
	
	public Ingredient(String name, double weightPerVolume, double amount, float timeToFill, String unitOfWeight, String unitOfVolume) {
		this.setAmount(amount);
		this.setTimeToFill(timeToFill);
		this.setUnitOfWeight(unitOfWeight);
		this.setUnitOfVolume(unitOfVolume);
		this.setName(name);
		this.weightPerVolume = weightPerVolume;
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
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
