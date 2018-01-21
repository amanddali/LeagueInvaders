import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {
	Rocketship rs2;
	ArrayList<Projectiles> prj;

	ObjectManager(Rocketship rs2) {
		this.rs2 = rs2;
		prj = new ArrayList<Projectiles>();
	}

	public void update() {
		rs2.update();
	}

	public void draw(Graphics g) {
		rs2.draw(g);
	}
}
