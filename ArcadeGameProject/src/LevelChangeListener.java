import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LevelChangeListener implements KeyListener {

	World world;

	public LevelChangeListener(World world) {
		this.world = world;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Do nothing
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("P");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("O");
		int newLevel = this.world.getLevel();
		if (e.getKeyCode() == 85) { // "u" key
			newLevel++;
			this.world.loadLevel(newLevel);
		}
		if (e.getKeyCode() == 68) { // "d" key
			newLevel--;
			this.world.loadLevel(newLevel);
		}
	}
}