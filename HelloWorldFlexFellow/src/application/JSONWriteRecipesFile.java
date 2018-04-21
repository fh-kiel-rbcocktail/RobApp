package application;


import java.io.IOException;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONWriteRecipesFile {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws JSONException {

		JSONObject f = new JSONObject();
		JSONArray listRecipe = new JSONArray();
		
        JSONObject recipe = new JSONObject();
        recipe.put("name", "milky orange juice");

        JSONArray listIngredient = new JSONArray();
        
        JSONObject ingredient1 = new JSONObject();
        ingredient1.put("nameIngredient", "milk");
        ingredient1.put("timeToFill", new Float(1.4));
        ingredient1.put("amount", new Float(20.1));
        
        JSONObject ingredient2 = new JSONObject();
        ingredient2.put("nameIngredient", "orange");
        ingredient2.put("timeToFill", new Float(1.5));
        ingredient2.put("amount", new Float(30.1));
        
        listIngredient.add(ingredient1);
        listIngredient.add(ingredient2);
       
        recipe.put("ingredients", listIngredient);
        listRecipe.add(recipe);
        
        
        JSONObject recipe1 = new JSONObject();
        recipe1.put("name", "milky orange juice -- 1");

        JSONArray listIngredient1 = new JSONArray();
        
        JSONObject ingredient3 = new JSONObject();
        ingredient3.put("nameIngredient", "milk-1");
        ingredient3.put("timeToFill", new Float(1.4));
        ingredient3.put("amount", new Float(20.1));
        
        JSONObject ingredient4 = new JSONObject();
        ingredient4.put("nameIngredient", "orange-1");
        ingredient4.put("timeToFill", new Float(1.5));
        ingredient4.put("amount", new Float(30.1));
        
        listIngredient1.add(ingredient3);
        listIngredient1.add(ingredient4);
       
        recipe1.put("ingredients", listIngredient1);
        listRecipe.add(recipe1);

        f.put("recipes", listRecipe);
//        try (FileWriter file = new FileWriter("D:\\test.json")) {
//
//            file.write(f.toString());
//            file.flush();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.print(f);

    }
}
