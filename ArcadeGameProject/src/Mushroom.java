import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * Represents a mushroom that can be poisonous and killed after 4 bullet shots.
 *
 * @author Trinity Merrell and Walter Panfil.
 *         Created Nov 12, 2015.
 */
public class Mushroom extends Entity {
	private boolean poisoned;
	private BufferedImage mushImage4;
	private BufferedImage mushImage3;
	private BufferedImage mushImage2;
	private BufferedImage mushImage1;
	private BufferedImage pMushImage4;
	private BufferedImage pMushImage3;
	private BufferedImage pMushImage2;
	private BufferedImage pMushImage1;

	public Mushroom(World world, Point2D centerPoint) throws IOException {
		super(world, centerPoint);
		this.radius = 9;
		this.health = 4;
		this.poisoned = false;
		
		// Load Image.
		BufferedImage img1 = ImageIO.read(getClass().getResource("/Mushroom.png"));
		this.mushImage4 = img1;
		
		// Load Image.
		BufferedImage img2 = ImageIO.read(getClass().getResource("/Mushroom2.png"));
		this.mushImage3 = img2;
		
		// Load Image.
		BufferedImage img3 = ImageIO.read(getClass().getResource("/Mushroom3.png"));
		this.mushImage2 = img3;
		
		// Load Image.
		BufferedImage img4 = ImageIO.read(getClass().getResource("/Mushroom4.png"));
		this.mushImage1 = img4;
		
		// Load Image.
		BufferedImage img5 = ImageIO.read(getClass().getResource("/PMushroom.png"));
		this.pMushImage4 = img5;
		
		// Load Image.
		BufferedImage img6 = ImageIO.read(getClass().getResource("/PMushroom2.png"));
		this.pMushImage3 = img6;
		
		// Load Image.
		BufferedImage img7 = ImageIO.read(getClass().getResource("/PMushroom3.png"));
		this.pMushImage2 = img7;
		
		// Load Image.
		BufferedImage img8 = ImageIO.read(getClass().getResource("/PMushroom4.png"));
		this.pMushImage1 = img8;
		
		
	}

	public boolean isPoisoned() {
		return this.poisoned;
	}

	public void setPoisoned(boolean poisoned) {
		this.poisoned = poisoned;
	}

	@Override
	public Color getColor() {
		if(this.getHealth()==0)
			return Color.BLACK;
		int poisonColor = 0;
		if(this.poisoned) {
			poisonColor = 200;
		}
		return new Color(255 / this.getHealth(),poisonColor, 255 / (5 - this.getHealth()));
	}

	@Override
	public BufferedImage getImage() {
		if(!this.poisoned) {
			if(this.health == 4) {
				return this.mushImage4;
			} else if (this.health == 3) {
				return this.mushImage3;
			} else if (this.health == 2) {
				return this.mushImage2;
			} else if (this.health == 1) {
				return this.mushImage1;
			}
		} else {
			if(this.health == 4) {
				return this.pMushImage4;
			} else if (this.health == 3) {
				return this.pMushImage3;
			} else if (this.health == 2) {
				return this.pMushImage2;
			} else if (this.health == 1) {
				return this.pMushImage1;
			}
		}
		return null;
	}

	@Override
	public void updatePosition() {
		if (checkCollision(getCenterPoint()) != null
				&& Weapon.class.isAssignableFrom(checkCollision(getCenterPoint()).getClass())) {
			this.takeDamage();
		}
		if (this.getHealth() == 0) {
			this.getWorld().setScore(10);
			die();
		}
	}

	@Override
	public Shape getShape() {
		return null;
	}
}
