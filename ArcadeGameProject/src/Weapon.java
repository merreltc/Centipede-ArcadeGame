import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * 
 * TODO Put here a description of what this class does.
 *
 * @author Trinity Merrell and Walter Panfil.
 *         Created Oct 28, 2015.
 */
public abstract class Weapon extends Entity {
	
	private int rateOfFire;
	private int velocity;

	public Weapon(World world, Point2D centerPoint) {
		super(world, centerPoint);
		velocity = 10;
		rateOfFire = 1;
	}
	
	public abstract void shoot();

	@Override
	public Color getColor() {
		return Color.PINK;
	}

	@Override
	public void updatePosition() {
		double currX = getCenterPoint().getX();
		double currY = getCenterPoint().getY();
		// Check whether the mover is paused or not.
		if (!this.getIsPaused()) {
			if(!(currY < 0)) {
			// Update new position.
			currX -= (this.velocity);
			currY -= (this.velocity);
			setCenterPoint(new Point2D.Double(currX, currY));
			}
		} else {
			die();
		}

	}

}
