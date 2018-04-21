package com.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.api.RecipeScript;
import com.object.Ingredient;
import com.object.Recipe;

public class tester {
	public static void main( String[] args ) {
		RecipeScript menu = new RecipeScript();
	
		Map<String, Recipe> menus = menu.getMenu();
		System.out.println(menus.size());
//		for(Map.Entry<String, Recipe> me : menus.entrySet() ) {
//			System.out.println("name of recipe" + me.getKey());
//			Map<String, Ingredient> ingredients = me.getValue().getIngredients();
//			for(Map.Entry<String, Ingredient> in: ingredients.entrySet()) {
//				System.out.println("----- name of ingredient: " + in.getKey());
//			}
//		}
		
		Recipe cop = menus.get("milky orange juice -- 1");
		System.out.println("name of recipe: " + cop.getName());
		Map<String, Ingredient> ingredients = cop.getIngredients();
		for(Map.Entry<String, Ingredient> in: ingredients.entrySet()) {
			System.out.println("----- name of ingredient: " + in.getValue().getName());
			System.out.println("----- name of ingredient: " + in.getValue().getAmount());
			System.out.println("----- name of ingredient: " + in.getValue().getTimeToFill());
		}
		
		List<Recipe> recipes = new ArrayList<Recipe>();
		Recipe cop2 = menus.get("milky orange juice -- 1");
		recipes.add(cop);
		recipes.add(cop2);
		//System.out.println(cop.getIngredients());
		cop.getIngredients().get("milk-1").setTimeToFill(13.0);
		System.out.println(cop2.getIngredients().get("milk-1").getTimeToFill());
		System.out.println(cop.getIngredients().get("milk-1").getTimeToFill());
	}
}
