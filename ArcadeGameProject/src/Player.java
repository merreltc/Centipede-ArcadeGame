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
	private int move; // 0 = stationary, 1 = up, 2 = down, 3 = left, 4 = right

	public Player(World world, Point2D centerPoint) {
		super(world, centerPoint);
		Weapon weapon = new RapidFire(world,
				new Point2D.Double(this.getCenterPoint().getX() + 7.5, this.getCenterPoint().getY() - 10));
		this.currentWeapon = weapon;
		this.move = 0;
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
				new int[] { (int) getCenterPoint().getX(), (int) getCenterPoint().getX() + 10,
						(int) getCenterPoint().getX() + 20 },
				new int[] { (int) getCenterPoint().getY() + 20, (int) getCenterPoint().getY(),
						(int) getCenterPoint().getY() + 20 },
				3);
	}

	@Override
	public void updatePosition() {
		if (this.move == 1 && this.getCenterPoint().getY() > 310 && !checkCollisionTop()) { // Move
																							// up
			setCenterPoint(new Point2D.Double(this.getCenterPoint().getX(), this.getCenterPoint().getY() - 5));
		} else if (this.move == 2 && this.getCenterPoint().getY() < 380 && !checkCollisionBottom()) { // Move
																										// down
			setCenterPoint(new Point2D.Double(this.getCenterPoint().getX(), this.getCenterPoint().getY() + 5));
		} else if (this.move == 3 && this.getCenterPoint().getX() > 5 && !checkCollisionLeft()) { // Move
																									// Left
			setCenterPoint(new Point2D.Double(this.getCenterPoint().getX() - 5, this.getCenterPoint().getY()));
		} else if (this.move == 4 && this.getCenterPoint().getX() < 375 && !checkCollisionRight()) { // Move
																										// Right
			setCenterPoint(new Point2D.Double(this.getCenterPoint().getX() + 5, this.getCenterPoint().getY()));
		}
		this.move = 0;
	}

	public void move(int move) {
		this.move = move;
	}
}
