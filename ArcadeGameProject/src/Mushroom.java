import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Mushroom extends Entity {
	private boolean poisoned;

	public Mushroom(World world, Point2D centerPoint) {
		super(world, centerPoint);
		this.radius = 9;
		this.health = 4;
		this.poisoned = false;
	}

	public boolean isPoisoned() {
		return this.poisoned;
	}

	public void setPoisoned(boolean poisoned) {
		this.poisoned = poisoned;
	}

	@Override
	public Color getColor() {
		int poisonColor = 0;
		if(this.poisoned) {
			poisonColor = 200;
		}
		return new Color(255 / this.getHealth(),poisonColor, 255 / (5 - this.getHealth()));
	}

	@Override
	public Shape getShape() {
		return new Ellipse2D.Double(this.getCenterPoint().getX() - this.getWorld().CELL_WIDTH / 2,
				this.getCenterPoint().getY() - this.getWorld().CELL_WIDTH / 2, 20, 20);
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
}
