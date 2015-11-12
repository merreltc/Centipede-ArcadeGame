import java.awt.geom.Point2D;

/**
 * Represents the player, weapons, mushrooms, and monsters in the game.
 *
 * @author Trinity Merrell and Walter Panfil. Created Oct 28, 2015.
 */
public abstract class Entity implements Drawable, Temporal {

	protected double radius;

	private Point2D centerPoint;
	private World world;
	protected int health;
	private boolean paused;

	public Entity(World world, Point2D centerPoint) {
		this.centerPoint = centerPoint;
		this.world = world;
		this.health = 1;
	}

	public double getRadius() {
		return this.radius;
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

	public void takeDamage() {
		this.health--;
	}

	public int getHealth() {
		return this.health;
	}

	/**
	 * 
	 * Checks next position of an entity to make sure its not colliding with anything
	 *
	 * @param testMove candidate for next move
	 * @return Entity the object is colliding with
	 */
	public Entity checkCollision(Point2D testMove) {
		for (Drawable d : this.world.getDrawableParts()) {
			Entity e = (Entity) d;
			if (e != this && Math.abs(e.getCenterPoint().getX() - testMove.getX()) <= this.getRadius() + e.getRadius()
					&& Math.abs(e.getCenterPoint().getY() - testMove.getY()) <= this.getRadius() + e.getRadius()
					&& !e.getClass().equals(this.getClass())) {

				return e;
			}
		}
		return null;
	}

	public abstract void updatePosition();

}
