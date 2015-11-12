import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 * 
 * TODO Put here a description of what this class does.
 *
 * @author Trinity Merrell and Walter Panfil.
 *         Created Oct 28, 2015.
 */
public class Explode extends Weapon {
	

	public Explode(World world, Point2D centerPoint) {
		super(world, centerPoint);
		this.velocity = 2;
		this.health = 1;
		this.radius = 5;
		this.rateOfFire = 30;
	}

	@Override
	public void shoot(Point2D centerPoint) {
		Explode shot = new Explode(this.getWorld(), centerPoint);
		this.getWorld().addEntity(shot);
	}

	public Shape getShape() {
		return new Ellipse2D.Double(getCenterPoint().getX(), getCenterPoint().getY(), 10, 10);
	}

	@Override
	public Color getColor() {
		return Color.RED;
	}

	@Override
	public void die(){
		this.getWorld().addEntity(new Explosion(this.getWorld(), this.getCenterPoint()));
		super.die();
	}

	@Override
	public BufferedImage getImage() {
		return null;
	}
}
