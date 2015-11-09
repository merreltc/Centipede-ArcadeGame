import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Pierce extends Weapon {
	
	public Pierce(World world, Point2D centerPoint) {
		super(world, centerPoint);
		this.velocity = 15;
		this.health = 20;
		this.rateOfFire = 30;
		this.radius = 2.5;
	}

	@Override
	public Color getColor() {
		return Color.YELLOW;
	}
	
	@Override
	public Shape getShape() {
		return new Ellipse2D.Double(getCenterPoint().getX() - 5, getCenterPoint().getY()-2.5, 5, 20);
	}

	@Override
	public void shoot(Point2D centerPoint) {
		Pierce shot = new Pierce(this.getWorld(), centerPoint);
		this.getWorld().addEntity(shot);
	}

}
