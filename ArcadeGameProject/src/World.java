import java.awt.Color;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JComponent;

/**
 * Creates the game board, handles level change, and addition/removal of entities.
 *
 * @author Trinity Merrell and Walter Panfil.
 *         Created Oct 28, 2015.
 */
public class World extends JComponent implements Drawable, Temporal, Runnable {
	private final int CELL_WIDTH = 20;
	private final int ROWS = 20;
	private final int COLUMNS = 20;
	private final int PLAYER_REGION_BOUND = 300;
	private Color BG_COLOR = Color.BLACK;
	
	private Shape background;
	private boolean isPaused;
	private int score;
	private int level;
	
	public class LevelChangeListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			//Unused
		}

		@Override
		public void keyReleased(KeyEvent e) {
			//Unused
		}

		@Override
		public void keyTyped(KeyEvent e) {
			if(e.getKeyChar()=='u')
				loadLevel(getLevel()+1);
			if(e.getKeyChar()=='d')
				loadLevel(getLevel()-1);
		}
		
	}

	public World() {
		
	}
	
	/**
	 * Adds the given entity to this world.
	 * 
	 * @param entity
	 *            the entity to add, must not be null
	 */
	void addEntity(Entity entity) {
		
	}

	/**
	 * Removes the given entity from this world, if it is in the world at all.
	 * Otherwise silently does nothing.
	 * 
	 * @param entity
	 *            the entity to remove
	 */
	void removeEntity(Entity entity) {
		
	}

	/**
	 * Returns a list of the drawable parts in this environment.
	 * 
	 * @return a possibly-empty list
	 */
	public List<Drawable> getDrawableParts() {
		return null;
		
	}

	/**
	 * Loads a predesigned level from a file.
	 */
	public void loadLevel(int level) {
		//FIX MEEEEE
	}
	
	public int getLevel() {
		return this.level;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub.
		
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
	public Color getColor() {
		// TODO Auto-generated method stub.
		return null;
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub.
		return null;
	}
	
	
}
