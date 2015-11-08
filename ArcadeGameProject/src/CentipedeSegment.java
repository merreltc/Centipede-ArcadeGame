import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class CentipedeSegment extends Entity {

	private Color color;
	private boolean right, down, up, lastVert;
	private Centipede centipede;
	
	public CentipedeSegment(World world, Point2D centerPoint, Centipede centipede) {
		super(world, centerPoint);
		this.centipede = centipede;
		this.color = Color.MAGENTA;
		this.radius = 9;
		this.health = 1;
		this.right = false;
		this.down = false;
		this.up = false;
		this.lastVert = true; // true = down, false = up
		// System.out.println("X: " + centerPoint.getX() + "Y: " +
		// centerPoint.getY());
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public Shape getShape() {
		return new Ellipse2D.Double(this.getCenterPoint().getX() - 10, this.getCenterPoint().getY() - 10, 20, 20);
	}
	
	public void setDirection(boolean right) {
		this.right = right;
	}

	@Override
	public void updatePosition() {
		if (this.getHealth() == 0)
			this.die();
		
		if (checkCollision(getCenterPoint()) != null
				&& checkCollision(getCenterPoint()).getClass().equals(RapidFire.class)) {
			this.takeDamage();
			this.centipede.split(this);
			return;
		}

		Point2D nextMove;

		if (this.down && this.lastVert && !getIsPaused()) { // Go down
			nextMove = new Point2D.Double(this.getCenterPoint().getX(), this.getCenterPoint().getY() + 20);
			if (!checkCollisionBottom(nextMove) && nextMove.getY() <= 405) {
				setCenterPoint(nextMove);
			} else if (nextMove.getY() >= 405) {
				this.up = true;
				this.lastVert = !this.lastVert;
			}
			this.down = false;
		} else if (this.up && !this.lastVert && !getIsPaused()) { // Go up
			nextMove = new Point2D.Double(this.getCenterPoint().getX(), this.getCenterPoint().getY() - 20);

			if (!checkCollisionTop(nextMove) && nextMove.getY() >= 10) {
				setCenterPoint(nextMove);
			} else if (checkCollisionTop(nextMove)) {
				if (this.right) { // Go right
					setCenterPoint(new Point2D.Double(this.getCenterPoint().getX() + 1, this.getCenterPoint().getY()));
				} else { // Go left
					setCenterPoint(new Point2D.Double(this.getCenterPoint().getX() - 1, this.getCenterPoint().getY()));
				}
			} else if (nextMove.getY() <= 10) {
				this.down = true;
				this.lastVert = !this.lastVert;
			}

			this.up = false;
		} else if (this.right && !getIsPaused()) { // Go right
			nextMove = new Point2D.Double(this.getCenterPoint().getX() + 1, this.getCenterPoint().getY());
			if ((checkCollision(nextMove) == null || !checkCollision(nextMove).getClass().equals(Mushroom.class)) && nextMove.getX() <= 391) {
				setCenterPoint(nextMove);
			} else if (checkCollision(nextMove) != null && checkCollision(nextMove).getClass().equals(Player.class)) {
				setCenterPoint(nextMove);
			} else {
				this.right = false;
				this.down = this.lastVert;
				this.up = !this.lastVert;
			}
		} else if (!this.right && !getIsPaused()) { // Go left
			nextMove = new Point2D.Double(this.getCenterPoint().getX() - 1, this.getCenterPoint().getY());
			if ((checkCollision(nextMove) == null || !checkCollision(nextMove).getClass().equals(Mushroom.class)) && nextMove.getX() >= 10) {
				setCenterPoint(nextMove);
			} else if (checkCollision(nextMove) != null && checkCollision(nextMove).getClass().equals(Player.class)) {
				setCenterPoint(nextMove);
			} else {
				this.right = true;
				this.down = this.lastVert;
				this.up = !this.lastVert;
			}
		}
		// System.out.println("Collision? " + checkCollision(getCenterPoint()) +
		// "\nCollision bottom? " + checkCollisionBottom(getCenterPoint()) +
		// "\nCollision top? " + checkCollisionTop(getCenterPoint()) +
		// "\nX = " + getCenterPoint().getX() +
		// "\nY = " + getCenterPoint().getY());

	}

}
