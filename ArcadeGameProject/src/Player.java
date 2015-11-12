import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * 
 * Represents a player
 *
 * @author Trinity Merrell and Walter Panfil. Created Oct 28, 2015.
 */

public class Player extends Entity {
	private Weapon currentWeapon;
	private boolean right, left, up, down;
	private int lives;
	private BufferedImage playerImage;

	public Player(World world, Point2D centerPoint) throws IOException {
		super(world, centerPoint);
		// Load Image.
		BufferedImage img = ImageIO.read(getClass().getResource("/Ship.png"));
		this.playerImage = img;
		
		this.radius = 9;
		this.health = 1;
		this.lives = 3;
		Weapon weapon = new RapidFire(world,
				new Point2D.Double(this.getCenterPoint().getX() + 7.5, this.getCenterPoint().getY() - 10));
		this.currentWeapon = weapon;
	}

	public void fire() {
		if (this.currentWeapon.readyToFire()) {
			this.currentWeapon
					.shoot(new Point2D.Double(this.getCenterPoint().getX(), this.getCenterPoint().getY() - 10));
			this.currentWeapon.overheat();
		}
	}

	public int getLives() {
		return this.lives;
	}

	public void setCurrentWeapon(int weapon) {
		if (weapon == 1) {
			this.currentWeapon = new RapidFire(getWorld(),
					new Point2D.Double(this.getCenterPoint().getX() + 7.5, this.getCenterPoint().getY() - 10));
		} else if (weapon == 2) {
			this.currentWeapon = new Pierce(getWorld(),
					new Point2D.Double(this.getCenterPoint().getX() + 7.5, this.getCenterPoint().getY() - 10));
		} else if (weapon == 3) {
			this.currentWeapon = new Explode(getWorld(),
					new Point2D.Double(this.getCenterPoint().getX() + 7.5, this.getCenterPoint().getY() - 10));
		}
	}

	@Override
	public Color getColor() {
		return Color.GREEN;
	}

	@Override
	public BufferedImage getImage() { // getImage
		return this.playerImage;
	}

	@Override
	public void updatePosition() {
		// System.out.println("Collide w/ " + checkCollision(getCenterPoint()) +
		// "\nCollide Bottom: " +
		// checkCollisionBottom(getCenterPoint()) + "\nCollide Top: "
		// + checkCollisionTop(getCenterPoint()));

		if (getHealth() <= 0) {
			this.lives --;
			setCenterPoint(new Point2D.Double(9 * 20 + 20 / 2, 19 * 20 + 20 / 2));
		}

		if (this.lives <= 0) {
			die();
			this.getWorld().setIsPaused(true);
			JOptionPane.showMessageDialog(null, "Game Over");
		}

		if (!this.currentWeapon.readyToFire()) {
			this.currentWeapon.cooldown();
		}

		Point2D.Double nextMove;

		if (this.up && this.getCenterPoint().getY() > 310) { // Move Up
			nextMove = new Point2D.Double(this.getCenterPoint().getX(), this.getCenterPoint().getY() - 3);
			if (!collisionCentipede(nextMove) && checkCollision(nextMove) == null) {
				setCenterPoint(nextMove);
			}
		}
		if (this.down && this.getCenterPoint().getY() < 389) { // Move down
			nextMove = new Point2D.Double(this.getCenterPoint().getX(), this.getCenterPoint().getY() + 3);
			if (!collisionCentipede(nextMove) && checkCollision(nextMove) == null) {
				setCenterPoint(nextMove);
			}
		}
		if (this.left && this.getCenterPoint().getX() > 10) { // Move Left
			nextMove = new Point2D.Double(this.getCenterPoint().getX() - 3, this.getCenterPoint().getY());
			if (!collisionCentipede(nextMove) && checkCollision(nextMove) == null) {
				setCenterPoint(nextMove);
			}
		}
		if (this.right && this.getCenterPoint().getX() < 387) { // Move Right
			nextMove = new Point2D.Double(this.getCenterPoint().getX() + 3, this.getCenterPoint().getY());
			if (!collisionCentipede(nextMove) && checkCollision(nextMove) == null) {
				setCenterPoint(nextMove);
			}
		}

		if (collisionCentipede(this.getCenterPoint())) {
			takeDamage();
		}
	}

	public boolean collisionCentipede(Point2D nextMove) {
		if (checkCollision(nextMove) != null && (checkCollision(nextMove).getClass().equals(CentipedeSegment.class)
				|| checkCollision(nextMove).getClass().equals(Flea.class)
				|| checkCollision(nextMove).getClass().equals(Spider.class))) {
			return true;
		}
		return false;
	}

	public void right(boolean right) {
		this.right = right;
	}

	public void left(boolean left) {
		this.left = left;
	}

	public void up(boolean up) {
		this.up = up;
	}

	public void down(boolean down) {
		this.down = down;
	}

	public void setHealth(int i) {
		this.health = i;
	}

	@Override
	public Shape getShape() {
		return null;
	}
}
