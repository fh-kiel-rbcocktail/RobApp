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

import application.object.Cafe;
import application.object.Ingredient;
import application.object.Milk;
import application.object.Orange;
import application.object.Recipe;
import application.object.Tea;


public class RecipeScript implements IRecipeScript {
	private Map<String, Recipe> menu = new HashMap<String, Recipe>();
    private Map<Integer, String> menuIdx = new HashMap<Integer, String>();

	public Map<String, Recipe> getMenu() {
		return menu;
	}

	public void setMenu(Map<String, Recipe> menu) {
		this.menu = menu;
	}

	public Map<Integer, String> getMenuIdx() {
		return menuIdx;
	}

	public void setMenuIdx(Map<Integer, String> menuIdx) {
		this.menuIdx = menuIdx;
	}

	public RecipeScript() {
		readJSONScript();
	}
    
    private Ingredient initializeIngredient(String name, int time, double amount) {
        // lower case and trim String
        name = name.toLowerCase().trim();
        if(name.equals("milk"))
            return new Milk(time, amount);
        else if(name.equals("orange"))
            return new Orange(time, amount);
        else if(name.equals("tea"))
        	return new Tea(time, amount);
        return new Cafe(time, amount);
    }
    
    @Override
	public void readJSONScript() {
		// TODO Auto-generated method stub
    	
	     JSONObject jsonObject =createDefaultValueForRecipe();
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
		     while (iterator.hasNext()){
		    	 JSONObject ing = iterator.next();
		    	 String nameIngredient = (String)ing.get("nameIngredient");
		    	 int timeToFill =Integer.parseInt(ing.get("timeToFill").toString());
		    	 double amount = Double.parseDouble(ing.get("amount").toString());
		         Ingredient ingre = initializeIngredient(nameIngredient, timeToFill, amount);
		         aRecipe.addIngredients(ingre);
		     }
		     this.menu.put(nameRecipe, aRecipe);
		             this.menuIdx.put(iter, nameRecipe);
		             iter += 1;
		 }
	}

	@Override
	public void createDefaultMenu() {
		// TODO Auto-generated method stub
		Recipe aRecipe = new Recipe("Cocktail Summer");
		Ingredient inRecipe1 = new Milk(10, 20.3);
		Ingredient inRecipie2 = new Cafe(20, 30.4);
		Ingredient inRecipe3 = new Orange(40, 10.4);
		Ingredient inRecipe4 = new Tea(30, 10.4);
		
		Map<String, Ingredient> listIngredients = new HashMap<String, Ingredient>() ;
		listIngredients.put("milk", inRecipe1);
		listIngredients.put("cafe", inRecipie2);
		listIngredients.put("orange", inRecipe3);
		listIngredients.put("tea", inRecipe4);
		aRecipe.setIngredients(listIngredients);
		
		this.menu.put("Cocktail Summer", aRecipe);
		
		Recipe bRecipe = new Recipe("Cocktail Lemon");
		Ingredient inRecipe1b = new Milk(50, 20.3);
		Ingredient inRecipie2b = new Cafe(20, 30.4);
		Ingredient inRecipe3b = new Tea(30, 10.4);
		Ingredient inRecipe4b = new Orange(40, 10.4);
		
		Map<String, Ingredient> blistIngredients = new HashMap<String, Ingredient>() ;
		blistIngredients.put("cafe", inRecipie2b);
		blistIngredients.put("milk", inRecipe1b);
		blistIngredients.put("tea", inRecipe3b);
		blistIngredients.put("orange", inRecipe4b);
		
		bRecipe.setIngredients(blistIngredients);
		this.menu.put("Cocktail Lemon", bRecipe);
	}
	
	private void readScript() {
		JSONParser parser = new JSONParser();
	     try {
	         Object obj = parser.parse(new FileReader("D:\\test.json"));
	
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
		        	 int timeToFill =Integer.parseInt(ing.get("timeToFill").toString());
		        	 double amount = Double.parseDouble(ing.get("amount").toString());
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
        //name = name.toLowerCase().trim();
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
	public Recipe generateCustomizedRecipe(String name, Map<String, Integer> ingredients) {
		Recipe newRecipe = generateRecipe(name);
		for(Map.Entry<String, Integer> ingre : ingredients.entrySet()) {
	                newRecipe.modifyIngredients(ingre.getKey().toString(), (int)ingre.getValue());
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
       // recipeName = recipeName.toLowerCase().trim();
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
      //  recipeName = recipeName.toLowerCase().trim();
        return this.menu.get(recipeName);
    }
    @Override
    public Ingredient getIngredient(String recipeName, String ingredientName) {
        // lower case and trim String
        //recipeName = recipeName.toLowerCase().trim();
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
      //  recipeName = recipeName.toLowerCase().trim();
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

    //Create JSON value
    public static JSONObject createDefaultValueForRecipe(){
    	JSONObject f = new JSONObject();
		JSONArray listRecipe = new JSONArray();
		
        JSONObject recipe = new JSONObject();
        recipe.put("name", "Cocktail Summer");

        JSONArray listIngredient = new JSONArray();
        
        JSONObject ingredient1 = new JSONObject();
        ingredient1.put("nameIngredient", "Milk");
        ingredient1.put("timeToFill", 1);
        ingredient1.put("amount", new Float(20.1));
        
        JSONObject ingredient2 = new JSONObject();
        ingredient2.put("nameIngredient", "Orange");
        ingredient2.put("timeToFill", 2);
        ingredient2.put("amount", new Float(30.1));
        
        
        JSONObject ingredient3 = new JSONObject();
        ingredient3.put("nameIngredient", "Tea");
        ingredient3.put("timeToFill", 1);
        ingredient3.put("amount", new Float(30.1));
        
        JSONObject ingredient4 = new JSONObject();
        ingredient4.put("nameIngredient", "Cafe");
        ingredient4.put("timeToFill", 2);
        ingredient4.put("amount", new Float(30.1));
//        
        listIngredient.add(ingredient1);
        listIngredient.add(ingredient2);
        listIngredient.add(ingredient3);
        listIngredient.add(ingredient4);
        
        recipe.put("ingredients", listIngredient);
        listRecipe.add(recipe);
        
        
        JSONObject recipe1 = new JSONObject();
        recipe1.put("name", "Coctail Lemon");

        JSONArray listIngredient1 = new JSONArray();
        
        JSONObject ingredient5 = new JSONObject();
        ingredient5.put("nameIngredient", "Milk");
        ingredient5.put("timeToFill", 2);
        ingredient5.put("amount", new Float(20.1));
        
        JSONObject ingredient6 = new JSONObject();
        ingredient6.put("nameIngredient", "Orange");
        ingredient6.put("timeToFill", 1);
        ingredient6.put("amount", new Float(30.1));
        
        JSONObject ingredient7 = new JSONObject();
        ingredient7.put("nameIngredient", "Cafe");
        ingredient7.put("timeToFill", 2);
        ingredient7.put("amount", new Float(20.1));
        
        JSONObject ingredient8 = new JSONObject();
        ingredient8.put("nameIngredient", "Tea");
        ingredient8.put("timeToFill", 1);
        ingredient8.put("amount", new Float(30.1));
        
        
        listIngredient1.add(ingredient5);
        listIngredient1.add(ingredient6);
        listIngredient1.add(ingredient7);
        listIngredient1.add(ingredient8);
        
        recipe1.put("ingredients", listIngredient1);
        
        
        listRecipe.add(recipe1);

        f.put("recipes", listRecipe);
    	return f;
    }
	
}
