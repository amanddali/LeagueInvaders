import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Rocketship rs2;
	ArrayList<Projectiles> prj;
	ArrayList<Alien> alien;
	long enemyTimer;
	int enemySpawnTime;

	ObjectManager(Rocketship rs2) {
		this.rs2 = rs2;
		prj = new ArrayList<Projectiles>();
		alien = new ArrayList<Alien>();
		enemyTimer = 0;
		enemySpawnTime = 1000;
	}

	public void update() {
		rs2.update();
		for (Projectiles projectile : prj) {
			projectile.update();
		}
		for (Alien alien2 : alien) {
			alien2.update();
		}
	}

	public void draw(Graphics g) {
		rs2.draw(g);
		for (Projectiles projectile : prj) {
			projectile.draw(g);
		}
		for (Alien alien2 : alien) {
			alien2.draw(g);
		}
	}

	public void addProjectile(Projectiles projectile) {
		prj.add(projectile);
	}

	public void addAlien(Alien alien) {

	}

	public void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			addAlien(new Alien(new Random().nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
			enemyTimer = System.currentTimeMillis();
		}
	}

	public void purgeObjects() {
		for (Alien alien2 : alien) {
			if (alien2.isAlive == false) {
				alien.remove(alien2);
			}
		}
		for (Projectiles projectile : prj) {
			if (projectile.isAlive == false) {
				prj.remove(projectile);
			}
		}
	}
}
