import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;

public class Centipede extends Entity{
	public Centipede(World world, Point2D centerPoint) {
		super(world, centerPoint);
		// TODO Auto-generated constructor stub.
	}

	private final int velocity = 3;
	private int scoreValue = 100;
	
	private int length;
	
	public void split(int segment){
		//not yet
	}
	
	public void turn(int direction){
		//not yet
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
