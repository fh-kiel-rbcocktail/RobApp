package application;

import java.util.Map;

import application.object.*;
import application.object.Recipe;

public interface IRecipeScript {
	// Generate recipe
	public Recipe generateRecipe(String name);
	public Recipe generateCustomizedRecipe(String name, Map<String, Float> ingredients);
	        
    // Get all recipes and ingredients
    public int menuSize();
    public Recipe getNextRecipe(int i);
    public int ingredientListSize(String recipeName);
    public int ingredientListSize(Recipe customizedRecipe);
    public Ingredient getNextIngredient(String recipeName, int i);
    public Ingredient getNextIngredient(Recipe customizedRecipe, int i);
    
    // Get a specific recipe and ingredient
    public Recipe getRecipe(String recipeName);
    public Ingredient getIngredient(String recipeName, String ingredientName);
    public Ingredient getIngredient(Recipe customizedRecipe, String ingredientName);
    
    // Weight conversion
    public double convertWeight(String recipeName);
    public double convertWeight(Recipe customizedRecipe);
}
