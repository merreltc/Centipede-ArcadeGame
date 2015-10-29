import java.awt.Color;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates the game board, handles level change, and addition/removal of
 * entities.
 *
 * @author Trinity Merrell and Walter Panfil.
 *         Created Oct 28, 2015.
 */
public class World implements Drawable, Temporal {
	protected static final long UPDATE_INTERVAL_MS = 10;
	private final int CELL_WIDTH = 20;
	private final int ROWS = 20;
	private final int COLUMNS = 20;
	private final int PLAYER_REGION_BOUND = 300;
	private Color BG_COLOR = Color.BLACK;

	private Shape background;
	private boolean isPaused = false;
	private int score;
	private int level;
	private final List<Entity> entities = new ArrayList<Entity>();
	private final List<Entity> entitiesToAdd = new ArrayList<Entity>();
	private final List<Entity> entitiesToRemove = new ArrayList<Entity>();

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

	public World() {
		this.background = new Rectangle2D.Double(0, 0, this.CELL_WIDTH * this.ROWS, this.CELL_WIDTH * this.COLUMNS);
		this.score = 0;
		this.level = 1;
		
		// Creates a separate "thread of execution" to inform this world of the
		// passage of time.
		Runnable tickTock = new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						Thread.sleep(UPDATE_INTERVAL_MS);
						timePassed();
					}
				} catch (InterruptedException exception) {
					// Stop when interrupted
				}
			}
		};
		new Thread(tickTock).start();
	}

	/**
	 * Adds the given entity to this world.
	 * 
	 * @param entity
	 *            the entity to add, must not be null
	 */
	public synchronized void addEntity(Entity entity) {
		this.entitiesToAdd.add(entity);
	}

	/**
	 * Removes the given entity from this world, if it is in the world at all.
	 * Otherwise silently does nothing.
	 * 
	 * @param entity
	 *            the entity to remove
	 * @return 
	 */
	public synchronized void removeEntity(Entity entity) {
		this.entitiesToRemove.add(entity);
	}

	/**
	 * Returns a list of the drawable parts in this environment.
	 * 
	 * @return a possibly-empty list
	 */
	public List<Drawable> getDrawableParts() {
		return new ArrayList<Drawable>(this.entities);

	}

	/**
	 * Loads a predesigned level from a file.
	 */
	public void loadLevel(int level) {
		// FIX MEEEEE
	}

	public int getLevel() {
		return this.level;
	}

	@Override
	public void timePassed() {
		if (!this.isPaused) {
			for (Temporal t : this.entities) {
				t.timePassed();
			}
		}
		this.entities.removeAll(this.entitiesToRemove);
		this.entitiesToRemove.clear();
		this.entities.addAll(this.entitiesToAdd);
		this.entitiesToAdd.clear();
	}

	@Override
	public void die() {
		// Do nothing

	}

	@Override
	public void setIsPaused(boolean isPaused) {
		this.isPaused = isPaused;

	}

	@Override
	public boolean getIsPaused() {
		return this.isPaused;
	}

	@Override
	public Color getColor() {
		return this.BG_COLOR;
	}

	@Override
	public Shape getShape() {
		return this.background;
	}

}
