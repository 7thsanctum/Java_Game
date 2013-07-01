package gui01;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author  10006179
 */
public class ButtonListener extends MouseAdapter implements MouseListener {
	int xPos;
	int yPos;
	Game g1;
	public ButtonListener(int x, int y)
	{
		xPos = x;
		yPos = y;
	}
	
	public void mouseClicked(MouseEvent me)
	{		
		g1 = Game.getInstance();
		JButton buttonX = (JButton)me.getSource();		
		buttonX.setIcon(new ImageIcon("warpdestination.jpg"));		
		
		g1.move(xPos, yPos);	
		//g1.moveEnemy();
	}
}
