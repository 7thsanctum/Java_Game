package gui01;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author  10006179
 */
public class Ship implements Serializable{
	private int health;
	private String shiptype;
	private String name;
	private boolean playerMoved;
	private int money;
	private int tech;
	private int shipID;
	private int[] currPos;
	private int[] prevPos;
	
	private ArrayList<Items> inventory =  new ArrayList<Items>();
	private int kills;
	
	public Ship(int startingInventory)
	{
		ItemFactory factory = new ItemFactory();
		this.inventory.add(factory.createItem("oxymite", startingInventory));
		this.inventory.add(factory.createItem("luxesium", startingInventory));
		this.inventory.add(factory.createItem("solil", startingInventory));
		this.inventory.add(factory.createItem("fibresium", startingInventory));
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
	
	/**
	 * @return  the inventory
	 * @uml.property  name="inventory"
	 */
	public ArrayList<Items> getInventory() { return inventory; }
	
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
	
	public int getID() { return shipID; }
	public void setID(int idnum) { this.shipID = idnum; }
	
	public String getType() { return shiptype; }
	public void setType(String shiptype) { this.shiptype = shiptype; }
	
	/**
	 * @return  the health
	 * @uml.property  name="health"
	 */
	public int getHealth() { return health; }
	/**
	 * @param health  the health to set
	 * @uml.property  name="health"
	 */
	public void setHealth(int health) { this.health = health; }
	
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
	
	public void addKill(int killNo) { this.kills = this.kills + killNo; }
	/**
	 * @return  the kills
	 * @uml.property  name="kills"
	 */
	public int getKills() { return kills; }
	
	public void setCurr(int[] currPos) { this.currPos= currPos; }
	public int[] getCurr() { return currPos; }
	
	public void setPrev(int[] prevPos) { this.prevPos = prevPos; }
	public int[] getPrev() { return prevPos; }
	
	public void setPMoved(boolean moved) { this.playerMoved = moved; }
	public boolean getPMoved() { return playerMoved; }
	
	/**
	 * @param name  the name to set
	 * @uml.property  name="name"
	 */
	public void setName(String name) { this.name = name; }
	/**
	 * @return  the name
	 * @uml.property  name="name"
	 */
	public String getName() { return name; }
	
	public void moveShip(){
		//if(this.getPMoved() == true)
		//{
			Game g1 = Game.getInstance();
			this.setPrev(this.getCurr());
			this.setCurr(g1.newPosition(this.getCurr()[0], this.getCurr()[1], this.getType()));	
			this.setPMoved(false);
		//}
	}
	
	public void undoTurn(){
		Game g1 = Game.getInstance();
		int[] placeholderArr = this.getCurr();
		this.setCurr(g1.prevGrid(this.getPrev()[0], this.getPrev()[1], this.getType()));	
		this.setPrev(placeholderArr);
	}
	
}
