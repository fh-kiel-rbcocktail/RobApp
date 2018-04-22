package com.object;

/**
 *
 * @author Tien
 */
public class Cafe extends Ingredient {
    public Cafe(double timeToFill, double amount) {
        super(timeToFill, amount);
        this.name = "cafe";
        this.velocity = 1;
        this.weightPerVolume = 2.0;
    }
}
