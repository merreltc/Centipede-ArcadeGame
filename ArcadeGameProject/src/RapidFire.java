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
	}
	
//	@Override
//	public double getRadius() {
//		return 5.0;
//	}

	@Override
	public void shoot() {
		this.bullet = new Ellipse2D.Double(getCenterPoint().getX(), getCenterPoint().getY(), 5, 10);
		this.getWorld().addEntity(this);
	}

	@Override
	public Shape getShape() {
		return this.bullet;
	}
}
