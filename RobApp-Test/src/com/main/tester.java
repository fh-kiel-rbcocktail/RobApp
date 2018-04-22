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
            // Initialize menu
            IRecipeScript menu = new RecipeScript();
            // Prepare ordering note
            List<Recipe> orders = new ArrayList<Recipe>();

            // Display menu for ordering purpose
            System.out.println("================= Menu =================");
            for(int i = 0; i < menu.menuSize(); i += 1) {
                Recipe drinkOnMenu = menu.getNextRecipe(i);
                String recipeName = drinkOnMenu.getName();
                System.out.printf("%d.Name: %s\n",i+1,recipeName);
                System.out.println("\tIncluding: ");
                for(int j = 0; j < menu.ingredientListSize(recipeName); j+=1) {
                    Ingredient fixedIngredient = menu.getNextIngredient(recipeName, j);
                    System.out.printf("\t%d.%d. %s: %f\n",i+1,j+1,fixedIngredient.getName(),fixedIngredient.getAmount());
                }
            }
            System.out.println("========================================");

            // Order Milky Orange Juice
            Recipe cup1 = menu.generateRecipe("milky orange juice");
            // Write down ordering note
            orders.add(cup1);
            
            // Order one more Milky Orange Juice
            Recipe cup2 = menu.generateRecipe("milky cafe");
            // Write down ordering note
            orders.add(cup2);
            
            // Robot makes ordered drinks
            System.out.println();
            System.out.println("================= Robot =================");
            for(int i = 0; i < orders.size(); i += 1) {
                Recipe orderedCup = menu.getNextRecipe(i);
                System.out.printf("Cup %d: %s\n",i+1,orderedCup.getName());
                System.out.printf("\tWeigh: %f\n",menu.convertWeight(orderedCup));
                System.out.println("\tIncluding: ");
                for(int j = 0; j < menu.ingredientListSize(orderedCup); j+=1) {
                    Ingredient in = menu.getNextIngredient(orderedCup, j);
                        System.out.printf("\t%s with %f%s during %fs\n", in.getName(), in.getAmount(), in.getUnitOfVolume(), in.getTimeToFill());
                }
            }
            System.out.println("=========================================");
	}
}
