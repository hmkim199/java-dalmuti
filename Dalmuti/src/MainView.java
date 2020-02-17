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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainView extends JFrame {
	public MainView(){
		super("Dalmuti");

		JLabel infoLabel = new JLabel("정보        ...");
		infoLabel.setPreferredSize(new Dimension(200,500));
		
		JPanel infoPanel = new JPanel();
//		infoPanel.setSize(200,900);
		infoPanel.add(infoLabel);
		infoPanel.setBackground(Color.white);
		
		JLabel rankLabel = new JLabel("랭크");
		JLabel nameLabel = new JLabel("이름");
		
		ImageIcon cardImage = new ImageIcon("res/card1.png");
		
		JLabel[] cardLabels = new JLabel[15];
		for (int i = 0; i < 15; i++) {
			JLabel cardLabel = cardLabels[i];
			cardLabel = new JLabel("이미");
//			cardLabel.setSize(60,80);
			cardLabel.setPreferredSize(new Dimension(60,80));
			
			
			try {
				String imagePath = "res/1.png";
				Image image = ImageIO.read(new File(imagePath));
				Image scaledImage = image.getScaledInstance(60, 80, 0);
				ImageIcon imageIcon = new ImageIcon(scaledImage);
				cardLabel.setIcon(imageIcon);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		JPanel cardPanel = new JPanel();
		for (int i = 0; i < 15; i++) {
			cardPanel.add(cardLabels[i]);
		}
		
		
		cardPanel.setLayout(new GridLayout(1,15));		
//		cardPanel.setPreferredSize(new Dimension(800,300));
		cardPanel.setBackground(Color.pink);
		cardPanel.setOpaque(true);
		
		
		JPanel playerInfoPanel = new JPanel();
		playerInfoPanel.setLayout(new GridLayout(2,1));
		playerInfoPanel.setBackground(Color.lightGray);
		playerInfoPanel.add(rankLabel);
		playerInfoPanel.add(nameLabel);
		
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new BorderLayout());
		playerPanel.add(cardPanel, BorderLayout.CENTER);
		playerPanel.add(playerInfoPanel, BorderLayout.SOUTH);
		
		JPanel boardPanel = new JPanel();
		boardPanel.setBackground(new Color(255,235,96));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setSize(1000,900);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(playerPanel, BorderLayout.SOUTH);
		mainPanel.add(boardPanel, BorderLayout.CENTER);
		
		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(infoPanel, BorderLayout.EAST);
		
		
		this.setPreferredSize(new Dimension(1200,900));
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainView mainview = new MainView();

	}
}
