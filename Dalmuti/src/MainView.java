import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

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
		JLabel cardLabel = new JLabel();
		
		ImageIcon cardImage = new ImageIcon();
		
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new GridLayout(2,1));
		playerPanel.setBackground(Color.lightGray);
		playerPanel.add(rankLabel);
		playerPanel.add(nameLabel);
		
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
