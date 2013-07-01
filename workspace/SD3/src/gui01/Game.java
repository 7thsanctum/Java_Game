package gui01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * @author  10006179
 */
public class Game implements Serializable  {

	private static Game uniqueInstance;
	
	int noEnemies = 12;	// + 1 is for the 0 starting point of an array
	int noPlanets = 5 + 1;
	int noCivilians = 6;
	
	ShipFactory sfactory = new ShipFactory(); 
	PlanetFactory pfactory = new PlanetFactory();
	
	Ship playerShip;
	ArrayList<Ship> enemyShip = new ArrayList<Ship>();
	ArrayList<Ship> civiShip = new ArrayList<Ship>();
	/**
	 * @uml.property  name="planetList"
	 * @uml.associationEnd  multiplicity="(0 -1)"
	 */
	Planet[] planetList = new Planet[noPlanets + 1];
	public int[][] civiBoard = new int[7][5];
	public int[][] shipBoard = new int[7][5];
	public int[][] enemyBoard = new int[7][5];
	public int[][] planetBoard = new int[7][5];	

	Random generator = new Random();

	private Game() {
		playerShip = sfactory.createShip("player");
		
		for(int j=0;j<5;j++)
		{
			for(int i=0;i<7;i++)
			{
				civiBoard[i][j] = 0;
				shipBoard[i][j] = 0;
				enemyBoard[i][j] = 0;
				planetBoard[i][j] = 0;				
			}
		}
		int[] pcurr = { 3, 4 };
		shipBoard[pcurr[0]][pcurr[1]] = 1;
		playerShip.setCurr(pcurr);
		for(int count=0;count<noEnemies;count++) 
		{
			int[] currPos = { (int)(Math.random() * (7)), (int)(Math.random() * (5)) } ;
			enemyBoard[currPos[0]][currPos[1]] = enemyBoard[currPos[0]][currPos[1]] + 1;
			enemyShip.add(sfactory.createShip("easy"));
			enemyShip.get(count).setCurr(currPos);
			enemyShip.get(count).setID(count);
		}
		for(int count=0;count<noCivilians;count++) 
		{
			int[] currPos = { (int)(Math.random() * (7)), (int)(Math.random() * (5)) } ;
			civiBoard[currPos[0]][currPos[1]] = 1;
			civiShip.add(sfactory.createShip("civilian"));
			civiShip.get(count).setCurr(currPos);
			civiShip.get(count).setID(count);
		}
		for(int count=0;count<noPlanets;count++) 
		{
			planetBoard[(int)(Math.random() * (7))][(int)(Math.random() * (5))] = count ;
			planetList[count] = pfactory.createPlanet();
		}
	}
	
	public void checkSpace()
	{
		for(int j=0;j<5;j++)
		{
			for(int i=0;i<7;i++)
			{				
				if(enemyBoard[i][j] > 0 && shipBoard[i][j] == 1 )
				{
					fightResult(i, j);
				}
				if (enemyBoard[i][j] == 0 && shipBoard[i][j] == 1 && planetBoard[i][j] > 0)
				{
					tradePlanet(i, j);
				}
				if (enemyBoard[i][j] == 0 && shipBoard[i][j] == 1 && civiBoard[i][j] > 0)
				{
					tradeCivilian(i, j);
				}
			}
		}
	}	
	
	void tradeCivilian(int i, int j)
	{
		int[] currPos = { i, j };
		for(Ship c : civiShip)
		{
			if(c.getCurr()[0] == currPos[0] && c.getCurr()[1] == currPos[1])
			{
				int response = JOptionPane.showConfirmDialog(null, "You are in the same quadrant as " + c.getName() + ". \nDo you want to trade with " + c.getName() + "?", "Confirm", 
		    			   JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION) 
				{
			    	int civiOxy = c.getItem("Oxymite");
			    	int civiLux = c.getItem("Luxesium");
			    	int civiSol = c.getItem("Non-conductive Solil");
			    	int civiFib = c.getItem("Fibresium");
			    	int civiMon = c.getMoney();
			    	
			    	int userOxy = playerShip.getItem("Oxymite");
			    	int userLux = playerShip.getItem("Luxesium");
			    	int userSol = playerShip.getItem("Non-conductive Solil");
			    	int userFib = playerShip.getItem("Fibresium");
			    	int userMon = playerShip.getMoney();
			    	
			    	String display = c.getName() + " has : " +
			    					 "\nOxymite : " + civiOxy +
			    					 "\nLuxesium : " + civiLux +
			    					 "\nNon-conductive Solil : " + civiSol + 
			    					 "\nFibresium : " + civiFib +
			    					 "\nMoney : " + civiMon;
			    	
			    	CiviGUI trade = new CiviGUI(c.getID());	//Pass in the planet that is being traded with
			    	trade.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			    	trade.setVisible(true);
			    	//JOptionPane.showMessageDialog(null, "Trading in process");
			    	
			    	display = display + 
			    			  "\n\nYou have : " +
			    			  "\nOxymite : " + userOxy +
					 		  "\nLuxesium : " + userLux +
					 		  "\nNon-conductive Solil : " + userSol + 
					 		  "\nFibresium : " + userFib +
					 		  "\nMoney : " + userMon;
			    	display(display);  
				}
			    else if (response == JOptionPane.NO_OPTION  || response == JOptionPane.CLOSED_OPTION) 
			    {
			    	display("Trade has been cancelled");
			    } 
			}
		}
	}
	
	void tradePlanet(int i, int j)
	{
		int planetNum = planetBoard[i][j];
		String name = planetList[planetNum].getName();
	    int response = JOptionPane.showConfirmDialog(null, "You have landed on " + name + ". \nDo you want to trade with " + name + "?", "Confirm", 
	    			   JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	    if (response == JOptionPane.YES_OPTION) 
	    {
	    	int planetOxy = planetList[planetNum].getItem("Oxymite");
	    	int planetLux = planetList[planetNum].getItem("Luxesium");
	    	int planetSol = planetList[planetNum].getItem("Non-conductive Solil");
	    	int planetFib = planetList[planetNum].getItem("Fibresium");
	    	int planetMon = planetList[planetNum].getMoney();
	    	
	    	int userOxy = playerShip.getItem("Oxymite");
	    	int userLux = playerShip.getItem("Luxesium");
	    	int userSol = playerShip.getItem("Non-conductive Solil");
	    	int userFib = playerShip.getItem("Fibresium");
	    	int userMon = playerShip.getMoney();
	    	
	    	String display = "The planet has : " +
	    					 "\nOxymite : " + planetOxy +
	    					 "\nLuxesium : " + planetLux +
	    					 "\nNon-conductive Solil : " + planetSol + 
	    					 "\nFibresium : " + planetFib +
	    					 "\nMoney : " + planetMon;
	    	
	    	TradeGUI trade = new TradeGUI(planetNum);	//Pass in the planet that is being traded with
	    	trade.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	    	trade.setVisible(true);
	    	//JOptionPane.showMessageDialog(null, "Trading in process");
	    	
	    	display = display + 
	    			  "\n\nYou have : " +
	    			  "\nOxymite : " + userOxy +
			 		  "\nLuxesium : " + userLux +
			 		  "\nNon-conductive Solil : " + userSol + 
			 		  "\nFibresium : " + userFib +
			 		  "\nMoney : " + userMon;
	    	display(display);  	
	    	
	    }
	    else if (response == JOptionPane.NO_OPTION  || response == JOptionPane.CLOSED_OPTION) 
	    {
	    	display("Trade has been cancelled");
	    } 
	}

	void fightResult(int i, int j)
	{
		int alliedStrength = 0;
		int enemyStrength = 0;
		int enemyCount = 0;
		int kills = 1;
		String display = "";
		boolean alliedReinforcements = false;
		ArrayList<Ship> ships = new ArrayList<Ship>();
		
		for(Ship b : enemyShip)
		{
			if(i == b.getCurr()[0] && j ==b.getCurr()[1])
			{
				enemyCount += 1;
				enemyStrength = enemyStrength + b.getHealth();
				ships.add(b);
			}
		}
		
		if(planetBoard[i][j] > 0) alliedReinforcements = true;
		

		display = "You are on the same spot as " + enemyCount + " enemies!!" +
				  "\nYour Health : " + playerShip.getHealth();				  
		
		for(Ship b : ships)
		{
			display = display + 					
					  "\n\nShip Name : " + b.getName() + ", Enemy Health : " + b.getHealth();
		}
		
		kills = enemyCount;
		
		if(alliedReinforcements)
		{
			alliedStrength = planetList[planetBoard[i][j]].getCombatValue();
			String planetName = planetList[planetBoard[i][j]].getName();
			display = display + "\n\n" + planetName + " sends reinforcements to help you!"  +
								"\n"+ planetName + "'s Reinforcement Strength : " + alliedStrength;
		}
		
		if(enemyStrength <= playerShip.getHealth() && !alliedReinforcements)
		{
			display = display + "\n\nYou have overpowered the enemy!";
			enemyBoard[i][j] = -1;
			playerShip.addKill(kills);
			for(Ship b : ships)
			{
				enemyShip.remove(b);
			}
		}					
		else if(enemyStrength > playerShip.getHealth() && !alliedReinforcements)
		{
			display = display + "\n\nYour foe outguns you. You have been killed ...";
			shipBoard[i][j] = 2;
		}
		else if(enemyStrength <= playerShip.getHealth() + alliedStrength)
		{
			display = display + "\n\nThe planets ships come to your aid and save you! Slaying the enemy...";
			enemyBoard[i][j] = 0;
			playerShip.addKill(kills);
			for(Ship b : ships)
			{
				playerShip.addItem("oxymite", b.getItem("oxymite"));
				playerShip.addItem("luxesium", b.getItem("luxesium"));
				playerShip.addItem("solil", b.getItem("solil"));
				playerShip.addItem("fibresium", b.getItem("fibresium"));
				enemyShip.remove(b);
			}
		}
		else if(enemyStrength > playerShip.getHealth() + alliedStrength)
		{
			display = display + "\n\nThe enemy overwhelm you and the planet you took refuge in, leaving nothing behind ...";
			shipBoard[i][j] = 2;
			planetBoard[i][j] = -1;
		}
		
		JOptionPane.showMessageDialog(null, display);
	}
	
	public void moveShips()
	{
		Mover mover = new Mover();
		for(Ship e : enemyShip)
		{
			mover.addShip(e);
		}
		for(Ship c : civiShip)
		{
			mover.addShip(c);
		}
		mover.moveShips();

	}
	
	public void undoTurn()
	{
		Mover mover = new Mover();
			 
		mover.addShip(playerShip);			 
		for(Ship e : enemyShip)
			mover.addShip(e);
		for(Ship c : civiShip)
			mover.addShip(c);
			 
		for(int j=0;j<5;j++)
		{
			for(int i=0;i<7;i++)
			{
				civiBoard[i][j] = 0;
				shipBoard[i][j] = 0;
				enemyBoard[i][j] = 0;		
			 }
		}
		 mover.undoTurn();
	}
	
	public int[] prevGrid(int prevX, int prevY, String type)
	{
		int[] prev = { prevX, prevY };
		
		if(type.equalsIgnoreCase("player"))
		{
			shipBoard[prevX][prevY] = 1;
		}
		else if(type.equalsIgnoreCase("enemy"))
		{
			enemyBoard[prevX][prevY] = enemyBoard[prevX][prevY] + 1;
		}
		else if(type.equalsIgnoreCase("civilian"))
		{
			civiBoard[prevX][prevY] = civiBoard[prevX][prevY] + 1;
		}
		
		return prev;
	}
	
	public int[] newPosition(int i, int j, String type)
	{
		Random randX = new Random();
		Random randY = new Random(System.currentTimeMillis()+1);
		
		int posX = randX.nextInt(2);
		int posY = randY.nextInt(2);
		
		if(randX.nextBoolean()) posX = posX * -1; 
		if(randY.nextBoolean()) posY = posY * -1; 
		
		do{
		if(i+posX < 0 || i+posX >= 7) posX = posX * -1;
		if(j+posY < 0 || j+posY >= 5) posY = posY * -1;
		}while((i+posX < 0 || i+posX >= 7 || j+posY < 0 || j+posY >= 5) );
		
		if(type.equalsIgnoreCase("enemy"))
		{
			enemyBoard[i+posX][j+posY] = enemyBoard[i+posX][j+posY] + 1;		
			enemyBoard[i][j] = enemyBoard[i][j] - 1;
		}
		if(type.equalsIgnoreCase("civilian"))
		{
			civiBoard[i+posX][j+posY] = civiBoard[i+posX][j+posY] + 1;		
			civiBoard[i][j] = civiBoard[i][j] - 1;
		}
		
		int[] newPos = { i+posX, j+posY };
		
		return newPos;
	}
	
	public void move(int posX, int posY)
	{		
		int shipCurrX = 0;
		int shipCurrY = 0;

		shipCurrX = playerShip.getCurr()[0];
		shipCurrY = playerShip.getCurr()[1];

		if((posX == shipCurrX+1 || posX == shipCurrX-1 || posX == shipCurrX)&&(posY == shipCurrY+1 || posY == shipCurrY-1 || posY == shipCurrY) )
		{
			//Clear the ship board
			for(int j=0;j<5;j++) for(int i=0;i<7;i++) if(shipBoard[i][j] == 1)  shipBoard[i][j] = 0; 		
			shipBoard[posX][posY] = 1; // Set the ships new position
			
			for(Ship b : enemyShip) b.setPMoved(true);
			for(Ship c : civiShip) c.setPMoved(true);
			int[] currPos = { posX, posY };
			playerShip.setPrev(playerShip.getCurr());
			playerShip.setCurr(currPos);
 		}
		else display("THE WARPCORE IS UNSTABLE! YOU CAN'T MOVE THAT FAR THIS TURN!");
		checkSpace();
	}	
	
	public static Game getInstance() 
	{
		if(uniqueInstance == null) 
		{
			uniqueInstance = new Game();
		}
		return uniqueInstance;
	}
	
	public static Game newGame() 
	{
		uniqueInstance = new Game();		
		return uniqueInstance;
	}
	
	public void serializeObject() throws IOException {
		FileOutputStream fileOut = new FileOutputStream("game.data");
		ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
		objOut.writeObject(this);
	}
	
	public static Game deserizaliseObject() throws IOException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream("game.data");
		ObjectInputStream objIn = new ObjectInputStream(fileIn);
		Object obj = objIn.readObject();
		
		if (obj instanceof Game)
		{
			System.out.println("GAME INSTANCE FOUND");
			uniqueInstance = (Game)obj;
		}
		return uniqueInstance;
	}
	
	
	void display(String display)
	{
		JOptionPane.showMessageDialog(null, display);
	}
}
