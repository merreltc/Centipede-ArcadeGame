import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
	
	private final List<Entity> entities = new ArrayList<>();
	private final List<Entity> entitiesToAdd = new ArrayList<>();
	private final List<Entity> entitiesToRemove = new ArrayList<>();
	private Shape background;
	private boolean isPaused = false;
	private int score;
	private int level;
	private Player player;
	private int levelTime;

	public World() throws IOException {
		this.background = new Rectangle2D.Double(0, 0, 400, 400);
		this.score = 0;
		this.level = 1;
		this.player = new Player(this, new Point2D.Double(9 * this.CELL_WIDTH + this.CELL_WIDTH / 2,
				19 * this.CELL_WIDTH + this.CELL_WIDTH / 2));
		addEntity(this.player);

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
				} catch (InterruptedException | IOException exception) {
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
	public void loadLevel(int levelToLoad) throws IOException {
		this.setIsPaused(true);
		this.levelTime = 0;
		this.entities.clear();

		try {
			Scanner loader = new Scanner(
					new File("C:\\EclipseWorkspaces\\csse220\\ArcadeGameProject\\Level Files\\level" + levelToLoad));
			for (int row = 0; row < 20; row++) {
				for (int column = 0; column < 20; column++) {
					switch (loader.nextInt()) {
					case 1:
						Mushroom m = new Mushroom(this, new Point2D.Double(column * this.CELL_WIDTH + this.CELL_WIDTH / 2,
								row * this.CELL_WIDTH + this.CELL_WIDTH / 2));
						if (Math.random() > 0.85)
							m.setPoisoned(true);
						addEntity(m);
						break;

					case 2:
						addEntity(
								new CentipedeSegment(this, new Point2D.Double(column * this.CELL_WIDTH + this.CELL_WIDTH / 2,
										row * this.CELL_WIDTH + this.CELL_WIDTH / 2), new Centipede(this, true)));
						break;

					case 3:
						this.player = new Player(this, new Point2D.Double(column * this.CELL_WIDTH + this.CELL_WIDTH / 2,
								row * this.CELL_WIDTH + this.CELL_WIDTH / 2));
						addEntity(this.player);
						break;

					case 4:
						addEntity(new Flea(this, new Point2D.Double(column * this.CELL_WIDTH + this.CELL_WIDTH / 2,
								row * this.CELL_WIDTH + this.CELL_WIDTH / 2)));
						break;

					case 5:
						addEntity(new Scorpion(this, new Point2D.Double(column * this.CELL_WIDTH + this.CELL_WIDTH / 2,
								row * this.CELL_WIDTH + this.CELL_WIDTH / 2)));
						break;

					case 6:
						addEntity(new Spider(this, new Point2D.Double(column * this.CELL_WIDTH + this.CELL_WIDTH / 2,
								row * this.CELL_WIDTH + this.CELL_WIDTH / 2)));
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

		if (levelToLoad == 1) {
			Centipede centipede = new Centipede(this, false);
			centipede.addHead(new Point2D.Double(10, 10));
			centipede.addHead(new Point2D.Double(30, 10));
			centipede.addHead(new Point2D.Double(50, 10));
			centipede.addHead(new Point2D.Double(70, 10));
			centipede.addHead(new Point2D.Double(90, 10));
			centipede.addHead(new Point2D.Double(110, 10));
			centipede.addHead(new Point2D.Double(130, 10));
			centipede.addHead(new Point2D.Double(150, 10));
			centipede.addHead(new Point2D.Double(170, 10));
			centipede.addHead(new Point2D.Double(190, 10));
			addEntity(this.player);

		} else if (levelToLoad == 2) {
			Centipede centipede = new Centipede(this, false);
			centipede.addHead(new Point2D.Double(10, 10));
			centipede.addHead(new Point2D.Double(30, 10));
			centipede.addHead(new Point2D.Double(50, 10));
			centipede.addHead(new Point2D.Double(70, 10));
			centipede.addHead(new Point2D.Double(90, 10));
			centipede.addHead(new Point2D.Double(110, 10));
			centipede.addHead(new Point2D.Double(130, 10));
			centipede.addHead(new Point2D.Double(150, 10));
			centipede.addHead(new Point2D.Double(170, 10));

			Centipede head = new Centipede(this, true);
			head.addHead(new Point2D.Double(190, 10));
			
			addEntity(this.player);

		} else if (levelToLoad == 3) {
			Centipede centipede = new Centipede(this, false);
			centipede.addHead(new Point2D.Double(30, 10));
			centipede.addHead(new Point2D.Double(50, 10));
			centipede.addHead(new Point2D.Double(70, 10));
			centipede.addHead(new Point2D.Double(90, 10));
			centipede.addHead(new Point2D.Double(110, 10));
			centipede.addHead(new Point2D.Double(130, 10));
			centipede.addHead(new Point2D.Double(150, 10));
			centipede.addHead(new Point2D.Double(170, 10));

			Centipede head1 = new Centipede(this, true);
			head1.addHead(new Point2D.Double(190, 10));

			Centipede head2 = new Centipede(this, false);
			head2.addHead(new Point2D.Double(210, 10));
			
			addEntity(this.player);
		} else if (levelToLoad == 4) {
			Centipede centipede = new Centipede(this, false);
			centipede.addHead(new Point2D.Double(30, 10));
			centipede.addHead(new Point2D.Double(50, 10));
			centipede.addHead(new Point2D.Double(70, 10));
			centipede.addHead(new Point2D.Double(90, 10));
			centipede.addHead(new Point2D.Double(110, 10));
			centipede.addHead(new Point2D.Double(130, 10));
			centipede.addHead(new Point2D.Double(150, 10));
			centipede.addHead(new Point2D.Double(170, 10));
			centipede.addHead(new Point2D.Double(190, 10));
			centipede.addHead(new Point2D.Double(210, 10));
			centipede.addHead(new Point2D.Double(210, 30));
			centipede.addHead(new Point2D.Double(190, 30));
			centipede.addHead(new Point2D.Double(170, 30));
			centipede.addHead(new Point2D.Double(150, 30));
			centipede.addHead(new Point2D.Double(130, 30));
			centipede.addHead(new Point2D.Double(110, 30));
			centipede.addHead(new Point2D.Double(90, 30));
			centipede.addHead(new Point2D.Double(70, 30));
			centipede.addHead(new Point2D.Double(50, 30));
			centipede.addHead(new Point2D.Double(30, 30));
			addEntity(this.player);
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
	public void timePassed() throws IOException {
		if (!this.isPaused) {
			if(Math.random() < .001 && this.getLevel() != 4)
				this.addEntity(new Scorpion(this, new Point2D.Double(0, 0)));
			if(Math.random() < .005  && this.getLevel() != 4)
				this.addEntity(new Spider(this, new Point2D.Double(0,0)));
			this.entities.removeAll(this.entitiesToRemove);
			this.entitiesToRemove.clear();
			this.entities.addAll(this.entitiesToAdd);
			this.entitiesToAdd.clear();

			boolean centipedesRemain = false;
			boolean flea = false;
			int m = 0;
			for (Temporal t : this.entities) {
				t.timePassed();
				if (t.getClass().equals(CentipedeSegment.class))
					centipedesRemain = true;
				if(t.getClass().equals(Mushroom.class) && ((Entity) t).getCenterPoint().getY()>300)
					m++;
				if(t.getClass().equals(Flea.class))
					flea = true;
			}
			if(this.player != null && this.player.health == 0) {
				loadLevel(this.level);
				this.player.setHealth(1);
			}
			if(m <= 5 && !flea  && this.getLevel() != 4)
				this.addEntity(new Flea(this, new Point2D.Double(0, 0)));
			if(!centipedesRemain && this.levelTime >= 5) {
				this.loadLevel(this.getLevel()+1);
			}
			this.levelTime++;
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
	public BufferedImage getImage() {
		return null;
	}
	
	@Override
	public Shape getShape() {
		return this.background;
	}
}
