import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Flea extends Entity {

	private final int VALUE = 500;
	private BufferedImage fleaImage;

	public Flea(World world, Point2D centerPoint) throws IOException {
		super(world, centerPoint);
		this.setCenterPoint(new Point2D.Double(10 + ((int) (Math.random() * 20)) * 20, this.getCenterPoint().getY()));

		// Load Image.
		BufferedImage img = ImageIO.read(getClass().getResource("/Flea.png"));
		this.fleaImage = img;

		this.health = 1;
		this.radius = 10;

	}

	public void spawnMushroom() throws IOException {
		if (Math.random() > .75) {
			Mushroom m = new Mushroom(this.getWorld(), this.getCenterPoint());
			this.getWorld().addEntity(m);
		}
	}

	@Override
	public Color getColor() {
		return Color.PINK;
	}

	@Override
	public BufferedImage getImage() {
		return this.fleaImage;
	}

	@Override
	public void updatePosition() {
		this.setCenterPoint(new Point2D.Double(this.getCenterPoint().getX(), this.getCenterPoint().getY() + 3));

		if (this.getHealth() == 0) {
			this.getWorld().setScore(this.VALUE);
			this.die();
		}

		if (checkCollision(getCenterPoint()) != null
				&& Weapon.class.isAssignableFrom(checkCollision(getCenterPoint()).getClass())) {
			this.takeDamage();
			return;
		}

		if (this.getCenterPoint().getY() % 20 == 10) {
			try {
				this.spawnMushroom();
			} catch (IOException exception) {
				System.out.println("Couldn't spawn Mushroom.");
			}
		}

		if (this.getCenterPoint().getY() > 400) {
			this.die();
		}
	}

	@Override
	public Shape getShape() {
		return null;
	}
}
