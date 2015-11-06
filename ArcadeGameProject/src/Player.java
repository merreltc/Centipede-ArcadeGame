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

	public Player(World world, Point2D centerPoint) {
		super(world, centerPoint);
		radius = 10;
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
				new int[] { (int) getCenterPoint().getY() + this.getWorld().CELL_WIDTH/2, (int) getCenterPoint().getY() - this.getWorld().CELL_WIDTH/2,
						(int) getCenterPoint().getY() + this.getWorld().CELL_WIDTH/2 },
				3);
	}

	@Override
	public void updatePosition() {
		if (this.up && this.getCenterPoint().getY() > 310 && !checkCollisionTop()) { // Move Up
			setCenterPoint(new Point2D.Double(this.getCenterPoint().getX(), this.getCenterPoint().getY() - 2));
		} else if (this.down && this.getCenterPoint().getY() < 380 && !checkCollisionBottom()) { // Move down
			setCenterPoint(new Point2D.Double(this.getCenterPoint().getX(), this.getCenterPoint().getY() + 2));
		} else if (this.left && this.getCenterPoint().getX() > 10 && !checkCollisionLeft()) { // Move Left
			setCenterPoint(new Point2D.Double(this.getCenterPoint().getX() - 2, this.getCenterPoint().getY()));
		} else if (this.right&& this.getCenterPoint().getX() < 385 && !checkCollisionRight()) { // Move Right
			setCenterPoint(new Point2D.Double(this.getCenterPoint().getX() + 2, this.getCenterPoint().getY()));
		}
		
		if(this.checkCollision() != null && this.checkCollision().getClass().equals(Centipede.class)){
			this.currentWeapon = null;
			this.die();
		}
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
