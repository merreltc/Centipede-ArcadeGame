import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class LevelChangeListener implements KeyListener {

	World world;

	public LevelChangeListener(World world) {
		this.world = world;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int newLevel = this.world.getLevel();

		if (e.getKeyCode() == 85) { // "u" key
			newLevel++;
			try {
				this.world.loadLevel(newLevel);
			} catch (IOException exception) {
				System.out.println("No files");
			}
		} else if (e.getKeyCode() == 68) { // "d" key
			newLevel--;
			try {
				this.world.loadLevel(newLevel);
			} catch (IOException exception) {
				System.out.println("No files");
			}
		} else if (e.getKeyCode() == 80) { // "p" key
			if (!this.world.getIsPaused()) {
				this.world.setIsPaused(true);
			} else if (this.world.getIsPaused()) {
				this.world.setIsPaused(false);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing
	}
}