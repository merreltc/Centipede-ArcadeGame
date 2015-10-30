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


	public RapidFire(World world, Point2D centerPoint) {
		super(world, centerPoint);
	}
	
	@Override
	public double getRadius() {
		return 5.0;
	}

	@Override
	public Shape getShape() {
		return new Ellipse2D.Double(getCenterPoint().getX(), getCenterPoint().getY(), 5, 10);
	}

	@Override
	public void shoot(Point2D centerPoint) {
		RapidFire shot = new RapidFire(this.getWorld(), centerPoint);
		this.getWorld().addEntity(shot);
	}
}
