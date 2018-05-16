package application.object;

public class Milk extends Ingredient {
    public Milk(int timeToFill, double amount) {
        super(timeToFill, amount);
        this.name = "milk";
        this.velocity = 1;
        this.weightPerVolume = 2.8;
    }
}
