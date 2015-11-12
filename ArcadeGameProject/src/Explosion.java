import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Explosion extends Weapon {

	public Explosion(World world, Point2D centerPoint) {
		super(world, centerPoint);
		this.velocity = 0;
		this.radius = 25;
		this.health = 2;
		this.rateOfFire = 0;
	}

	@Override
	public Color getColor() {
		return Color.ORANGE;
	}

	@Override
	public Shape getShape() {
		return new Ellipse2D.Double(getCenterPoint().getX()-20, getCenterPoint().getY()-20, 50, 50);
	}

	@Override
	public void shoot(Point2D centerPoint) {
		//Not used
	}

	@Override
	public void updatePosition(){
		this.health--;
		if(this.getHealth()==0)
			this.die();
	}

	@Override
	public BufferedImage getImage() {
		return null;
	}
}
