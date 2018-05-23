package application.object;

public class Tea extends Ingredient {
	public Tea(int timeToFill, double amount) {
        super(timeToFill, amount);
        this.name = "tea";
        this.velocity = 1;
        this.weightPerVolume = 2.0;
    }
}
