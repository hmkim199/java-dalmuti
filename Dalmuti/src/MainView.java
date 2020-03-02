import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainView extends JFrame {
	PlayerPanel[] playerPanels;
	
	public MainView() {
		super("Dalmuti");

		JLabel infoLabel = new JLabel("정보        ...");
		infoLabel.setPreferredSize(new Dimension(200, 500));		
		
		JPanel infoPanel = new JPanel();
//		infoPanel.setSize(200,900);
		infoPanel.setLayout(new BorderLayout());
		infoPanel.add(infoLabel);
		infoPanel.setBackground(Color.white);
		
		JButton confirmBtn = new JButton("확인");
		confirmBtn.setVisible(true);
		confirmBtn.setPreferredSize(new Dimension(60, 126));
		infoPanel.add(confirmBtn, BorderLayout.SOUTH);

		playerPanels = new PlayerPanel[4];
		playerPanels[0] = new PlayerPanel(BorderLayout.EAST);
		playerPanels[1] = new PlayerPanel(BorderLayout.WEST);
		playerPanels[2] = new PlayerPanel(BorderLayout.SOUTH);
		playerPanels[3] = new PlayerPanel(BorderLayout.NORTH);

		BoardPanel boardPanel = new BoardPanel();

		JPanel mainPanel = new JPanel();
		mainPanel.setSize(1000, 900);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(playerPanels[0], BorderLayout.EAST);
		mainPanel.add(playerPanels[1], BorderLayout.WEST);
		mainPanel.add(playerPanels[2], BorderLayout.SOUTH);
		mainPanel.add(playerPanels[3], BorderLayout.NORTH);
		mainPanel.add(boardPanel, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(infoPanel, BorderLayout.EAST);

		this.setPreferredSize(new Dimension(1200, 900));
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainView mainview = new MainView();

	}

	void updateView(int[] ranks, String[] names) {
		// TODO Auto-generated method stub
		for (int i = 0; i < ranks.length; i++) {
			playerPanels[i].rankLabel.setText("" + ranks[i]);
			playerPanels[i].nameLabel.setText("" + names[i]);
		}
		
	}
}
