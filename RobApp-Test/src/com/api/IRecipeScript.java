package com.api;

import java.util.Map;

import com.object.Recipe;

public interface IRecipeScript {
	public Recipe getRecipe(String name);
	public Recipe customizeRecipe(String name, Map<String, Float> ingredients);
}
