package gui01;

import java.io.Serializable;
import java.util.ArrayList;

public class Mover {

	private ArrayList <Ship> theShips = new ArrayList <Ship> (); 
	
	public void addShip(Ship theShip) {
		this.theShips.add(theShip);
	}
	
	public void moveShips() {
		for(Ship ship : this.theShips) {
			if(ship.getPMoved() == true)
			{
				ship.moveShip();
			}
		}
	}
	
	public void undoTurn() {
		for(Ship ship : this.theShips) {
			ship.undoTurn();
		}
	}
	
	public void clearList(){
		this.theShips.clear();
	}
}
