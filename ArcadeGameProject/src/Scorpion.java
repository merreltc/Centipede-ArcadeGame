import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Scorpion extends Entity{
	
	private int VALUE = 1000;
	private BufferedImage scorpionImage;
	
	public Scorpion(World world, Point2D centerPoint) throws IOException {
		super(world, centerPoint);
		
		// Load Image.
		BufferedImage img = ImageIO.read(getClass().getResource("/Scorpion.png"));
		this.scorpionImage = img;
		
		this.setCenterPoint(new Point2D.Double(this.getCenterPoint().getX(), 10+((int)(Math.random()*15))*20));
		this.health = 1;
		this.radius = 9;
	}

	@Override
	public Color getColor() {
		return Color.WHITE;
	}
	@Override
	public BufferedImage getImage() {
		return this.scorpionImage;
	}
	@Override
	public void updatePosition() {
		this.setCenterPoint(new Point2D.Double(this.getCenterPoint().getX() + 2, this.getCenterPoint().getY()));
		
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
			((Mushroom) checkCollision(getCenterPoint())).setPoisoned(true);
		}
		
		if(this.getCenterPoint().getX() > 387)
			this.die();
	}

	@Override
	public Shape getShape() {
		return null;
	}

}
