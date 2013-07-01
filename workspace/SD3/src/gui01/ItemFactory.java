package gui01;

import java.io.Serializable;

public class ItemFactory implements Serializable {
	public ItemFactory(){}
	
	public Items createItem(String itemToCreate, int amount)
	{
		Items item = null;
		
		//Alloyed Hermirus			To be created
		//Botanosite
		//Esperus
		//Flammable Rubideonium
		//Gaseous Auradium
		//Mesil
		//Nitriril
		
		if (itemToCreate.equalsIgnoreCase("oxymite"))  		item = new Oxymite(amount); 
		else if(itemToCreate.equalsIgnoreCase("luxesium")) 	item = new Luxesium(amount); 
		else if(itemToCreate.equalsIgnoreCase("solil")) 	item = new Solil(amount); 
		else if(itemToCreate.equalsIgnoreCase("fibresium")) item = new Fibresium(amount); 
		if( item == null)
		{
			System.out.println(itemToCreate + " didn't get made properly");
		}
		
		return item;
	}

}
