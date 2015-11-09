import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * The main class for your arcade game.
 * 
 * You can design your game any way you like, but make the game start
 * by running main here.
 * 
 * Also don't forget to write javadocs for your classes and functions!
 * 
 * @author Trinity Merrell and Walter Panfil.
 *         Created Oct 28, 2015. 
 *
 */
public class Main extends JFrame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Centipede");

		World world = new World();
		GameComponent component = new GameComponent(world);
		world.loadLevel(1);
		frame.add(component);
//	JPanel scoreAndLives = new JPanel();
//	scoreAndLives.setBackground(Color.BLACK);
//	JLabel scoreLives = new JLabel("Score: " + world.getScore() + " Lives: " + world.getPlayer().getLives());
//	scoreLives.setBackground(Color.BLACK);
//	JPanel.paintComponent(g);
//	scoreAndLives.add(scoreLives);
//	frame.add(scoreAndLives, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(417, 471));
		frame.setVisible(true);
	}

}
