import java.awt.Dimension;
import java.io.IOException;

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
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame();
		frame.setTitle("Centipede");

		World world = new World();
		GameComponent component = new GameComponent(world);
		world.loadLevel(1);
		frame.add(component);
//		frame.add(scoreLabel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(417, 463));
		frame.setVisible(true);
	}

}
