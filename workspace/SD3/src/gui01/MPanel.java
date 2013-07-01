package gui01;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class MPanel extends JPanel {

	public MPanel() {		
	}
		
	//public void paintComponent(Graphics g, Image image){
	//	g.drawImage(image, 0, 0, this);		
	//}

	public MPanel(LayoutManager layout) {
		super(layout);
	}
	
	public void mouseClicked( String text){
		System.out.println(text);
	}

	public MPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

}
