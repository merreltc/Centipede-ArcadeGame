import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Renders a centipede world on the GUI.
 *
 * @author merreltc. Created Oct 28, 2015.
 */
public class GameComponent extends JComponent {

	private static final int FRAMES_PER_SECOND = 100;
	private static final long REPAINT_INTERVAL_MS = 1000 / FRAMES_PER_SECOND;

	private World world;
	private boolean hasShownNullErrorMessage = false;
	private Entity selectedEntity = null;
	private JLabel scoreLabel;

	/**
	 * Constructs a component for rendering the Centipede environment on the
	 * GUI.
	 * 
	 * @param world
	 */
	public GameComponent(World world) {
		this.world = world;
		setFocusable(true);
		LevelChangeListener levelHandler = new LevelChangeListener(world);
		addKeyListener(levelHandler);
		PlayerListener playerHandler = new PlayerListener(world);
		addKeyListener(playerHandler);

		this.scoreLabel = new JLabel("Score: " + world.getScore() + " Lives: " + 3);
		this.scoreLabel.setBounds(0, 417, 415, 20);
		if (this.world.getPlayer() != null) {
			this.scoreLabel.setText("Score: " + this.world.getScore() + " Lives: " + this.world.getPlayer().getLives());
		}

		// Creates a separate "thread of execution" to trigger periodic
		// repainting of this component.
		Runnable repainter = new Runnable() {
			@Override
			public void run() {
				// Periodically asks Java to repaint this component
				try {
					while (true) {
						Thread.sleep(REPAINT_INTERVAL_MS);
						repaint();
					}
				} catch (InterruptedException exception) {
					// Stop when interrupted
				}
			}
		};
		new Thread(repainter).start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		drawDrawable(g2, this.world);

		List<Drawable> drawableParts = this.world.getDrawableParts();
		for (Drawable d : drawableParts) {
			if(Weapon.class.isAssignableFrom(d.getClass())) {
				drawDrawable(g2,d);
			} else {
				Entity entity = (Entity) d;
				int x = (int) (entity.getCenterPoint().getX() - 10);
				int y = (int) (entity.getCenterPoint().getY() - 10);
				g2.drawImage(d.getImage(), null, x, y);
			}
		}
	}

	/**
	 * This helper method draws the given drawable object on the given graphics
	 * area.
	 * 
	 * @param g2
	 *            the graphics area on which to draw
	 * @param d
	 *            the drawable object
	 */
	private void drawDrawable(Graphics2D g2, Drawable d) {
		Color color = d.getColor();
		if (color == null) {
			showNullPointerMessage("color", d);
			return;
		}
		Shape shape = d.getShape();
		if (shape == null) {
			showNullPointerMessage("shape", d);
			return;
		}
		g2.setColor(color);
		g2.fill(shape);
	}

	/**
	 * Displays an error message explaining that the Entity's color/shape was
	 * null so it could not be drawn. Show the message only once per component.
	 */
	private void showNullPointerMessage(String nullAttribute, Drawable d) {
		if (!this.hasShownNullErrorMessage) {
			this.hasShownNullErrorMessage = true;
			String message = "I could not draw this Drawable object of type " + d.getClass().getName() + " because its "
					+ nullAttribute + " is null.\n";
			JOptionPane.showMessageDialog(null, message, "Null pointer exception", JOptionPane.ERROR_MESSAGE);
		}
	}
}