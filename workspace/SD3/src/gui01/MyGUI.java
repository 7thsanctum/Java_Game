package gui01;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


/**
 * @author  10006179
 */
public class MyGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton newGame = null;
	
	public static JButton[][] JBList = new JButton[7][5];  //  @jve:decl-index=0:
	private ArrayList<JPanel> JPList = new ArrayList<JPanel>(); // Button Array	
	private ArrayList<Image> imageList = new ArrayList<Image>();// Image Array  //  @jve:decl-index=0:
	
	boolean imageDisplayed = false;
	boolean playerAlive = true;
	private JScrollPane Inventory_1 = null;
	private JLabel jLabel = null;
	private JPanel testGrid = null;
	/**
	 * This method initializes testButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	
	ImageIcon brick = new ImageIcon("brick.jpg");  //  @jve:decl-index=0:
	private JButton loadGame = null;
	private JButton saveGame = null;
	private JButton jButton = null;
	/**
	 * @return  the newGame
	 * @uml.property  name="newGame"
	 */
	private JButton getNewGame() {
		if (newGame == null) {
			newGame = new JButton();
			newGame.setBounds(new Rectangle(15, 405, 166, 91));
			newGame.setText("New Game");
			newGame.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					int response = JOptionPane.showConfirmDialog(null, "Are you sure you wish to start a new game? All current progress will be lost!", "Confirm", 
				  			   JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (response == JOptionPane.YES_OPTION) 
					{
						Game.newGame();
					}
				}
			
			});
		}
		return newGame;
	}

	/**
	 * This method initializes Inventory_1	
	 * @return  javax.swing.JScrollPane
	 * @uml.property  name="inventory_1"
	 */	
	private JScrollPane getInventory_1() {
		if (Inventory_1 == null) {
			jLabel = new JLabel();
			jLabel.setText("JLabel");
			Inventory_1 = new JScrollPane();
			Inventory_1.setBounds(new Rectangle(585, 15, 203, 580));
			Inventory_1.setFont(new Font("Dialog", Font.PLAIN, 12));
			Inventory_1.setEnabled(false);
			Inventory_1.setViewportView(jLabel);
			Inventory_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		}
		return Inventory_1;
	}

	/**
	 * This method initializes testGrid	
	 * @return  javax.swing.JPanel
	 * @uml.property  name="testGrid"
	 */
	private JPanel getTestGrid() {
		if (testGrid == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(5);
			gridLayout.setHgap(5);
			gridLayout.setVgap(5);
			gridLayout.setColumns(7);
			testGrid = new JPanel();
			testGrid.setLayout(gridLayout);
			testGrid.setBounds(new Rectangle(15, 15, 562, 375));
		}
		return testGrid;
	}

	/**
	 * This method initializes loadGame	
	 * @return  javax.swing.JButton
	 * @uml.property  name="loadGame"
	 */
	private JButton getLoadGame() {
		if (loadGame == null) {
			loadGame = new JButton();
			loadGame.setBounds(new Rectangle(210, 405, 166, 91));
			loadGame.setText("Load Game");
			loadGame.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int response = JOptionPane.showConfirmDialog(null, "Are you sure you wish to load a previous game? All current progress will be lost!", "Confirm", 
				  			   JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (response == JOptionPane.YES_OPTION) 
					{
						try {
							Game.deserizaliseObject();
						} catch (ClassNotFoundException e1) {
							JOptionPane.showMessageDialog(null, "Invalid save game file found");
							e1.printStackTrace();
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "No save game file found!");
							e1.printStackTrace();
						}
					}
				}
			});
		}
		return loadGame;
	}

	/**
	 * This method initializes saveGame	
	 * @return  javax.swing.JButton
	 * @uml.property  name="saveGame"
	 */
	private JButton getSaveGame() {
		if (saveGame == null) {
			saveGame = new JButton();
			saveGame.setBounds(new Rectangle(405, 405, 166, 91));
			saveGame.setText("Save Game");
			saveGame.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Game.getInstance().serializeObject();
						JOptionPane.showMessageDialog(null, "Game successfully saved!");
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Save failed.");
						e1.printStackTrace();
					}
				}
			});
		}
		return saveGame;
	}

	/**
	 * This method initializes jButton	
	 * @return  javax.swing.JButton
	 * @uml.property  name="jButton"
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(210, 510, 166, 91));
			jButton.setText("Undo Turn");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int response = JOptionPane.showConfirmDialog(null, "Are you sure you wish to undo your turn?", "Confirm", 
				  			   JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (response == JOptionPane.YES_OPTION) 
					{
						Game g1 = Game.getInstance();
						g1.undoTurn();
					}
				}
			});
		}
		return jButton;
	}

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {		
		
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MyGUI thisClass = new MyGUI();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);				
			}
		});		
	}
	
	private void updateGrid()
	{
		Game g1 = Game.getInstance();  //  @jve:decl-index=0:
		Ship player = g1.playerShip;
		int[][] planetBoard = g1.planetBoard;
		int[][] shipBoard = g1.shipBoard;
		int[][] enemyBoard = g1.enemyBoard;
		int[][] civiBoard = g1.civiBoard;
		g1.moveShips();
		
		String text = "";
		JTextArea textArea = new JTextArea();
		//playerAlive = false;
		for(int j=0;j<5;j++)
		{
			for(int i=0;i<7;i++)
			{
				if(shipBoard[i][j] == 1) playerAlive = true;
				text = text + "\nAt ("+j+","+i+") there is ";
				JButton buttonX = JBList[i][j];
				
				buttonX.setIcon(new ImageIcon("starfield.jpg")); 
				
				if(planetBoard[i][j] > 0) 
				{
					buttonX.setIcon(new ImageIcon("planet.jpg"));
					text = text + "a planet, ";
				}
				if(civiBoard[i][j] > 0)
				{
					buttonX.setIcon(new ImageIcon("civi.jpg"));
					text = text + "a civilian, ";
				}
				if(enemyBoard[i][j] > 0)
				{
					buttonX.setIcon(new ImageIcon("enemy.jpg"));
					text = text + "an enemy, ";
				}
				if(enemyBoard[i][j] == -1)
				{
					buttonX.setIcon(new ImageIcon("destroyedspaceship.jpg"));
					text = text + "a destroyed enemy, ";
				}
				if(shipBoard[i][j] == 2)	
				{
					buttonX.setIcon(new ImageIcon("destroyedspaceship.jpg"));
					text = text + "the dead player, ";
				}
				if(planetBoard[i][j] == -1) 
				{
					buttonX.setIcon(new ImageIcon("destroyedplanet.jpg"));
					text = text + "a destroyed planet, ";
				}
				if(enemyBoard[i][j] > 1)	
				{
					buttonX.setIcon(new ImageIcon("reinforcement.jpg"));
					text = text + "an enemy reinforcement, ";
				}
				if(shipBoard[i][j] == 1) 
				{ 
					buttonX.setIcon(new ImageIcon("spaceship.jpg"));
					text = text + "the player, ";
				}
			    
			}		
		}
		if(playerAlive == false) 
		{
			JOptionPane.showMessageDialog(null, "You are dead, You scored : " + calculateScore());		
			try {
				Thread.sleep(100000L);
			} catch (InterruptedException e) { }
			
		}
		textArea.setText(text);
	    Inventory_1.setViewportView(textArea);
	}
	
	public int calculateScore()
	{
		Game g1 = Game.getInstance();  //  @jve:decl-index=0:
		Ship player = g1.playerShip;		
		int score = 0;
		score = score + player.getMoney();
		score = score + (int)(player.getTech() * 0.5);
		score = score + player.getKills() * 2;
		return score;
	}
	
	public void updateScreen()
	{
		Runnable r1 = new Runnable()
		{
			public void run() 
			{		
				boolean alive = true;
				while(alive == true)
				{		
					try
					{ 
						updateGrid();  //  @jve:decl-index=0:
					} catch(NullPointerException e)
					{
						e.printStackTrace();
						System.out.println("Failed to run thread");
					}
					try {
						Thread.sleep(500L);
					} catch (InterruptedException e) {
						e.printStackTrace();
						System.out.println("Failed to run thread");
					}
				}
			}
		};
		
		Thread thr1 = new Thread(r1);
		 thr1.start();
	}

	/**
	 * This is the default constructor
	 */
	public MyGUI() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */

	
	private void initialize() {
		this.setSize(816, 652);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
		this.addWindowListener(new java.awt.event.WindowAdapter() {   
			public void windowClosing(java.awt.event.WindowEvent e) {    

			}   
		   
		
			public void windowOpened(java.awt.event.WindowEvent e) {
				// Load Images
				imageList.add(Toolkit.getDefaultToolkit().getImage("brick.jpg"));
				imageList.add(Toolkit.getDefaultToolkit().getImage("star.jpg"));
				// ###########	
				String text = "";
				for(int j=0;j<5;j++)
				{
					for(int i=0;i<7;i++)
					{					
						JButton newButton = new JButton(""+j+i);
						JBList[i][j]= newButton;
						JBList[i][j].addMouseListener(new ButtonListener(i, j));
						testGrid.add(JBList[i][j]);
						text = text + "\n("+j+","+i+"),";
					}
				}
				 
				testGrid.revalidate();			
				// create a jtextarea
				
			    JTextArea textArea = new JTextArea();
			    // add text to it; we want to make it scroll
			    textArea.setText(text);
			    Inventory_1.setViewportView(textArea);
			    
				imageDisplayed = true;
				//JPList.add(panel_1);			
				for(JPanel jp : JPList){
					jp.repaint();
				}
			}
		});
	}

	/**
	 * This method initializes jContentPane
	 * @return  javax.swing.JPanel
	 * @uml.property  name="jContentPane"
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getNewGame(), null);
			jContentPane.add(getInventory_1(), null);
			jContentPane.add(getTestGrid(), null);
			jContentPane.add(getLoadGame(), null);
			jContentPane.add(getSaveGame(), null);
			jContentPane.add(getJButton(), null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
