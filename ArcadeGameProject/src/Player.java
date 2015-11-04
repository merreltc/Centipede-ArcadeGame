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
		return Color.GREEN;
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
		if (this.up && this.getCenterPoint().getY() > 310 && !checkCollisionTop()) { // Move
																							// up
			setCenterPoint(new Point2D.Double(this.getCenterPoint().getX(), this.getCenterPoint().getY() - 2));
		} else if (this.down && this.getCenterPoint().getY() < 380 && !checkCollisionBottom()) { // Move
																										// down
			setCenterPoint(new Point2D.Double(this.getCenterPoint().getX(), this.getCenterPoint().getY() + 2));
		} else if (this.left && this.getCenterPoint().getX() > 5 && !checkCollisionLeft()) { // Move
																									// Left
			setCenterPoint(new Point2D.Double(this.getCenterPoint().getX() - 2, this.getCenterPoint().getY()));
		} else if (this.right&& this.getCenterPoint().getX() < 375 && !checkCollisionRight()) { // Move
																										// Right
			setCenterPoint(new Point2D.Double(this.getCenterPoint().getX() + 2, this.getCenterPoint().getY()));
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
