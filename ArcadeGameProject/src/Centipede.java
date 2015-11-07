import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Centipede extends Entity {

	private Color color;
	private boolean right, down;

	public Centipede(World world, Point2D centerPoint) {
		super(world, centerPoint);
		this.color = Color.MAGENTA;
		this.radius = 10;
		this.right = true;
		this.down = false;
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public Shape getShape() {
		return new Ellipse2D.Double(this.getCenterPoint().getX() - 10, this.getCenterPoint().getY() - 10, 20, 20);
	}

	@Override
	public void updatePosition() {
		Point2D nextMove;

		if (this.right && this.getCenterPoint().getX() < 389) { // Go right
			nextMove = new Point2D.Double(this.getCenterPoint().getX() + 1, this.getCenterPoint().getY());
			if (checkCollision(nextMove) == null) {
				this.setCenterPoint(nextMove);
				return;
			} else {
				this.down = true;
			}
		} else if (this.down && this.getCenterPoint().getY() < 389) {
			nextMove = new Point2D.Double(this.getCenterPoint().getX(), this.getCenterPoint().getY() + 20);
			if (canMoveDown(nextMove)) {
				setCenterPoint(nextMove);
			}
			this.down = false;
		}
	}

	public boolean canMoveDown(Point2D nextMove) {
		if (checkCollision(nextMove) != null) {
			return !checkCollisionBottom(nextMove);
		}
		return true;
	}
}