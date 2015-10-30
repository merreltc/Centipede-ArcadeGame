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
		// TODO Auto-generated method stub.
		return Color.GREEN;
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub.
		return new Polygon(new int[]{(int)getCenterPoint().getX(), (int)getCenterPoint().getX()+10,
				(int)getCenterPoint().getX()+20}, new int[]{(int)getCenterPoint().getY()+20, (int)getCenterPoint().getY(),
						(int)getCenterPoint().getY()+20}, 3);
	}

	@Override
	public void timePassed() {
		// TODO Auto-generated method stub.
		
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub.
		
	}

	@Override
	public void updatePosition() {
		// TODO Auto-generated method stub.
		
	}

	@Override
	public void checkCollision() {
		// TODO Auto-generated method stub.
		
	}
}
