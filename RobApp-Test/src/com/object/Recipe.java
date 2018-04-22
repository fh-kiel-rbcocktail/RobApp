package com.object;

import java.util.*;

public class Recipe {
	private Map<String, Ingredient> ingredients;
        private Map<Integer, String> ingredientsIdx;
	private String name = "";
	
	public Recipe(String name) {
		this.ingredients = new HashMap<String, Ingredient>();
                this.ingredientsIdx = new HashMap<Integer, String>();
		// lower case and trim String
		name = name.toLowerCase().trim();
		this.setName(name);
	}
	
	public Recipe(String name, Map<String, Ingredient> ingredients) {
                this.ingredientsIdx = new HashMap<Integer, String>();
		this.setIngredients(ingredients);
		// lower case and trim String
		name = name.toLowerCase().trim();
		this.setName(name);
	}
	
        public Map<String, Ingredient> getIngredients() {
		return this.ingredients ;
	}
	
	public void addIngredients(Ingredient ingredient) {
                this.ingredientsIdx.put(this.ingredients.size(), ingredient.getName());
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
        
        public Ingredient getIngredients(int i) {
            return this.ingredients.get(this.ingredientsIdx.get(i));
        }

	public void setIngredients(Map<String, Ingredient> ingredients) {
		this.ingredients = ingredients;
                // TODO: index
                int i = 0;
                for(Map.Entry<String, Ingredient> in: ingredients.entrySet()) {
                    this.ingredientsIdx.put(i, in.getKey());
                    i += 1;
                }
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
