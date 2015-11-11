import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Point2D;

public class Spider extends Entity {
	private final int VELOCITY = 3;
	private final int VALUE = 500;

	private int deltaH;
	private boolean right;

	public Spider(World world, Point2D centerPoint) {
		super(world, centerPoint);
		this.health = 1;
		this.radius = 9;
		this.deltaH = 2;
		if(Math.random() <= 0.5) {
			this.right = true;
			this.setCenterPoint(new Point2D.Double(0, 300));
		} else {
			this.right = false;
			this.setCenterPoint(new Point2D.Double(400, 300));
		}
	}

	@Override
	public Color getColor() {
		return Color.LIGHT_GRAY;
	}

	@Override
	public Shape getShape() {
		return new Polygon(
				new int[] { (int) getCenterPoint().getX() - this.getWorld().CELL_WIDTH / 2,
						(int) getCenterPoint().getX(), (int) getCenterPoint().getX() + this.getWorld().CELL_WIDTH / 2 },
				new int[] { (int) getCenterPoint().getY() + this.getWorld().CELL_WIDTH / 2,
						(int) getCenterPoint().getY() - this.getWorld().CELL_WIDTH / 2,
						(int) getCenterPoint().getY() + this.getWorld().CELL_WIDTH / 2 },
				3);
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
					this.getCenterPoint().getY()));
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
			if(Math.random() < 0.07) {
				getWorld().removeEntity(checkCollision(getCenterPoint()));
			}
		}
		
		if(this.getCenterPoint().getX() > 387 || this.getCenterPoint().getX() < 10)
			this.die();
	}
}
