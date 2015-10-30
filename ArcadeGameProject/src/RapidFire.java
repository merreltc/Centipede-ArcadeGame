import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * 
 * Represents a bullet that fires three times when player hits the "Z" key
 *
 * @author Trinity Merrell and Walter Panfil.
 *         Created Oct 28, 2015.
 */
public class RapidFire extends Weapon {
	private Ellipse2D.Double bullet;
	
	public RapidFire(World world, Point2D centerPoint) {
		super(world, centerPoint);
		this.bullet = new Ellipse2D.Double(centerPoint.getX(), centerPoint.getY(), 2, 3);
	}

	@Override
	public void shoot() {
		updatePosition();
	}

	@Override
	public Shape getShape() {
		return this.bullet;
	}
}
