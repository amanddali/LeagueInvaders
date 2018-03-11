import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = MENU_STATE;
	Font titleFont;
	Font smallerFont;
	Rocketship rs;
	boolean moveRight;
	boolean moveLeft;
	boolean moveUp;
	boolean moveDown;
	ObjectManager om;
	public static BufferedImage alienImg;
	public static BufferedImage rocketImg;
	public static BufferedImage bulletImg;
	public static BufferedImage spaceImg;

	GamePanel() {
		timer = new Timer(1000 / 60, this);
		titleFont = new Font("Oswald", Font.BOLD, 48);
		smallerFont = new Font("Oswald", Font.PLAIN, 30);
		rs = new Rocketship(250, 700, 50, 50);
		moveRight = false;
		moveLeft = false;
		moveUp = false;
		moveDown = false;
		om = new ObjectManager(rs);
		try {
			alienImg = ImageIO.read(this.getClass().getResourceAsStream("alien.png"));
			rocketImg = ImageIO.read(this.getClass().getResourceAsStream("rocket.png"));
			bulletImg = ImageIO.read(this.getClass().getResourceAsStream("bullet.png"));
			spaceImg = ImageIO.read(this.getClass().getResourceAsStream("space.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startGame() {
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == GAME_STATE) {
			drawGameState(g);
		} else if (currentState == END_STATE) {
			drawEndState(g);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			currentState += 1;
		}
		if (currentState > END_STATE) {
			currentState = MENU_STATE;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moveRight = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moveLeft = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			moveUp = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			moveDown = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			om.addProjectile(new Projectiles(rs.x + 20, rs.y, 10, 10));
		}
		rs.update();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moveRight = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moveLeft = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			moveUp = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			moveDown = false;
		}
	}

	public void updateMenuState() {

	}

	public void updateGameState() {
		if (moveRight == true && rs.x < 440) {
			rs.x += rs.speed;
		}
		if (moveLeft == true && rs.x > 10) {
			rs.x -= rs.speed;
		}
		if (moveUp == true) {
			rs.y -= rs.speed;
		}
		if (moveDown == true) {
			rs.y += rs.speed;
		}
		om.update();
		om.manageEnemies();
		om.checkCollision();
		om.purgeObjects();
		if (rs.isAlive == false) {
			currentState = END_STATE;
			rs = new Rocketship(250, 700, 50, 50);
			om.reset();
			om.addRocketship(rs);
		}
	}

	public void updateEndState() {

	}

	public void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("LEAGUE INVADERS", 20, 200);
		g.setFont(smallerFont);
		g.drawString("press ENTER to start", 100, 400);
		g.drawString("press SPACE for instructions", 45, 600);
	}

	public void drawGameState(Graphics g) {
		g.drawImage(GamePanel.spaceImg, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		om.draw(g);
	}

	public void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER", 100, 200);
		g.setFont(smallerFont);
		g.drawString("you killed " + om.getScore() + " enemies", 100, 400);
		g.drawString("press ENTER to restart", 90, 600);

	}
}
