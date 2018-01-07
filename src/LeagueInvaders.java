import java.awt.Dimension;

import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame frame;
	static final int WIDTH = 500;
	static final int HEIGHT = 800;
	GamePanel gp;

	LeagueInvaders(int WIDTH, int HEIGHT) {
		frame = new JFrame();
		gp = new GamePanel();
	}

	public static void main(String[] args) {
		LeagueInvaders li = new LeagueInvaders(WIDTH, HEIGHT);
		li.setup();
	}

	public void setup() {
		frame.add(gp);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gp.startGame();
		frame.addKeyListener(gp);
	}

}