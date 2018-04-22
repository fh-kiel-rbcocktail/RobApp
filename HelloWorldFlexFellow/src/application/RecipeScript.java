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

import application.object.*;

public class RecipeScript implements IRecipeScript {
	private Map<String, Recipe> menu = new HashMap<String, Recipe>();
    private Map<Integer, String> menuIdx = new HashMap<Integer, String>();

	public RecipeScript() {
		readScript();
	}
    
    private Ingredient initializeIngredient(String name, double time, double amount) {
        // lower case and trim String
        name = name.toLowerCase().trim();
        if(name.equals("milk"))
            return new Milk(time, amount);
        else if(name.equals("orange"))
            return new Orange(time, amount);
        return new Cafe(time, amount);
    }
    
	private void readScript() {
		JSONParser parser = new JSONParser();
	     try {
	         Object obj = parser.parse(new FileReader("C:\\Users\\Tien\\Desktop\\test.json"));
	
	         JSONObject jsonObject = (JSONObject) obj;
	         JSONArray recipes = (JSONArray) jsonObject.get("recipes");
	         Iterator<JSONObject> iterRecipes = recipes.iterator();
	             int iter = 0;
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
			         Ingredient ingre = initializeIngredient(nameIngredient, timeToFill, amount);
			         aRecipe.addIngredients(ingre);
		         }
		         this.menu.put(nameRecipe, aRecipe);
	                     this.menuIdx.put(iter, nameRecipe);
	                     iter += 1;
	         }
	     } catch (FileNotFoundException e) {
	         e.printStackTrace();
	     } catch (IOException e) {
	         e.printStackTrace();
	     } catch (ParseException e) {
	         e.printStackTrace();
	     }
	}
    
	@Override
	public Recipe generateRecipe(String name) {
        // lower case and trim String
        name = name.toLowerCase().trim();
        // TODO: Should deep copy object
        Recipe currRecipe = menu.get(name);
        Map<String, Ingredient> listIngredients = new HashMap<String, Ingredient>() ;
        for(Map.Entry<String, Ingredient> ingre : currRecipe.getIngredients().entrySet()){
                Ingredient i = initializeIngredient(ingre.getKey(), ingre.getValue().getTimeToFill(), ingre.getValue().getAmount());
                listIngredients.put(ingre.getKey(),i);
        }
        Recipe result = new Recipe(currRecipe.getName(), listIngredients);
        return result;
	}
	@Override
	public Recipe generateCustomizedRecipe(String name, Map<String, Float> ingredients) {
		Recipe newRecipe = generateRecipe(name);
		for(Map.Entry<String, Float> ingre : ingredients.entrySet()) {
	                newRecipe.modifyIngredients(ingre.getKey().toString(), (Float)ingre.getValue());
		}
		return newRecipe;
	}
    
    @Override
    public int menuSize() {
        return this.menu.size();
    }
    @Override
    public Recipe getNextRecipe(int i){
        return this.menu.get(this.menuIdx.get(i));
}
    @Override
    public int ingredientListSize(String recipeName) {
        // lower case and trim String
        recipeName = recipeName.toLowerCase().trim();
        return this.menu.get(recipeName).getIngredients().size();
    }
    @Override
    public int ingredientListSize(Recipe customizedRecipe) {
        return customizedRecipe.getIngredients().size();
    }
    @Override
    public Ingredient getNextIngredient(String recipeName, int i) {
        return this.menu.get(recipeName).getIngredients(i);
    }
    @Override
    public Ingredient getNextIngredient(Recipe customizedRecipe, int i) {
        return customizedRecipe.getIngredients(i);
    }

    @Override
    public Recipe getRecipe(String recipeName) {
        // lower case and trim String
        recipeName = recipeName.toLowerCase().trim();
        return this.menu.get(recipeName);
    }
    @Override
    public Ingredient getIngredient(String recipeName, String ingredientName) {
        // lower case and trim String
        recipeName = recipeName.toLowerCase().trim();
        ingredientName = ingredientName.toLowerCase().trim();
        return this.menu.get(recipeName).getIngredients(ingredientName);
    }
    @Override
    public Ingredient getIngredient(Recipe customizedRecipe, String ingredientName) {
        ingredientName = ingredientName.toLowerCase().trim();
        return customizedRecipe.getIngredients().get(ingredientName);
    }
    
    @Override
    public double convertWeight(String recipeName) {
        // lower case and trim String
        recipeName = recipeName.toLowerCase().trim();
        double myWeight = 0;
        Recipe myRecipe = this.menu.get(recipeName);
        for(Map.Entry<String, Ingredient> in: myRecipe.getIngredients().entrySet()) {
            //TODO: should be same weight unit
            myWeight += in.getValue().convertWeight();
        }
        return myWeight;
    }
    @Override
    public double convertWeight(Recipe customizedRecipe) {
        double myWeight = 0;
        for(Map.Entry<String, Ingredient> in: customizedRecipe.getIngredients().entrySet()) {
            //TODO: should be same weight unit
            myWeight += in.getValue().convertWeight();
        }
        return myWeight;
    }
}
