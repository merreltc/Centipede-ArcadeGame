import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerListener implements KeyListener {
	private World world;

	public PlayerListener(World world) {
		this.world = world;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 38) { // moves up
			this.world.getPlayer().up(true);
		} else if (e.getKeyCode() == 40) { // moves down
			this.world.getPlayer().down(true);
		} else if (e.getKeyCode() == 37) { // moves left
			this.world.getPlayer().left(true);
		} else if (e.getKeyCode() == 39) { // moves right
			this.world.getPlayer().right(true);
		} else if (e.getKeyCode() == 90) { // shoots bullet
			this.world.getPlayer().fire();
		} else if (e.getKeyCode() == 49) { // shoots bullet
			this.world.getPlayer().setCurrentWeapon(1);
		} else if (e.getKeyCode() == 50) { // shoots bullet
			this.world.getPlayer().setCurrentWeapon(2);
		} else if (e.getKeyCode() == 51) { // shoots bullet
			this.world.getPlayer().setCurrentWeapon(3);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 38) { // moves up
			this.world.getPlayer().up(false);
		} else if (e.getKeyCode() == 40) { // moves down
			this.world.getPlayer().down(false);
		} else if (e.getKeyCode() == 37) { // moves left
			this.world.getPlayer().left(false);
		} else if (e.getKeyCode() == 39) { // moves right
			this.world.getPlayer().right(false);
			}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing
	}
}
