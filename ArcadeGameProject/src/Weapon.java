import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * 
 * Represents an abstract weapon that can be shot and moves through the field.
 *
 * @author Trinity Merrell and Walter Panfil. Created Oct 28, 2015.
 */
public abstract class Weapon extends Entity {

	protected int rateOfFire;
	private int cooldown;
	private double velocity;

	public Weapon(World world, Point2D centerPoint) {
		super(world, centerPoint);
		this.velocity = 3;
		this.cooldown = 0;
		this.health = 1;
		this.radius = 7;
	}

	public abstract void shoot(Point2D centerPoint);

	@Override
	public Color getColor() {
		return Color.PINK;
	}

	@Override
	public void updatePosition() {
		if (this.getHealth() == 0)
			this.die();

		Point2D nextMove;
		double currY = getCenterPoint().getY();
		if (currY >= 0) {
			// Update new position.
			currY -= (this.velocity);
			nextMove = new Point2D.Double(getCenterPoint().getX(), currY);

			setCenterPoint(nextMove);
			if (!canMoveUp(nextMove)) {
				this.takeDamage();
			}
		} else {
			die();
		}
	}
	
	public boolean readyToFire(){
		return this.cooldown == 0;
	}
	
	public void cooldown(){
		this.cooldown --;
	}
	
	public void overheat(){
		this.cooldown = this.rateOfFire;
	}

	private boolean canMoveUp(Point2D nextMove) {
		if (checkCollision(nextMove) != null) {
			return false;
		}
		return true;
	}
}
