import java.awt.Color;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
/**
 * 
 * TODO Put here a description of what this class does.
 *
 * @author Trinity Merrell and Walter Panfil.
 *         Created Oct 28, 2015.
 */

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
			// Do nothing
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// Do nothing
		}
	}
	
	private Weapon currentWeapon;
	

	public Player(World world, Point2D centerPoint) {
		super(world, centerPoint);
		Weapon weapon = new RapidFire(world, this.getCenterPoint());
		this.currentWeapon = weapon;
		ArrowListener move = new ArrowListener();
		//addKeyListener(move);
	}
	
	public void fire() {
		this.currentWeapon.shoot();
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
}
