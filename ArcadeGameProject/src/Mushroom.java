import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Mushroom extends Entity {
	private boolean poisoned;

	public Mushroom(World world, Point2D centerPoint) {
		super(world, centerPoint);
		this.health = 4;
		this.poisoned = false;
	}
	
	public boolean isPoisoned(){
		return poisoned;
	}
	
	public void setPoisoned(boolean poisoned){
		this.poisoned = poisoned;
	}

	@Override
	public Color getColor() {
		return new Color(255/this.getHealth(), 255/(5-this.getHealth()), 0);
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub.
		return new Ellipse2D.Double(this.getCenterPoint().getX() - this.getWorld().CELL_WIDTH/2, this.getCenterPoint().getY() - this.getWorld().CELL_WIDTH/2, 20, 20);
	}

	@Override
	public void timePassed() {
		if(this.checkCollision() != null && this.checkCollision().getClass().equals(RapidFire.class)){
			this.takeDamage();
		}
		if(this.getHealth()==0)
			this.die();
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
}
