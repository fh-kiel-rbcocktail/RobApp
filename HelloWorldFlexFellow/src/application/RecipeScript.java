package application;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.*;

public class RecipeScript implements IRecipeScript {
	private Map<String, Recipe> menu = new HashMap<String, Recipe>();
	
	public RecipeScript() {
		readScript();
	}
	
	private void readScript() {
		try {
			File fXmlFile = new File("Recipe.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
					
			doc.getDocumentElement().normalize();

			NodeList recipes = doc.getElementsByTagName("recipe");
			// read recipes
			for(int iRecipe = 0; iRecipe < recipes.getLength(); iRecipe++) {
				NodeList aParentNode = (NodeList)recipes.item(iRecipe);
				// create a Recipe object
				String recipeName = ((Element)aParentNode).getAttribute("id");
				Recipe aRecipe = new Recipe(recipeName);
				// read Ingredients
				for(int iIngredient = 0; iIngredient < aParentNode.getLength(); iIngredient++) {
					Node aNode = aParentNode.item(iIngredient);
					initializeIngredient(aNode, aRecipe);
				}
				// create a Recipe list
				menu.put(recipeName, aRecipe);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initializeIngredient(Node aNode, Recipe aRecipe) {
		if (aNode.getNodeType() == Node.ELEMENT_NODE) {
			Element anIngre = (Element)aNode;
			String IngreName = anIngre.getAttribute("id");
			float time = Float.parseFloat(anIngre.getAttribute("timeToFill"));
			double amount = Double.parseDouble(anIngre.getAttribute("amount"));
			// create Ingredient object
			Ingredient ele;
			if(IngreName.equals("milk")) {
				ele = new Milk(time, amount);
			}
			else {
				ele = new Orange(time, amount);
			}
			if(anIngre.hasAttribute("unitOfVolume"))
				ele.setUnitOfVolume(anIngre.getAttribute("unitOfVolume"));
			aRecipe.addIngredients(ele);
		}
	}
	
	public Recipe getRecipe(String name) {
		// lower case and trim String
		name = name.toLowerCase().trim();
		// TODO: Should deep copy object
		return menu.get(name);
	}
	
	public Recipe customizeRecipe(String name, Map<String, Float> ingredients) {
		Recipe newRecipe = getRecipe(name);
		for(Map.Entry<String, Float> ingre : ingredients.entrySet()) {
			newRecipe.modifyIngredients(ingre.getKey().toString(), (Float)ingre.getValue());
		}
		return newRecipe;
	}
}
