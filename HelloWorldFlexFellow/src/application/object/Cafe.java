package application.object;

public class Cafe extends Ingredient {
    public Cafe(int timeToFill, double amount) {
        super(timeToFill, amount);
        this.name = "cafe";
        this.velocity = 1;
        this.weightPerVolume = 2.0;
    }
}
