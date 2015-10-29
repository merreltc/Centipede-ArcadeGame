import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LevelChangeListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			// Unused
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// Unused
		}

		@Override
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == 'u')
				loadLevel(getLevel() + 1);
			if (e.getKeyChar() == 'd')
				loadLevel(getLevel() - 1);
		}
	}