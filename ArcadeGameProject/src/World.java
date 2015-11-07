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
	protected static final long UPDATE_INTERVAL_MS = 10;
	public final int CELL_WIDTH = 20;
	public final int ROWS = 20;
	public final int COLUMNS = 20;
	public final int PLAYER_REGION_BOUND = 300;
	private Color BG_COLOR = Color.BLACK;
	private final List<Entity> entities = new ArrayList<Entity>();
	private final List<Entity> entitiesToAdd = new ArrayList<Entity>();
	private final List<Entity> entitiesToRemove = new ArrayList<Entity>();
	
	private Shape background;
	private boolean isPaused = false;
	private int score;
	private int level;
	private Player player;

	public World() {
		this.background = new Rectangle2D.Double(0, 0, 400, 400);
		this.score = 0;
		this.level = 1;

		// Creates a separate "thread of execution" to inform this world of the
		// passage of time.
		Runnable r1 = new Runnable() {
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
		new Thread(r1).start();
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
			for (int row = 0; row < 20; row++) {
				for (int column = 0; column < 20; column++) {
					switch (loader.nextInt()) {
					case 1:
						addEntity(new Mushroom(this, new Point2D.Double(column * this.CELL_WIDTH + CELL_WIDTH/2 - 1, row * this.CELL_WIDTH + CELL_WIDTH/2 - 1)));
						break;

					case 2:
						addEntity(new Centipede(this, new Point2D.Double(column * this.CELL_WIDTH + CELL_WIDTH/2 - 1, row * this.CELL_WIDTH + CELL_WIDTH/2 - 1)));
						break;

					case 3:
						this.player = new Player(this, new Point2D.Double(column * this.CELL_WIDTH + CELL_WIDTH/2 - 1, row * this.CELL_WIDTH + CELL_WIDTH/2 - 1));
						addEntity(this.player);
						break;

					default:
					}
				}
				loader.nextLine();
			}
			loader.close();
			this.level = levelToLoad;
		} catch (FileNotFoundException e) {
			loadLevel(this.level);
		}
		this.setIsPaused(false);
	}

	public int getLevel() {
		return this.level;
	}
	
	public Player getPlayer() {
		return this.player;
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
