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

	private final int WIDTH = 22;
	private final int HEIGHT = 8;

	private Image boardImg;

	public BoardPanel() {
		super();
		this.setBackground(new Color(255, 235, 96));

		try {
			boardImg = ImageIO.read(new File("res/board.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(boardImg, 0, 0, this);

		for (int i = 2; i < 5; i++) {
			for (int j = 2; j < 5; j++) {
				Image cardImage = null;
				try {
					String imagePath = "res/card3.png";
					Image image = ImageIO.read(new File(imagePath));
					cardImage = image.getScaledInstance(120, 160, 0);
					g.drawImage(cardImage, j * 120, i * 160, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
