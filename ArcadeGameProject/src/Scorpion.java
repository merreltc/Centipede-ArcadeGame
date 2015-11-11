import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Scorpion extends Entity{
	
	private int VALUE = 1000;
	
	public Scorpion(World world, Point2D centerPoint) {
		super(world, centerPoint);
		this.setCenterPoint(new Point2D.Double(this.getCenterPoint().getX(), 10+((int)(Math.random()*15))*20));
		this.health = 1;
		this.radius = 9;
	}

	@Override
	public Color getColor() {
		return Color.WHITE;
	}
	@Override
	public Shape getShape() {
		return new Rectangle2D.Double(this.getCenterPoint().getX() - this.getWorld().CELL_WIDTH / 2,
				this.getCenterPoint().getY() - this.getWorld().CELL_WIDTH / 2, 20, 20);
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

}
