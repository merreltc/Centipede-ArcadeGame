import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Scorpion extends Entity{
	
	private int VALUE = 1000;
	
	public Scorpion(World world, Point2D centerPoint) {
		super(world, centerPoint);
		// TODO Auto-generated constructor stub.
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub.
		return Color.WHITE;
	}
	@Override
	public Shape getShape() {
		return new Rectangle2D.Double(this.getCenterPoint().getX() - this.getWorld().CELL_WIDTH / 2,
				this.getCenterPoint().getY() - this.getWorld().CELL_WIDTH / 2, 20, 20);
	}
	@Override
	public void updatePosition() {
		
	}

}
