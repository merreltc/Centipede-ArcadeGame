import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;

/**
 * Represents the player, weapons, mushrooms, and monsters in the game.
 *
 * @author Trinity Merrell and Walter Panfil.
 *         Created Oct 28, 2015.
 */
public abstract class Entity implements Drawable, Temporal {
	
	private Point2D centerPoint;
	private World world;
	private int health;
	private Color color;
	private Shape shape;
	private boolean paused;
	private double radius;
	
	public Entity(World world, Point2D centerPoint) {
		this.centerPoint = centerPoint;
		this.world = world;
		this.health = 100;
		this.color = getColor();
		this.shape = getShape();
		
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
	
	public double getRadius() {
		return this.radius;
	}
	
	@Override
	public boolean getIsPaused(){
		return this.paused;
	}
	
	@Override
	public void setIsPaused(boolean paused){
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
	
	public abstract void checkCollision();
	
	public abstract void updatePosition();


}
