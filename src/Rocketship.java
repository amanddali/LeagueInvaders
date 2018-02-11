import java.awt.Graphics;

public class Rocketship extends GameObject {
	int speed;

	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 5;
	}

	public void update() {
		super.update();
	}

	public void draw(Graphics g) {
		g.drawImage(GamePanel.rocketImg, x, y, width, height, null);
	}
}
