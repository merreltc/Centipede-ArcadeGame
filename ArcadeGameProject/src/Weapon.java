import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;

public abstract class Weapon extends Entity {
	
	private int rateOfFire;
	private int velocity;

	public Weapon(World world, Point2D centerPoint) {
		super(world, centerPoint);
		// TODO Auto-generated constructor stub.
	}
	
	public abstract void shoot();

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub.
		return null;
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub.
		return null;
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
	public void setIsPaused(boolean isPaused) {
		// TODO Auto-generated method stub.

	}

	@Override
	public boolean getIsPaused() {
		// TODO Auto-generated method stub.
		return false;
	}

	@Override
	public void updatePosition() {
		// TODO Auto-generated method stub.

	}

	@Override
	public void checkCollision(Entity entity) {
		// TODO Auto-generated method stub.

	}

}
