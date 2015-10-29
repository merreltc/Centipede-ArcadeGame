import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;

public class Mushroom extends Entity {
	private boolean poisoned;

	public Mushroom(World world, Point2D centerPoint) {
		super(world, centerPoint);
		this.poisoned = false;
		// TODO Auto-generated constructor stub.
	}
	
	public boolean isPoisoned(){
		return poisoned;
	}
	
	public void setPoisoned(boolean poisoned){
		this.poisoned = poisoned;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub.
		return null;
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub.
		return null;
	}

	@Override
	public void timePassed() {
		// TODO Auto-generated method stub.

	}

	@Override
	public void die() {
		// TODO Auto-generated method stub.

	}

	@Override
	public void setIsPaused(boolean isPaused) {
		// TODO Auto-generated method stub.

	}

	@Override
	public boolean getIsPaused() {
		// TODO Auto-generated method stub.
		return false;
	}

	@Override
	public void updatePosition() {
		// TODO Auto-generated method stub.

	}

	@Override
	public void checkCollision(Entity entity) {
		// TODO Auto-generated method stub.

	}

}
