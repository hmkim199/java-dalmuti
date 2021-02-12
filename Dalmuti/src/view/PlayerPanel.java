package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerPanel extends JPanel {
	JLabel rankLabel;
	JLabel nameLabel;
	JLabel[] cardLabels;
	private JPanel cardPanel;
	JLabel leftCardsLabel;
	HashSet<Integer> clickedLocations = new HashSet<>();

	public HashSet<Integer> getClickedLocations() {
		return clickedLocations;
	}

	public PlayerPanel(String position) {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.add(Box.createVerticalGlue());
		
		this.addMouseListener(new MyMouseListener());

		rankLabel = new JLabel("랭크");
		rankLabel.setAlignmentX(CENTER_ALIGNMENT);

		nameLabel = new JLabel("이름");
		nameLabel.setAlignmentX(CENTER_ALIGNMENT);

		if (position == BorderLayout.SOUTH) {
			cardLabels = new JLabel[17];
			for (int i = 0; i < cardLabels.length; i++) {
				cardLabels[i] = new JLabel();
				cardLabels[i].setPreferredSize(new Dimension(60, 80));
			}

			cardPanel = new JPanel();

			for (int i = 0; i < cardLabels.length; i++) {
				cardPanel.add(cardLabels[i]);
			}
			cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.X_AXIS));
			cardPanel.setBackground(Color.pink);
			cardPanel.setOpaque(true);
			cardPanel.add(Box.createHorizontalGlue());
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

	public class MyMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int exClicked = e.getX() / cardLabels[0].getWidth();
			if (clickedLocations.contains(exClicked)) {
				clickedLocations.remove(exClicked);
			}
			else {
				clickedLocations.add(exClicked);
			}
			
			System.out.println(clickedLocations);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public int askToChooseTaxCard() {
		return 0;
	}
}
