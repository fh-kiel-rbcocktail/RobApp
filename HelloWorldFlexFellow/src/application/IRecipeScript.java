package application;

import java.util.Map;

import application.object.Recipe;

public interface IRecipeScript {
	public Recipe getRecipe(String name);
	public Recipe customizeRecipe(String name, Map<String, Float> ingredients);
}
