import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * Represents a spider monster that moves in a random zigzag motion and eats
 * 	 	mushrooms at a rate of 3%.
 *
 * @author merreltc.
 *         Created Nov 12, 2015.
 */
public class Spider extends Entity {
	private final int VALUE = 500;

	private int deltaH;
	private boolean right;
	private BufferedImage spiderImage;

	public Spider(World world, Point2D centerPoint) throws IOException {
		super(world, centerPoint);
		this.health = 1;
		this.radius = 9;
		this.deltaH = 2;
		
		// Load Image.
		BufferedImage img = ImageIO.read(getClass().getResource("/Spider.png"));
		this.spiderImage = img;
		
		if(Math.random() <= 0.5) {
			this.right = true;
			this.setCenterPoint(new Point2D.Double(10, 300));
		} else {
			this.right = false;
			this.setCenterPoint(new Point2D.Double(389, 300));
		}
	}

	@Override
	public Color getColor() {
		return Color.LIGHT_GRAY;
	}

	@Override
	public BufferedImage getImage() {
		return this.spiderImage;
	}

	@Override
	public void updatePosition() {
		if(this.right) {
			this.setCenterPoint(new Point2D.Double(this.getCenterPoint().getX()+2,
					this.getCenterPoint().getY()+this.deltaH));
			if(Math.random() > .9)
				this.deltaH = -this.deltaH;
		} else if (!this.right) {
			this.setCenterPoint(new Point2D.Double(this.getCenterPoint().getX()-2,
					this.getCenterPoint().getY()+this.deltaH));
			if(Math.random() > .9)
				this.deltaH = -this.deltaH;
		}
		
		if (this.getHealth() == 0) {
			this.getWorld().setScore(this.VALUE);
			this.die();
		}
		
		if (checkCollision(getCenterPoint()) != null
				&& Weapon.class.isAssignableFrom(checkCollision(getCenterPoint()).getClass())) {
			this.takeDamage();
			return;
		}
		
		if(checkCollision(getCenterPoint()) != null
				&& checkCollision(getCenterPoint()).getClass().equals(Mushroom.class)) {
			if(Math.random() < 0.03) {
				getWorld().removeEntity(checkCollision(getCenterPoint()));
			}
		}
		
		if(this.getCenterPoint().getX() > 387 || this.getCenterPoint().getX() < 10)
			this.die();
	}

	@Override
	public Shape getShape() {
		return null;
	}
}
