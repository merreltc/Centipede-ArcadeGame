import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;

/**
 * Represents the player, weapons, mushrooms, and monsters in the game.
 *
 * @author merreltc.
 *         Created Oct 28, 2015.
 */
public abstract class Entity implements Drawable, Temporal {
	
	private Point2D centerPoint;
	private World world;
	private int health;
	private Color color;
	private Shape shape;
	
	public Entity(World world, Point2D centerPoint) {
		this.centerPoint = centerPoint;
		
	}
	
	public void setCenterPoint(Point2D point) {
		this.centerPoint = point;
	}
	
	public Point2D getCenterPoint() {
		return centerPoint;
		
	}
	
	public World getWorld() {
		return world;
		
	}
	
	public abstract void updatePosition();
	public abstract void checkCollision(Entity entity);

}
