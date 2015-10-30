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
			this.world.getPlayer().move(1);
		} else if (e.getKeyCode() == 40) { // moves down
			this.world.getPlayer().move(2);
		} else if (e.getKeyCode() == 37) { // moves left
			this.world.getPlayer().move(3);
		} else if (e.getKeyCode() == 39) { // moves right
			this.world.getPlayer().move(4);
		} else if (e.getKeyCode() == 90) { // shoots bullet
			this.world.getPlayer().fire();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing
	}
}
