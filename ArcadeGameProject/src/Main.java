import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Creates all the necessary components of the game including a JFrame,
 * 		JLabel for score and lives, GameComponent for handling the GUI,
 * 		and World to handle the components of the centipede world
 * 
 * @author Trinity Merrell and Walter Panfil.
 *         Created Oct 28, 2015. 
 *
 */

public class Main extends JFrame {
	/*
	public static class StartGameListener implements ActionListener{
		private JFrame frame;
		
		public StartGameListener(JFrame frame){
			this.frame = frame;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub.
			//frame.removeAll();
			
		}
		
	}
*/
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame();
		frame.setTitle("Centipede");
		//JButton start = new JButton("Start Game!");
		//start.addActionListener(new StartGameListener(frame));
		//frame.add(start, BorderLayout.NORTH);
		World world = new World();
		GameComponent component = new GameComponent(world);
		frame.add(component);
		JLabel scoreLabel = new JLabel();
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.add(scoreLabel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(417, 463));
		world.startGame();
		frame.setVisible(true);
		while(true){
			scoreLabel.setText("Score: " + world.getScore() + "    Lives: " + world.getPlayer().getLives());
		}
	}
}
