package main;

import javax.swing.JFrame;

public class main {
	public static void main(String[]args) {
		JFrame window = new JFrame("Hasan Chess");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		
		// Adds GamePanel to the window
		GamePanel gp = new GamePanel();
		window.add(gp);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gp.launchGame();
	}
}
