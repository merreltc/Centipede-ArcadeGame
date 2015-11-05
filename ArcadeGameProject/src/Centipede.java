import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Centipede extends Entity {

	private final double velocity = 1.3;
	private int scoreValue = 100;
	private int length;
	private boolean up, down, left, right;

	public Centipede(World world, Point2D centerPoint) {
		super(world, centerPoint);
		this.left = true;
		this.right = false;
		this.up = false;
		this.down = false;
		System.out.println(getCenterPoint().getX());
	}

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
		return new Ellipse2D.Double(getCenterPoint().getX() - 10, getCenterPoint().getY() - 10, 20, 20);
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

	/**
	 * Right bound: > 385
	 * Left bound: < 10
	 * Top bound: < 10
	 * Bottom bound: >425
	 */
	@Override
	public void updatePosition() {
		if (getIsPaused()) {
			this.up = false;
			this.down = false;
			this.left = false;
			this.right = false;
		}
		int counter = 0;
		

		
		if (this.down && getCenterPoint().getY() < 425) {
			setCenterPoint(new Point2D.Double(getCenterPoint().getX(), getCenterPoint().getY() + 1));
			if (checkCollisionBottom() || checkCollision() != null || (getCenterPoint().getY() - 10) % 20 == 0) {
				this.down = false;
			}
//			if(getCenterPoint().getY() < 70) {
//				System.out.println("\nCOLLIDE WITH " + checkCollision() + "\nBOTTOM: " + checkCollisionBottom()
//				+  "\nTOP: " + checkCollisionTop() + "\nRIGHT: " + checkCollisionRight() + "\nLEFT: " +
//				checkCollisionLeft() + "\nX IS " + getCenterPoint().getX() + "\nY IS " + getCenterPoint().getY());
//				}
		} else if (this.left && getCenterPoint().getX() >= 10) {
			if (checkCollisionLeft() || getCenterPoint().getX() == 10) {
				this.left = false;
				this.right = true;
				this.down = true;
				System.out.println(getCenterPoint().getX());
			}
			setCenterPoint(new Point2D.Double(getCenterPoint().getX() - 1, getCenterPoint().getY()));
		} else if (this.right && getCenterPoint().getX() <= 388) {
			if (checkCollisionRight() || getCenterPoint().getX() == 388) {
				this.left = true;
				this.right = false;
				this.down = true;
			}
			setCenterPoint(new Point2D.Double(getCenterPoint().getX() + 1, getCenterPoint().getY()));
		}

	}
}
