package gui01;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * @author  10006179
 */
public class TradeGUI extends JFrame {
	DefaultListModel PlayerInventory = new DefaultListModel();
	DefaultListModel PlanetInventory = new DefaultListModel();
	Game g1 = Game.getInstance();  //  @jve:decl-index=0:
	int planetNum;
	int selectedPlanetIndex;
	int selectedPlayerIndex;
	
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel11 = null;
	private JList jList = null;
	private JList jList1 = null;
	private JLabel playerLabel = null;
	private JLabel planetLabel = null;
	private JButton jButton = null;
	private JTextField playerText = null;
	private JTextField planetText = null;
	/**
	 * This method initializes jList	
	 * @return  javax.swing.JList
	 * @uml.property  name="jList"
	 */
	private JList getJList() {
		if (jList == null) {
			jList = new JList(PlanetInventory);
			jList.setName("jList");
			jList.setBounds(new Rectangle(225, 60, 165, 203));
			jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
				public void valueChanged(javax.swing.event.ListSelectionEvent e) {
					selectedPlanetIndex =  jList.getSelectedIndex();
					if(jList.getSelectedIndex() != -1)
					{
						calculatePurchase();
						selectedItem();
						updateOfferPlay();
					}
				}
			});
		}
		return jList;
	}

	public void selectedItem()
	{
		if(selectedPlayerIndex == 0) 		playerLabel.setText("Oxymite");
		else if(selectedPlayerIndex == 1) 	playerLabel.setText("Luxesium");
		else if(selectedPlayerIndex == 2) 	playerLabel.setText("Solil");
		else if(selectedPlayerIndex == 3) 	playerLabel.setText("Fibresium");
		else if(selectedPlayerIndex == 4) 	playerLabel.setText("Money");
		
		if(selectedPlanetIndex == 0) 		planetLabel.setText("Oxymite");
		else if(selectedPlanetIndex == 1) 	planetLabel.setText("Luxesium");
		else if(selectedPlanetIndex == 2) 	planetLabel.setText("Solil");
		else if(selectedPlanetIndex == 3) 	planetLabel.setText("Fibresium");
		else if(selectedPlanetIndex == 4) 	planetLabel.setText("Money");
	}
	/**
	 * This method initializes jList1	
	 * @return  javax.swing.JList
	 * @uml.property  name="jList1"
	 */
	private JList getJList1() {
		if (jList1 == null) {
			jList1 = new JList(PlayerInventory);
			jList1.setName("jList1");
			jList1.setBounds(new Rectangle(15, 60, 166, 202));
			jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
				public void valueChanged(javax.swing.event.ListSelectionEvent e) {
					selectedPlayerIndex =  jList1.getSelectedIndex();
					if(jList1.getSelectedIndex() != -1)
					{
						calculatePurchase();
						selectedItem();
						updateOfferPlan();
					}
				}
			});
		}
		return jList1;
	}

	public void calculatePurchase()
	{		
		int amount = 0;
		int value = 0;
		int purchase_amount = 0;
		
		if(itemName(selectedPlayerIndex) != "money") 
		{
			amount = g1.playerShip.getItem(itemName(selectedPlayerIndex));	
			value = g1.playerShip.getInventory().get(selectedPlayerIndex).getValue() * g1.playerShip.getItem(itemName(selectedPlayerIndex));
			
		}
		else
		{
			amount = g1.playerShip.getMoney();
			value = amount;
		}
		
		if(value != 0)
		{
			if(itemName(selectedPlanetIndex) != "money")
				purchase_amount = value / g1.planetList[planetNum].getInventory().get(selectedPlanetIndex).getValue() ;
			else  purchase_amount = value / g1.planetList[planetNum].getMoney() ;
		}
		
		if(purchase_amount > g1.planetList[planetNum].getItem(itemName(selectedPlanetIndex)))
			purchase_amount = g1.planetList[planetNum].getItem(itemName(selectedPlanetIndex));
		
		planetText.setText("" + purchase_amount);	//Planets input box
		playerText.setText("" + amount);
	}
	
	/**
	 * This method initializes jButton	
	 * @return  javax.swing.JButton
	 * @uml.property  name="jButton"
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(135, 345, 121, 61));
			jButton.setText("Trade");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(checkInputs())
						doTransaction();
				}
			});
		}
		return jButton;
	}
	
	public void doTransaction()
	{		
		int planMulti = 1;
		int playMulti = 1;
		int planetOrigi = g1.planetList[planetNum].getMoney();
		int planetDeduc = Integer.parseInt(planetText.getText());
		int playerOrigi = g1.playerShip.getMoney();
		int playerDeduc = Integer.parseInt(playerText.getText());
		
		if(itemName(selectedPlanetIndex) != "money") 
		{
			planMulti = g1.planetList[planetNum].getInventory().get(selectedPlanetIndex).getValue();
			planetOrigi = g1.planetList[planetNum].getInventory().get(selectedPlanetIndex).getAmount();			
		}
		if(itemName(selectedPlayerIndex) != "money") 
		{ 
			playMulti = g1.playerShip.getInventory().get(selectedPlayerIndex).getValue();
			playerOrigi = g1.playerShip.getInventory().get(selectedPlayerIndex).getAmount();			
		}	
		
		int planet = planetDeduc * planMulti;
		int player = playerDeduc * playMulti;
				
		int newPlayer = (playerOrigi * playMulti - planet) / playMulti;		
		int playerCost = playerOrigi-newPlayer * playMulti;
		int newPlanet = (planetOrigi * planMulti - player) / planMulti;
		int planetCost = planetOrigi-newPlanet  * planMulti;
		if(itemName(selectedPlanetIndex) != "money") 
		{
			g1.planetList[planetNum].getInventory().get(selectedPlanetIndex).setAmount(newPlanet);
			g1.playerShip.getInventory().get(selectedPlanetIndex).setAmount(g1.playerShip.getInventory().get(selectedPlanetIndex).getAmount() + playerCost / planMulti);
		}
		else 
		{
			g1.planetList[planetNum].setMoney(newPlanet);
			g1.playerShip.setMoney(g1.playerShip.getMoney() + planetCost);
		}
		if(itemName(selectedPlayerIndex) != "money") 
		{
			g1.playerShip.getInventory().get(selectedPlayerIndex).setAmount(newPlayer);
			g1.planetList[planetNum].getInventory().get(selectedPlayerIndex).setAmount(g1.planetList[planetNum].getInventory().get(selectedPlayerIndex).getAmount() + playerCost / planMulti);
		}
		else 
		{
			g1.playerShip.setMoney(newPlayer);
			g1.planetList[planetNum].setMoney(g1.planetList[planetNum].getMoney() + playerCost);
		}		
		
		JOptionPane.showMessageDialog(null,  "Your original : " + playerOrigi + " Your new : " + newPlayer + " Final cost : " + playerCost);
		JOptionPane.showMessageDialog(null,  "Their original : " + planetOrigi + " Their new : " + newPlanet + " Final cost : " + planetCost);
		updateListBox();
	}
	
	public boolean checkInputs()
	{
		if(selectedPlayerIndex == selectedPlanetIndex)
		{
			JOptionPane.showMessageDialog(null, "You cannot trade " + itemName(selectedPlayerIndex) + " for " + itemName(selectedPlanetIndex));
			return false;
		}
		else
		{
			int response = JOptionPane.showConfirmDialog(null, "Do you wish to trade your " + itemName(selectedPlayerIndex) + " for " + itemName(selectedPlanetIndex) + " ?", "Confirm", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) 
			{
				//checkValues();

				try
				{	
					int planMulti = 1;
					int playMulti = 1;
					if(itemName(selectedPlanetIndex) != "money") planMulti = g1.planetList[planetNum].getInventory().get(selectedPlanetIndex).getValue();
					if(itemName(selectedPlayerIndex) != "money") playMulti = g1.playerShip.getInventory().get(selectedPlayerIndex).getValue();
			 
					int val = Integer.parseInt(planetText.getText()) * planMulti;
					int val2 = Integer.parseInt(playerText.getText()) * playMulti;
			
					if((val > g1.planetList[planetNum].getItem(itemName(selectedPlanetIndex)) * planMulti && itemName(selectedPlanetIndex) != "money")  || val < 0)
					{
						JOptionPane.showMessageDialog(null,  g1.planetList[planetNum].getName() + "doesn't have enough " + itemName(selectedPlanetIndex) + " to complete the transaction!");
						return false;
					}
					if((val2 > g1.playerShip.getItem(itemName(selectedPlayerIndex)) * playMulti && itemName(selectedPlayerIndex) != "money") || val2 < 0)
					{
						JOptionPane.showMessageDialog(null,  "You don't have enough " + itemName(selectedPlayerIndex) + " to complete the transaction! Not enough resource");
						return false;
					}
					if(val > g1.planetList[planetNum].getMoney() && itemName(selectedPlanetIndex) == "money")
					{
						JOptionPane.showMessageDialog(null,  g1.planetList[planetNum].getName() + "doesn't have enough " + itemName(selectedPlanetIndex) + " to complete the transaction!");
						return false;
					}
					if(val2 > g1.playerShip.getMoney()  && itemName(selectedPlayerIndex) == "money")
					{
						JOptionPane.showMessageDialog(null,  "You don't have enough " + itemName(selectedPlayerIndex) + " to complete the transaction! Not enough money");
						return false;
					}
					JOptionPane.showMessageDialog(null,  "Your offer : " + val2 + " Their offer : " + val);
				}
				catch (NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid value");
					return false;
				}
		
				return true;
			}
			return false;
		}
	}
	
	public String itemName(int index)
	{
		String name = "";
		if(index == 0) name = "oxymite";
		if(index == 1) name = "luxesium";
		if(index == 2) name = "Non-conductive Solil";
		if(index == 3) name = "fibresium";
		if(index == 4) name = "money";
		return name;
	}

	/**
	 * This method initializes playerText	
	 * @return  javax.swing.JTextField
	 * @uml.property  name="playerText"
	 */
	private JTextField getPlayerText() {
		if (playerText == null) {
			playerText = new JTextField();
			playerText.setBounds(new Rectangle(30, 300, 106, 20));
			playerText.addKeyListener(new java.awt.event.KeyAdapter() {   
				public void keyReleased(java.awt.event.KeyEvent e) {    
					updateOfferPlay();
				}
			});
		}
		return playerText;
	}

	public void updateOfferPlay()
	{
		try
		{	
			int planMulti = 1;
			int playMulti = 1;
			if(itemName(selectedPlanetIndex) != "money") planMulti = g1.planetList[planetNum].getInventory().get(selectedPlanetIndex).getValue();
			if(itemName(selectedPlayerIndex) != "money") playMulti = g1.playerShip.getInventory().get(selectedPlayerIndex).getValue();
	 
			int val = Integer.parseInt(playerText.getText()) * playMulti;
			
			int val2 = g1.planetList[planetNum].getItem(itemName(selectedPlanetIndex)) * planMulti;
			if(itemName(selectedPlanetIndex) == "money") val2 = g1.planetList[planetNum].getMoney();
			
			if(val2 > val) val2 = val;
			
			planetText.setText(val2 / planMulti + "");
		}
		catch (NumberFormatException nfe)
		{
			planetText.setText("" + g1.playerShip.getItem(itemName(selectedPlayerIndex)));
			JOptionPane.showMessageDialog(null, "Please enter a valid value");
		}

	}
	
	public void updateOfferPlan()
	{
		try
		{	
			int planMulti = 1;
			int playMulti = 1;
			if(itemName(selectedPlanetIndex) != "money") planMulti = g1.planetList[planetNum].getInventory().get(selectedPlanetIndex).getValue();
			if(itemName(selectedPlayerIndex) != "money") playMulti = g1.playerShip.getInventory().get(selectedPlayerIndex).getValue();
	 
			int val =  g1.playerShip.getItem(itemName(selectedPlayerIndex)) * playMulti;
			int val2 = Integer.parseInt(planetText.getText()) * planMulti;
			if(itemName(selectedPlayerIndex) == "money") val = g1.playerShip.getMoney();
			if(val > val2) val = val2;
			
			playerText.setText(val / playMulti + "");
		}
		catch (NumberFormatException nfe)
		{
			planetText.setText("" + g1.playerShip.getItem(itemName(selectedPlayerIndex)));
			JOptionPane.showMessageDialog(null, "Please enter a valid value");
		}

	}
	/**
	 * This method initializes planetText	
	 * @return  javax.swing.JTextField
	 * @uml.property  name="planetText"
	 */
	private JTextField getPlanetText() {
		if (planetText == null) {
			planetText = new JTextField();
			planetText.setBounds(new Rectangle(255, 300, 106, 20));
			planetText.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyReleased(java.awt.event.KeyEvent e) {
					updateOfferPlan();
				}
			});
		}
		return planetText;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TradeGUI thisClass = new TradeGUI(1);
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public TradeGUI(int planetNum) {
		super();
		initialize(planetNum);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize(int planetNum) {
		this.setSize(422, 469);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
		this.planetNum = planetNum;
		updateListBox();

	}
	
	public void updateListBox()
	{
		PlayerInventory.clear();
		jLabel11.setText(g1.planetList[planetNum].getName() + "'s \nInventory");
		PlayerInventory.addElement("Oxymite : " + g1.playerShip.getItem("oxymite"));
		PlayerInventory.addElement("Luxesium : " + g1.playerShip.getItem("Luxesium"));
		PlayerInventory.addElement("Non-conductive Solil : " + g1.playerShip.getItem("Non-conductive Solil"));
		PlayerInventory.addElement("Fibresium : " + g1.playerShip.getItem("Fibresium"));
		PlayerInventory.addElement("Money : " + g1.playerShip.getMoney());
		jList1.setSelectedIndex(0);
		PlanetInventory.clear();
		PlanetInventory.addElement("Oxymite : " + g1.planetList[planetNum].getItem("oxymite"));
		PlanetInventory.addElement("Luxesium : " + g1.planetList[planetNum].getItem("Luxesium"));
		PlanetInventory.addElement("Non-conductive Solil : " + g1.planetList[planetNum].getItem("Non-conductive Solil"));
		PlanetInventory.addElement("Fibresium : " + g1.planetList[planetNum].getItem("Fibresium"));
		PlanetInventory.addElement("Money : " + g1.planetList[planetNum].getMoney());
		jList.setSelectedIndex(0);
	}

	/**
	 * This method initializes jContentPane
	 * @return  javax.swing.JPanel
	 * @uml.property  name="jContentPane"
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			planetLabel = new JLabel();
			planetLabel.setBounds(new Rectangle(255, 270, 106, 16));
			planetLabel.setText("JLabel");
			playerLabel = new JLabel();
			playerLabel.setBounds(new Rectangle(30, 270, 106, 16));
			playerLabel.setText("JLabel");
			jLabel11 = new JLabel();
			jLabel11.setText("Inventory");
			jLabel11.setBounds(new Rectangle(225, 30, 174, 25));
			jLabel11.setName("jLabel11");
			jLabel1 = new JLabel();
			jLabel1.setText("Your Inventory");
			jLabel1.setBounds(new Rectangle(30, 30, 97, 24));
			jLabel1.setName("jLabel1");
			jLabel = new JLabel();
			jLabel.setText("Trading Screen");
			jLabel.setBounds(new Rectangle(150, 0, 107, 31));
			jLabel.setName("jLabel");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel11, null);
			jContentPane.add(getJList(), null);
			jContentPane.add(getJList1(), null);
			jContentPane.add(playerLabel, null);
			jContentPane.add(planetLabel, null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getPlayerText(), null);
			jContentPane.add(getPlanetText(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
