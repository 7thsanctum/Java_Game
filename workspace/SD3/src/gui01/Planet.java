package gui01;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author  10006179
 */
public class Planet implements Serializable {
	private int combatValue;
	private int money;
	private int tech;
	private String name;
	private ArrayList<Items> inventory =  new ArrayList<Items>();
	
	public Planet()
	{
		NameFactory nfactory = new NameFactory();
		this.name = (nfactory.createName("planet"));		
				
		ItemFactory factory = new ItemFactory();
		this.inventory.add(factory.createItem("oxymite", 0));
		this.inventory.add(factory.createItem("luxesium", 0));
		this.inventory.add(factory.createItem("solil", 0));
		this.inventory.add(factory.createItem("fibresium", 0));
	}
	
	public int getItem(String name) 
	{ 
		int returnVal = 0;
		for(Items b : inventory)
		{
			if(b.getName().equalsIgnoreCase(name))
			{ 
				returnVal = b.getAmount();
			}
		}
		return returnVal;
	}
	public void addItem(String name, int addAmount)
	{
		for(Items b : inventory)
		{
			if(b.getName().equalsIgnoreCase(name))
			{ 
				b.setAmount(b.getAmount() + addAmount); 
			}
		}
	}
	
	/**
	 * @return  the inventory
	 * @uml.property  name="inventory"
	 */
	public  ArrayList<Items> getInventory()
	{
		return inventory;
	}
	
	public void removeItem(String name, int addAmount)
	{
		for(Items b : inventory)
		{
			if(b.getName().equalsIgnoreCase(name))
			{ 
				b.setAmount(b.getAmount() - addAmount); 
			}
		}
	}
	
	/**
	 * @return  the combatValue
	 * @uml.property  name="combatValue"
	 */
	public int getCombatValue() { return combatValue; }
	/**
	 * @param combatValue  the combatValue to set
	 * @uml.property  name="combatValue"
	 */
	public void setCombatValue(int value) { this.combatValue = value; }
	
	/**
	 * @return  the money
	 * @uml.property  name="money"
	 */
	public int getMoney() { return money; }
	/**
	 * @param money  the money to set
	 * @uml.property  name="money"
	 */
	public void setMoney(int money) { this.money = money; }
	
	/**
	 * @return  the tech
	 * @uml.property  name="tech"
	 */
	public int getTech() { return tech; }
	/**
	 * @param tech  the tech to set
	 * @uml.property  name="tech"
	 */
	public void setTech(int value) { this.tech = value; }
	
	/**
	 * @return  the name
	 * @uml.property  name="name"
	 */
	public String getName() { return name; }
}
