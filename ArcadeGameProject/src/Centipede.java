import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Centipede extends Entity {
	// Inner class of centipede heads

	public Centipede(World world, Point2D centerPoint) {
		super(world, centerPoint);
		this.moveVert = 0;
		this.moveHoriz = 3;
	}

	private final int velocity = 3;
	private int scoreValue = 100;
	private int length;
	private int moveVert; // 0 = stationary, 1 = up, 2 = down
	private int moveHoriz; // 0 = stationary, 1 = left, 2 = right

	public void split(int segment) {
		// not yet
	}

	public void turn(int direction) {
		// not yet
	}

	@Override
	public Color getColor() {
		return Color.ORANGE;
	}

	@Override
	public Shape getShape() {
		return new Ellipse2D.Double(this.getCenterPoint().getX() - 10, this.getCenterPoint().getY() - 10, 20, 20);
	}

	/**
	 * Will remove Centipede segment, replacing it with a mushroom and splitting
	 * the remaining centipede into 2 separate segments.
	 *
	 */
	@Override
	public void die() {
		Mushroom newMush = new Mushroom(this.getWorld(), this.getCenterPoint());
		// split(getIndexOf(this.head));
	}

	@Override
	public void updatePosition() {
		if (getIsPaused()) {
			this.moveHoriz = 0;
			this.moveVert = 0;
		}
		
		if (this.moveVert == 1 && this.getCenterPoint().getY() > 435) { // Move up
			setCenterPoint(new Point2D.Double(this.getCenterPoint().getX(), this.getCenterPoint().getY() - 1));
			// if(checkCollisionTop()) {
			//}
			this.moveVert = 0; 
		} else if (this.moveVert == 2 && this.getCenterPoint().getY() < 380 && checkCollisionBottom()) { // Move down
			setCenterPoint(new Point2D.Double(this.getCenterPoint().getX(), this.getCenterPoint().getY() + 1));
			this.moveVert = 0; 
		} else if (this.moveHoriz == 1 && this.getCenterPoint().getX() > 5) { // Move left
			if (checkCollisionLeft()) {
				this.moveVert = 2;
				this.moveHoriz = 2;
			}
			setCenterPoint(new Point2D.Double(this.getCenterPoint().getX() - 1, this.getCenterPoint().getY()));
		} else if (this.moveVert == 4 && this.getCenterPoint().getX() < 375 && !checkCollisionRight()) { // Move right
			if (checkCollisionRight()) {
				this.moveVert = 2;
				this.moveHoriz = 1;
			}
			setCenterPoint(new Point2D.Double(this.getCenterPoint().getX() + 1, this.getCenterPoint().getY()));
		}
	}
}
