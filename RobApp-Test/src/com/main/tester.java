package com.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.api.IRecipeScript;
import com.api.RecipeScript;
import com.object.Ingredient;
import com.object.Recipe;

public class tester {
	public static void main( String[] args ) {
		IRecipeScript menu = new RecipeScript();
		
		Recipe cop = menu.getRecipe("milky orange juice -- 1");
		System.out.println("name of recipe: " + cop.getName());
		Map<String, Ingredient> ingredients = cop.getIngredients();
		for(Map.Entry<String, Ingredient> in: ingredients.entrySet()) {
			System.out.println("----- name of ingredient: " + in.getValue().getName());
			System.out.println("----- amount of ingredient: " + in.getValue().getAmount());
			System.out.println("----- time of ingredient: " + in.getValue().getTimeToFill());
		}
		
		List<Recipe> orders = new ArrayList<Recipe>();
		Recipe cop2 = menu.getRecipe("milky orange juice -- 1");
		orders.add(cop);
		orders.add(cop2);
		
		cop.getIngredients().get("milk-1").setTimeToFill(13.0);
        System.out.println(cop == cop2);
	}
}
