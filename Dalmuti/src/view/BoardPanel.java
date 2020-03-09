package view;

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
	private int exCardNum = 0;
	private int exCardsCount = 0;

	public BoardPanel() {
		super();
		this.setBackground(new Color(255, 235, 96));
	}

	public void setExCards(int num, int count) {
		this.exCardNum = num;
		this.exCardsCount = count;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < exCardsCount; i++) {

			Image cardImage = null;
			try {
				String imagePath = "res/card" + exCardNum + ".png";
				Image image = ImageIO.read(new File(imagePath));
				cardImage = image.getScaledInstance(WIDTH, HEIGHT, 0);
				g.drawImage(cardImage, (int) ((3 + i * 0.5) * WIDTH), (int) (1.5 * HEIGHT), this);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
