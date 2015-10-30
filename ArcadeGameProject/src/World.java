import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Creates the game board, handles level change, and addition/removal of
 * entities.
 *
 * @author Trinity Merrell and Walter Panfil. Created Oct 28, 2015.
 */
public class World implements Drawable, Temporal {
	protected static final long UPDATE_INTERVAL_MS = 5;
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

	public World() {
		this.background = new Rectangle2D.Double(0, 0, 400, 400);
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
	public void loadLevel(int levelToLoad) {
		this.setIsPaused(true);
		this.entities.clear();

		try {
			Scanner loader = new Scanner(
					new File("C:\\EclipseWorkspaces\\csse220\\ArcadeGameProject\\Level Files\\level" + levelToLoad));
			for (int y = 0; y < 20; y++) {
				for (int x = 0; x < 20; x++) {
					switch (loader.nextInt()) {
					case 1:
						addEntity(new Mushroom(this, new Point2D.Double(x * this.CELL_WIDTH, y * this.CELL_WIDTH)));
						break;

					case 2:
						addEntity(new Centipede(this, new Point2D.Double(x * this.CELL_WIDTH, y * this.CELL_WIDTH)));
						break;

					case 3:
						addEntity(new Player(this, new Point2D.Double(x * this.CELL_WIDTH, y * this.CELL_WIDTH)));
						break;

					default:
					}
				}
				loader.nextLine();
			}
			loader.close();
		} catch (FileNotFoundException e) {
			addEntity(new Player(this, new Point2D.Double()));
		}
		this.setIsPaused(false);
	}

	public int getLevel() {
		return this.level;
	}

	/**
	 * Will reset score or augment score by a given value.
	 * 
	 * @param value
	 *            Either 0 or an additional value.
	 */
	public void setScore(int value) {
		if (value == 0) {
			this.score = 0;
		} else {
			this.score += value;
		}
	}

	public int getScore() {
		return this.score;
	}

	@Override
	public void timePassed() {
		if (!this.isPaused) {
			for (Temporal t : this.entities) {
				t.timePassed();
			}
			this.entities.removeAll(this.entitiesToRemove);
			this.entitiesToRemove.clear();
			this.entities.addAll(this.entitiesToAdd);
			this.entitiesToAdd.clear();
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

	@Override
	public void updatePosition() {
		// Do something

	}

}
