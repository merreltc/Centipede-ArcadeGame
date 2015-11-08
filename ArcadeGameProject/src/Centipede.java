import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Centipede extends Entity {

	private Color color;
	private boolean right, down, up, lastVert;
	public Centipede(World world, Point2D centerPoint) {
		super(world, centerPoint);
		this.color = Color.MAGENTA;
		this.radius = 9;
		this.right = true;
		this.down = false;
		this.up = false;
		this.lastVert = true; // true = down, false = up
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

		if (this.down && this.lastVert) { // Go down
			nextMove = new Point2D.Double(this.getCenterPoint().getX(), this.getCenterPoint().getY() + 20);
			if (!checkCollisionBottom(nextMove) && nextMove.getY() <= 395) {
				setCenterPoint(nextMove);
			} else if(nextMove.getY() > 405) {
				this.up = true;
				this.lastVert = !this.lastVert;
			}
			this.down = false;
		} else if (this.up && !this.lastVert) {
			nextMove = new Point2D.Double(this.getCenterPoint().getX(), this.getCenterPoint().getY() - 20);
			if (!checkCollisionTop(nextMove) && nextMove.getY() >= 10) {
				setCenterPoint(nextMove);
			} else if(nextMove.getY() < 10) {
				this.down = true;
				this.lastVert = !this.lastVert;
			}
			this.up = false;
		} else if (this.right) { // Go right
			nextMove = new Point2D.Double(this.getCenterPoint().getX() + 3, this.getCenterPoint().getY());
			if (checkCollision(nextMove) == null && nextMove.getX() <= 391) {
				setCenterPoint(nextMove);
			} else {
				this.right = false;
				this.down = this.lastVert;
				this.up = !this.lastVert;
			}
		} else if (!this.right) { // Go left
			nextMove = new Point2D.Double(this.getCenterPoint().getX() - 3, this.getCenterPoint().getY());
			if (checkCollision(nextMove) == null && nextMove.getX() >= 10) {
				setCenterPoint(nextMove);
			} else {
				this.right = true;
				this.down = this.lastVert;
				this.up = !this.lastVert;
			}
		 }
//		System.out.println("Collision? " + checkCollision(getCenterPoint()) +
//				"\nCollision bottom? " + checkCollisionBottom(getCenterPoint()) +
//				"\nCollision top? " + checkCollisionTop(getCenterPoint()) +
//				"\nX = " + getCenterPoint().getX() +
//				"\nY = " + getCenterPoint().getY());
	}
}