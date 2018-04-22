package com.object;

public abstract class Ingredient {
    // Variables
    private double timeToFill;
    private double amount;

    protected double weightPerVolume = 1.0; // gr/ml
    protected String name = "";
    private String unitOfWeight = "gr";
    private String unitOfVolume = "ml";
    protected float velocity = 1;
    // private Frame position;

    // Constructors
    public Ingredient() {
        this.timeToFill = 0;
        this.amount = 0;
    }

    public Ingredient(double timeToFill, double amount) {
            this.timeToFill = timeToFill;
            this.amount = amount;
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
