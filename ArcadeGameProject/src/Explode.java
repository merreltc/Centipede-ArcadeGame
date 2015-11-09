import java.awt.Color;
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
public class Explode extends Weapon {
	
	private final int RADIUS = 5;

	public Explode(World world, Point2D centerPoint) {
		super(world, centerPoint);
	}

	@Override
	public void shoot(Point2D centerPoint) {
		RapidFire shot = new RapidFire(this.getWorld(), centerPoint);
		this.getWorld().addEntity(shot);
	}

	@Override
	public Shape getShape() {
		return new Ellipse2D.Double(getCenterPoint().getX(), getCenterPoint().getY(), 5, 10);
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub.
		return null;
	}

}
