import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Point2D;

/**
 * 
 * TODO Put here a description of what this class does.
 *
 * @author Trinity Merrell and Walter Panfil.
 *         Created Oct 28, 2015.
 */

public class Player extends Entity {
	private Weapon currentWeapon;
	private boolean right, left, up, down;
	private int lives;

	public Player(World world, Point2D centerPoint) {
		super(world, centerPoint);
		this.radius = 9;
		this.health = 1;
		this.lives = 3;
		Weapon weapon = new RapidFire(world,
				new Point2D.Double(this.getCenterPoint().getX() + 7.5, this.getCenterPoint().getY() - 10));
		this.currentWeapon = weapon;
	}

	public void fire() {
		this.currentWeapon
				.shoot(new Point2D.Double(this.getCenterPoint().getX() + 7.5, this.getCenterPoint().getY() - 10));
	}

	@Override
	public Color getColor() {
		return Color.CYAN;
	}

	@Override
	public Shape getShape() {
		return new Polygon(
				new int[] { (int) getCenterPoint().getX() - this.getWorld().CELL_WIDTH/2, (int) getCenterPoint().getX(),
						(int) getCenterPoint().getX() + this.getWorld().CELL_WIDTH/2 },
				new int[] { (int) getCenterPoint().getY() + this.getWorld().CELL_WIDTH/2,
						(int) getCenterPoint().getY() - this.getWorld().CELL_WIDTH/2,
						(int) getCenterPoint().getY() + this.getWorld().CELL_WIDTH/2 },3);
	}

	@Override
	public void updatePosition() {
//		System.out.println("Collide w/ " + checkCollision(getCenterPoint()) + "\nCollide Bottom: " +
//			checkCollisionBottom(getCenterPoint()) + "\nCollide Top: "
//				+ checkCollisionTop(getCenterPoint()));
		
		if(getHealth() == 0) {
			die();
			this.getWorld().setIsPaused(true);
		}
		
		Point2D.Double nextMove;
		
		if (this.up && this.getCenterPoint().getY() > 310) { // Move Up
			nextMove = new Point2D.Double(this.getCenterPoint().getX(), this.getCenterPoint().getY() - 1);
			if(!collisionCentipede(nextMove) && checkCollision(nextMove) == null) {
				setCenterPoint(nextMove);
			} else if(collisionCentipede(nextMove)) {
				this.lives--;
				takeDamage();
			}
		} else if (this.down && this.getCenterPoint().getY() < 389) { // Move down
			nextMove = new Point2D.Double(this.getCenterPoint().getX(), this.getCenterPoint().getY() + 1);
			if(!collisionCentipede(nextMove) && checkCollision(nextMove) == null) {
				setCenterPoint(nextMove);
			} else if(collisionCentipede(nextMove)) {
				this.lives--;
				takeDamage();
			}
		} else if (this.left && this.getCenterPoint().getX() > 10) { // Move Left
			nextMove = new Point2D.Double(this.getCenterPoint().getX() - 1, this.getCenterPoint().getY());
			if(!collisionCentipede(nextMove) && checkCollision(nextMove) == null) {
				setCenterPoint(nextMove);
			} else if(collisionCentipede(nextMove)) {
				this.lives--;
				takeDamage();
			}
		} else if (this.right && this.getCenterPoint().getX() < 387) { // Move Right
			nextMove = new Point2D.Double(this.getCenterPoint().getX() + 1, this.getCenterPoint().getY());
			if(!collisionCentipede(nextMove) && checkCollision(nextMove) == null) {
				setCenterPoint(nextMove);
			} else if(collisionCentipede(nextMove)) {
				this.lives--;
				takeDamage();
				this.currentWeapon = null;
			}
		}
	}
	
	public boolean collisionCentipede(Point2D nextMove) {
		if(checkCollision(nextMove) != null 
				&& checkCollision(nextMove).getClass().equals(Centipede.class)){
			return true;
		}
		return false;
	}

	public void right(boolean right){
		this.right = right;
	}
	
	public void left(boolean left){
		this.left = left;
	}
	
	public void up(boolean up){
		this.up = up;
	}
	
	public void down(boolean down){
		this.down = down;
	}
}
