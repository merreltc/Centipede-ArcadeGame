import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * 
 * Represents a segment of a centipede
 *
 * @author merreltc.
 *         Created Nov 12, 2015.
 */
public class CentipedeSegment extends Entity {

	private final int VALUE = 100;
	private Color color;
	private boolean right, down, up, lastVert, poisoned;
	private Centipede centipede;
	private BufferedImage headLImage;
	private BufferedImage headRImage;
	private BufferedImage segmentImage;

	public CentipedeSegment(World world, Point2D centerPoint, Centipede centipede) throws IOException {
		super(world, centerPoint);
		this.centipede = centipede;
		this.color = Color.MAGENTA;
		this.radius = 9;
		this.health = 1;
		this.right = false;
		this.down = false;
		this.up = false;
		this.poisoned = false;
		this.lastVert = true; // true = down, false = up
		// System.out.println("X: " + centerPoint.getX() + "Y: " +
		// centerPoint.getY());

		// Load Image.
		BufferedImage img = ImageIO.read(getClass().getResource("/Segment.png"));
		this.segmentImage = img;

		// Load Image.
		BufferedImage img1 = ImageIO.read(getClass().getResource("/HeadL.png"));
		this.headLImage = img1;
		
		// Load Image.
		BufferedImage img2 = ImageIO.read(getClass().getResource("/HeadR.png"));
		this.headRImage = img2;
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public BufferedImage getImage() {
		if (this.centipede != null &&
				this.centipede.getList().indexOf(this) == 0) {
			
			if(this.right) {
				return this.headRImage;
			} else if(!this.right) {
				return this.headLImage;
			}
		}
		return this.segmentImage;
	}

	public void setDirection(boolean right) {
		this.right = right;
	}

	@Override
	public void updatePosition() {
		if (this.getHealth() == 0) {
			this.getWorld().setScore(this.VALUE);
			this.die();
		}

		if (checkCollision(getCenterPoint()) != null
				&& Weapon.class.isAssignableFrom(checkCollision(getCenterPoint()).getClass())) {
			this.takeDamage();
			try {
				this.centipede.split(this);
			} catch (IOException exception) {
				System.out.println("Couldn't split centipede");
			}
			return;
		}

		Point2D nextMove;

		if (this.down && this.lastVert && !getIsPaused()) { // Go down
			nextMove = new Point2D.Double(this.getCenterPoint().getX(), this.getCenterPoint().getY() + 20);
			if (nextMove.getY() <= 405) {
				setCenterPoint(nextMove);
			} else if (nextMove.getY() >= 405) {
				this.up = true;
				this.lastVert = !this.lastVert;
			}
			if (!this.poisoned)
				this.down = false;
			else if (nextMove.getY() >= 300)
				this.poisoned = false;
		} else if (this.up && !this.lastVert && !getIsPaused()) { // Go up
			nextMove = new Point2D.Double(this.getCenterPoint().getX(), this.getCenterPoint().getY() - 20);

			if (nextMove.getY() >= 10) {
				setCenterPoint(nextMove);
			} else if (nextMove.getY() <= 10) {
				this.down = true;
				this.lastVert = !this.lastVert;
			}
			this.up = false;
			this.poisoned = false;
		} else if (this.right && !getIsPaused()) { // Go right
			nextMove = new Point2D.Double(this.getCenterPoint().getX() + 1, this.getCenterPoint().getY());
			if ((checkCollision(nextMove) == null || !checkCollision(nextMove).getClass().equals(Mushroom.class))
					&& nextMove.getX() <= 391) {
				setCenterPoint(nextMove);
			} else if (checkCollision(nextMove) != null && checkCollision(nextMove).getClass().equals(Player.class)) {
				setCenterPoint(nextMove);
			} else {
				if (checkCollision(nextMove) != null &&
						checkCollision(nextMove).getClass().equals(Mushroom.class)
						&& ((Mushroom) checkCollision(nextMove)).isPoisoned())
					this.poisoned = true;
				this.right = false;
				this.down = this.lastVert;
				this.up = !this.lastVert;
			}
		} else if (!this.right && !getIsPaused()) { // Go left
			nextMove = new Point2D.Double(this.getCenterPoint().getX() - 1, this.getCenterPoint().getY());
			if ((checkCollision(nextMove) == null || !checkCollision(nextMove).getClass().equals(Mushroom.class))
					&& nextMove.getX() >= 10) {
				setCenterPoint(nextMove);
			} else if (checkCollision(nextMove) != null && checkCollision(nextMove).getClass().equals(Player.class)) {
				setCenterPoint(nextMove);
			} else {
				if (checkCollision(nextMove) != null && checkCollision(nextMove).getClass().equals(Mushroom.class)
						&& ((Mushroom) checkCollision(nextMove)).isPoisoned())
					this.poisoned = true;
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

	@Override
	public Shape getShape() {
		return null;
	}
}
