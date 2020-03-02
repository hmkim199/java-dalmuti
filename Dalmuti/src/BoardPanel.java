import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	
	private final int WIDTH = 120;
	private final int HEIGHT = 160;

	public BoardPanel() {
		super();
		this.setBackground(new Color(255, 235, 96));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 1; i < 4; i++) {
			for (int j = 2; j < 5; j++) {
				Image cardImage = null;
				try {
					String imagePath = "res/card3.png";
					Image image = ImageIO.read(new File(imagePath));
					cardImage = image.getScaledInstance(WIDTH, HEIGHT, 0);
					g.drawImage(cardImage, j * WIDTH, i * HEIGHT, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
