package application;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.*;

public class RecipeScript {
	private Map<String, Recipe> menu = new HashMap<String, Recipe>();
	
	public RecipeScript() {
		readScript();
	}
	
	public void readScript() {
		try {
			File fXmlFile = new File("Recipe.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
					
			doc.getDocumentElement().normalize();

			NodeList recipes = doc.getElementsByTagName("recipe");
			// read recipe object
			for(int iRecipe = 0; iRecipe < recipes.getLength(); iRecipe++) {
				Node aNode = recipes.item(iRecipe);
				if (aNode.getNodeType() == Node.ELEMENT_NODE) {
					Element aRecipe = (Element)aNode;
					// create a Recipe object
					// aRecipe.getAttribute("id");
					// read Ingredient object
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Recipe getRecipe(String name) {
		// lower case and trim String
		name = name.toLowerCase().trim();
		// get
		return menu.get(name);
	}
}
