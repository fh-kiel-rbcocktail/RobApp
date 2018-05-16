package application.object;

public class Orange extends Ingredient {
    public Orange(int timeToFill, double amount) {
        super(timeToFill, amount);
        this.name = "orange";
        this.velocity = 1;
        this.weightPerVolume = 1.5;
    }
}
