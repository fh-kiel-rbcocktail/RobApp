package com.object;

/**
 *
 * @author Tien
 */
public class Milk extends Ingredient {
    public Milk(double timeToFill, double amount) {
        super(timeToFill, amount);
        this.name = "milk";
        this.velocity = 1;
        this.weightPerVolume = 2.8;
    }
}
