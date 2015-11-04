import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * 
 * Represents an abstract weapon that can be shot and moves through the field.
 *
 * @author Trinity Merrell and Walter Panfil.
 *         Created Oct 28, 2015.
 */
public abstract class Weapon extends Entity {

	private int rateOfFire;
	private int velocity;

	public Weapon(World world, Point2D centerPoint) {
		super(world, centerPoint);
		velocity = 1;
		rateOfFire = 1;
	}

	public abstract void shoot(Point2D centerPoint);

	@Override
	public Color getColor() {
		return Color.PINK;
	}

	@Override
	public void updatePosition() {
		double currY = getCenterPoint().getY();
		if (currY >= 0) {
			// Update new position.
			currY -= (this.velocity);
			setCenterPoint(new Point2D.Double(getCenterPoint().getX(), currY));
		} else if(checkCollisionTop()) {
			die();
		} else {
			die();
		}
	}
}
