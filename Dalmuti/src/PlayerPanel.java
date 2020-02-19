import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerPanel extends JPanel {
	private JLabel rankLabel;
	private JLabel nameLabel;
	private JLabel[] cardLabels;
	private JPanel cardPanel;
	private JPanel playerInfoPanel;
	
	public PlayerPanel() {
		super();
		rankLabel = new JLabel("∑©≈©");
		rankLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel = new JLabel("¿Ã∏ß");
		nameLabel.setHorizontalAlignment(JLabel.CENTER);



		cardLabels = new JLabel[15];
		for (int i = 0; i < 15; i++) {
			cardLabels[i] = new JLabel();
//			cardLabels[i].setSize(60,80);
			cardLabels[i].setPreferredSize(new Dimension(60, 80));

			try {
				String imagePath = "res/1.png";
				Image image = ImageIO.read(new File(imagePath));
				Image scaledImage = image.getScaledInstance(60, 80, 0);
				ImageIcon imageIcon = new ImageIcon(scaledImage);
				cardLabels[i].setIcon(imageIcon);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		cardPanel = new JPanel();
		for (int i = 0; i < 15; i++) {
			cardPanel.add(cardLabels[i]);
		}

		cardPanel.setLayout(new FlowLayout());
		cardPanel.setBackground(Color.pink);
		cardPanel.setOpaque(true);

		playerInfoPanel = new JPanel();
		playerInfoPanel.setLayout(new GridLayout(2, 1));
		playerInfoPanel.setBackground(Color.lightGray);
		playerInfoPanel.add(rankLabel);
		playerInfoPanel.add(nameLabel);

		this.setLayout(new BorderLayout());
		this.add(cardPanel, BorderLayout.CENTER);
		this.add(playerInfoPanel, BorderLayout.SOUTH);

	}
	
	
}
