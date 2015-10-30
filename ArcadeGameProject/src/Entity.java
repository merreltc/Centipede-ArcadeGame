import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;

/**
 * Represents the player, weapons, mushrooms, and monsters in the game.
 *
 * @author Trinity Merrell and Walter Panfil. Created Oct 28, 2015.
 */
public abstract class Entity implements Drawable, Temporal {

	private final double RADIUS = 20;

	private Point2D centerPoint;
	private World world;
	private int health;
	private Color color;
	private Shape shape;
	private boolean paused;

	
	public Entity(World world, Point2D centerPoint) {
		this.centerPoint = centerPoint;
		this.world = world;
		this.health = 100;
		this.color = getColor();
		this.shape = getShape();
	}
	public double getRadius() {
		return this.RADIUS;
	}

	public void setCenterPoint(Point2D point) {
		this.centerPoint = point;
	}

	public Point2D getCenterPoint() {
		return this.centerPoint;

	}

	public World getWorld() {
		return this.world;

	}
	@Override
	public boolean getIsPaused() {
		return this.paused;
	}

	@Override
	public void setIsPaused(boolean paused) {
		this.paused = paused;
	}

	@Override
	public void timePassed() {
		updatePosition();
	}

	@Override
	public void die() {
		this.world.removeEntity(this);
	}

	public boolean checkCollision() {
		for (Drawable e : this.world.getDrawableParts()) {
			if (e != this &&
					Math.abs(((Entity) e).getCenterPoint().getX() - this.getCenterPoint().getX()) <= this.RADIUS
					&& Math.abs(((Entity) e).getCenterPoint().getY() - this.getCenterPoint().getY()) <= this.RADIUS) {
					
				return true;
			}
		}
		return false;
	}
	
	public boolean checkCollisionTop(){
		for (Drawable e : this.world.getDrawableParts()) {
			if (e != this &&
					Math.abs(((Entity) e).getCenterPoint().getX() - this.getCenterPoint().getX()) <= this.RADIUS
					&& Math.abs(((Entity) e).getCenterPoint().getY() - this.getCenterPoint().getY()) <= this.RADIUS
					&&((Entity) e).getCenterPoint().getY() < this.getCenterPoint().getY()) {
					
				return true;
			}
		}
		return false;
	}
	
	public boolean checkCollisionBottom(){
		for (Drawable e : this.world.getDrawableParts()) {
			if (e != this &&
					Math.abs(((Entity) e).getCenterPoint().getX() - this.getCenterPoint().getX()) <= this.RADIUS
					&& Math.abs(((Entity) e).getCenterPoint().getY() - this.getCenterPoint().getY()) <= this.RADIUS
					&&((Entity) e).getCenterPoint().getY() > this.getCenterPoint().getY()) {
					
				return true;
			}
		}
		return false;
	}
	
	public boolean checkCollisionLeft(){
		for (Drawable e : this.world.getDrawableParts()) {
			if (e != this &&
					Math.abs(((Entity) e).getCenterPoint().getX() - this.getCenterPoint().getX()) <= this.RADIUS
					&& Math.abs(((Entity) e).getCenterPoint().getY() - this.getCenterPoint().getY()) <= this.RADIUS
					&&((Entity) e).getCenterPoint().getX() < this.getCenterPoint().getX()) {
					
				return true;
			}
		}
		return false;
	}
	
	public boolean checkCollisionRight(){
		for (Drawable e : this.world.getDrawableParts()) {
			if (e != this &&
					Math.abs(((Entity) e).getCenterPoint().getX() - this.getCenterPoint().getX()) <= this.RADIUS
					&& Math.abs(((Entity) e).getCenterPoint().getY() - this.getCenterPoint().getY()) <= this.RADIUS
					&&((Entity) e).getCenterPoint().getX() > this.getCenterPoint().getX()) {
					
				return true;
			}
		}
		return false;
	}

	public abstract void updatePosition();

}
