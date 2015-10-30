import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * 
 * TODO Put here a description of what this class does.
 *
 * @author Trinity Merrell and Walter Panfil.
 *         Created Oct 28, 2015.
 */
public class Pierce extends Weapon {
	private Ellipse2D.Double bullet;

	public Pierce(World world, Point2D centerPoint) {
		super(world, centerPoint);
		this.bullet = new Ellipse2D.Double(centerPoint.getX(), centerPoint.getY(), 2, 3);
	}

	@Override
	public void shoot() {
		this.getWorld().addEntity(this);

	}

	@Override
	public Shape getShape() {
		return this.bullet;
	}

}
