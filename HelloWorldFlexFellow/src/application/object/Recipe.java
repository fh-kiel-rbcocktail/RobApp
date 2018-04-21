package application.object;

import java.util.*;


public class Recipe {
	private Map<String, Ingredient> ingredients;
	private String name = "";
	
	public Recipe(String name) {
		this.ingredients = new HashMap<String, Ingredient>();
		// lower case and trim String
		name = name.toLowerCase().trim();
		this.setName(name);
	}
	
	public Recipe(String name, Map<String, Ingredient> ingredients) {
		this.setIngredients(ingredients);
		// lower case and trim String
		name = name.toLowerCase().trim();
		this.setName(name);
	}
	
	public void addIngredients(Ingredient ingredient) {
		this.ingredients.put(ingredient.getName(), ingredient);
	}
	
	public boolean modifyIngredients(String name, float timeToFill) {
		// lower case and trim String
		name = name.toLowerCase().trim();
		// get
		if(this.ingredients.containsKey(name)) {
			this.ingredients.get(name).setTimeToFill(timeToFill);
			return true;
		}
		return false;
	}

	public Ingredient getIngredients(String name) {
		// lower case and trim String
		name = name.toLowerCase().trim();
		// get
		return ingredients.get(name);
	}

	public void setIngredients(Map<String, Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public Map<String, Ingredient> getIngredients() {
		return this.ingredients ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
