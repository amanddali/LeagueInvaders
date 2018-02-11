import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Rocketship rs2;
	ArrayList<Projectiles> prj;
	ArrayList<Alien> alienList;
	long enemyTimer;
	int enemySpawnTime;
	int score;

	ObjectManager(Rocketship rs2) {
		this.rs2 = rs2;
		prj = new ArrayList<Projectiles>();
		alienList = new ArrayList<Alien>();
		enemyTimer = 0;
		enemySpawnTime = 1000;
		score = 0;
	}

	public void update() {
		rs2.update();
		for (Projectiles projectile : prj) {
			projectile.update();
		}
		for (Alien alien : alienList) {
			alien.update();
		}
	}

	public void draw(Graphics g) {
		rs2.draw(g);
		for (Projectiles projectile : prj) {
			projectile.draw(g);
		}
		for (Alien alien : alienList) {
			alien.draw(g);
		}
	}

	public void addProjectile(Projectiles projectile) {
		prj.add(projectile);
	}

	public void addAlien(Alien alien) {
		alienList.add(alien);
	}

	public void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			addAlien(new Alien(new Random().nextInt(LeagueInvaders.WIDTH - 20), 0, 50, 50));
			enemyTimer = System.currentTimeMillis();
		}
	}

	public void checkCollision() {
		for (Alien alien : alienList) {
			if (rs2.collisionBox.intersects(alien.collisionBox)) {
				rs2.isAlive = false;
			}
			for (Projectiles projectile : prj) {
				if (alien.collisionBox.intersects(projectile.collisionBox)) {
					alien.isAlive = false;
					projectile.isAlive = false;
				}
			}
		}
	}

	public void purgeObjects() {
		for (int i = 0; i < alienList.size(); i++) {
			if (alienList.get(i).isAlive == false) {
				alienList.remove(i);
				score += 1;
			}
		}
		for (int i = 0; i < prj.size(); i++) {
			if (prj.get(i).isAlive == false) {
				prj.remove(i);
			}
		}
	}

	int getScore() {
		return this.score;
	}
}
