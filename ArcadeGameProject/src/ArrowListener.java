import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ArrowListener implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 38) {
			// up
		} else if (e.getKeyCode() == 40)

		{
			// down
		} else if (e.getKeyCode() == 37)

		{
			// left
		} else if (e.getKeyCode() == 39)

		{
			// right
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
