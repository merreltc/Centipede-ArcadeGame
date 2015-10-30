import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Point2D;
/**
 * 
 * TODO Put here a description of what this class does.
 *
 * @author Trinity Merrell and Walter Panfil.
 *         Created Oct 28, 2015.
 */

public class Player extends Entity {
	private Weapon currentWeapon;

	public Player(World world, Point2D centerPoint) {
		super(world, centerPoint);
		Weapon weapon = new RapidFire(world, this.getCenterPoint());
		this.currentWeapon = weapon;
	}

	public void fire() {
		this.currentWeapon.shoot();
	}

	@Override
	public Color getColor() {
		return Color.GREEN;
	}

	@Override
	public Shape getShape() {
		return new Polygon(new int[]{(int)getCenterPoint().getX(), (int)getCenterPoint().getX()+10,
				(int)getCenterPoint().getX()+20}, new int[]{(int)getCenterPoint().getY()+20, (int)getCenterPoint().getY(),
						(int)getCenterPoint().getY()+20}, 3);
	}

	@Override
	public void updatePosition() {
		// Do nothing
	}
	public void move(boolean vertical, boolean positive) {
		if(vertical && positive && this.getCenterPoint().getY() > 50) { // Up
			setCenterPoint(new Point2D.Double(this.getCenterPoint().getX(),
					this.getCenterPoint().getY() + 5));
		} else if (vertical && !positive && this.getCenterPoint().getY() < 400) { // Down
			setCenterPoint(new Point2D.Double(this.getCenterPoint().getX(),
					this.getCenterPoint().getY() - 5));
		} else if (!vertical && positive && this.getCenterPoint().getX() < 375) { // Right
			setCenterPoint(new Point2D.Double(this.getCenterPoint().getX() + 5,
					this.getCenterPoint().getY()));
		} else if (!vertical && !positive && this.getCenterPoint().getX() > 5) { // Left
			setCenterPoint(new Point2D.Double(this.getCenterPoint().getX() - 5,
					this.getCenterPoint().getY()));
		}
	
	}
}
