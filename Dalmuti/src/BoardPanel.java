import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	private Image imgBoard;
	private Graphics boardGraphics;
	
	public BoardPanel(){
		super();
		this.setBackground(new Color(255, 235, 96));
//		this.setPreferredSize(new Dimension(800, 700));
	}
	
	private void drawCard(int i, int j) {
		Image cardImage = null; 
		try {
			String imagePath = "res/card3.png";
			Image image = ImageIO.read(new File(imagePath));
			cardImage = image.getScaledInstance(60, 80, 0);
			boardGraphics.drawImage(cardImage, j * 80, i * 60, this);	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		if (cardImage != null) {
//			
//		}
		
	}	
	public void paint(Graphics g) {
		for (int i = 0; i < 3; i++) {
			for (int j =0; j < 3; j++) {
				drawCard(i, j);
			}
		}
		System.out.println("paint method called..");
	}
}
