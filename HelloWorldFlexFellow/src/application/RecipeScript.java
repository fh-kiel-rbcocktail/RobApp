package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import application.object.Ingredient;
import application.object.Recipe;

public class RecipeScript implements IRecipeScript {
	private Map<String, Recipe> menu = new HashMap<String, Recipe>();
	
	public RecipeScript() {
		readScript();
	}
	
	private void readScript(){
		JSONParser parser = new JSONParser();
	     try {
	         Object obj = parser.parse(new FileReader("D:\\test.json"));

	         JSONObject jsonObject = (JSONObject) obj;
	         JSONArray recipes = (JSONArray) jsonObject.get("recipes");
	         Iterator<JSONObject> iterRecipes = recipes.iterator();
	         while(iterRecipes.hasNext()) {
	        	 JSONObject recipe = iterRecipes.next();
	        	 String nameRecipe = (String) recipe.get("name");
	        	 Recipe aRecipe = new Recipe(nameRecipe);
	        	 // loop array
		         JSONArray iterIngredient = (JSONArray) recipe.get("ingredients");
		         Iterator<JSONObject> iterator = iterIngredient.iterator();
		         while (iterator.hasNext()) {
		        	 JSONObject ing = iterator.next();
		        	 String nameIngredient = (String)ing.get("nameIngredient");
		        	 double timeToFill =(double) ing.get("timeToFill");
		        	 double amount = (double)ing.get("amount");
			         Ingredient ingre = new Ingredient(nameIngredient, timeToFill, amount);
			         aRecipe.addIngredients(ingre);
		         }
		         menu.put(nameRecipe, aRecipe);
	         }
	     } catch (FileNotFoundException e) {
	         e.printStackTrace();
	     } catch (IOException e) {
	         e.printStackTrace();
	     } catch (ParseException e) {
	         e.printStackTrace();
	     }
	}
	
	public Recipe getRecipe(String name) {
		// lower case and trim String
		name = name.toLowerCase().trim();
		// TODO: Should deep copy object
		Recipe currRecipe = menu.get(name);
//		Map<String, Ingredient> listIngredients = new HashMap<String, Ingredient>() ;
//		for(Map.Entry<String, Ingredient> ingre : currRecipe.getIngredients().entrySet()){
//			Ingredient i = new Ingredient(ingre.getKey(), ingre.getValue().getTimeToFill(), ingre.getValue().getAmount());
//			listIngredients.put(ingre.getKey(),i);
//		}
//		Recipe result = new Recipe(currRecipe.getName(), listIngredients);
		return currRecipe;
	}
	
	public Recipe customizeRecipe(String name, Map<String, Float> ingredients) {
		Recipe newRecipe = getRecipe(name);
		for(Map.Entry<String, Float> ingre : ingredients.entrySet()) {
			newRecipe.modifyIngredients(ingre.getKey().toString(), (Float)ingre.getValue());
		}
		return newRecipe;
	}
}
