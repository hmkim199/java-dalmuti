import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerPanel extends JPanel {
	private JLabel rankLabel;
	private JLabel nameLabel;
	private JLabel[] cardLabels;
	private JPanel cardPanel;
	private JLabel leftCardsLabel;

	public PlayerPanel(String position) {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.add(Box.createVerticalGlue());

		rankLabel = new JLabel("랭크");
		rankLabel.setAlignmentX(CENTER_ALIGNMENT);

		nameLabel = new JLabel("이름");
		nameLabel.setAlignmentX(CENTER_ALIGNMENT);

		if (position == BorderLayout.SOUTH) {
			cardLabels = new JLabel[15];
			for (int i = 0; i < 15; i++) {
				cardLabels[i] = new JLabel();
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
			this.add(cardPanel);
		} else {
			leftCardsLabel = new JLabel("남은 카드 수 : 00");
			leftCardsLabel.setAlignmentX(CENTER_ALIGNMENT);
			this.add(leftCardsLabel);
		}

		this.add(rankLabel);
		this.add(nameLabel);
		this.add(Box.createVerticalGlue());

	}

}
