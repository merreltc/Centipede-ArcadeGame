import java.awt.Color;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;

public class Player extends Entity {
	
	public class ArrowListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == 38) {
				//up
			} else if(e.getKeyCode() == 40) {
				//down
			} else if(e.getKeyCode() == 37) {
				//left
			} else if (e.getKeyCode() == 39) {
				//right
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub.
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub.
			
		}
		
	}
	
	private Weapon currentWeapon;
	

	public Player(World world, Point2D centerPoint) {
		super(world, centerPoint);
		Weapon weapon = new RapidFire(world, this.getCenterPoint());
		this.currentWeapon = weapon;
	}
	
	public void fire() {
		
	}

	@Override
	public void updatePosition() {

	}

	@Override
	public void checkCollision(Entity entity) {
		// TODO Auto-generated method stub.

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
}
