import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Point2D;

public class Flea extends Entity{
	
	private final int VALUE = 500;
	
	public Flea(World world, Point2D centerPoint) {
		super(world, centerPoint);
		
	}
	
	public void spawnMushroom(){
		if(Math.random() > .75){
			Mushroom m = new Mushroom(this.getWorld(), this.getCenterPoint());
			this.getWorld().addEntity(m);
		}
	}

	@Override
	public Color getColor() {
		return Color.PINK;
	}

	@Override
	public Shape getShape() {
		return new Polygon(
				new int[] { (int) getCenterPoint().getX() - this.getWorld().CELL_WIDTH / 2,
						(int) getCenterPoint().getX(), (int) getCenterPoint().getX() + this.getWorld().CELL_WIDTH / 2 },
				new int[] { (int) getCenterPoint().getY() - this.getWorld().CELL_WIDTH / 2,
						(int) getCenterPoint().getY() + this.getWorld().CELL_WIDTH / 2,
						(int) getCenterPoint().getY() - this.getWorld().CELL_WIDTH / 2 },
				3);
	}

	@Override
	public void updatePosition() {
		this.setCenterPoint(new Point2D.Double(this.getCenterPoint().getX(), this.getCenterPoint().getY()+5));
		if(this.checkCollision(getCenterPoint()) != null && Weapon.class.isAssignableFrom(checkCollision(getCenterPoint()).getClass()))
			this.die();
		if(this.getCenterPoint().getY() % 20 == 10)
			this.spawnMushroom();
		if(this.getCenterPoint().getY() > 400)
			this.die();
	}
}

