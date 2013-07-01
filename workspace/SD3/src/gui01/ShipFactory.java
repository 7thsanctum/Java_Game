package gui01;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class ShipFactory implements Serializable  {
	
	public ShipFactory() {};
	
	public Ship createShip(String shiptype)
	{
		Ship ship = null;
		
		if (shiptype.equalsIgnoreCase("player")) {
			ship = new Player();
			System.out.println("Ship has been set and is ready to sail!");
		} 
		else if (shiptype.equalsIgnoreCase("easy")) {
			ship = new Enemy(1);
		} 
		else if(shiptype.equalsIgnoreCase("medium")) {
			ship = new Enemy(2);
		}
		else if(shiptype.equalsIgnoreCase("strong")) {
			ship = new Enemy(3);
		}
		else if(shiptype.equalsIgnoreCase("civilian")) {
			ship = new Civilian();
			System.out.println("Civilians have been spotted!");
		}
		if( ship == null)
		{
			System.out.println(shiptype + " didn't get made properly");
		}
		return ship;
	}
}
