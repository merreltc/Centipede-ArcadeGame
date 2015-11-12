/**
 * Represents a centipede object
 * 
 * @author Trinity Merrell and Walter Panfil. Created Oct 28, 2015.
 */
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

public class Centipede {

	private boolean moveRight;
	private ArrayList<CentipedeSegment> centipede;
	private World world;

	public Centipede(World world, boolean moveRight) {
		this.centipede = new ArrayList<>();
		this.world = world;
		this.moveRight = moveRight;
	}
	
	/**
	 * 
	 * Handles splitting the centipede into two new centipedes.
	 *
	 * @param segment segment at which to split the centipede
	 * @throws IOException
	 */

	public synchronized void split(CentipedeSegment segment) throws IOException {
		int index = this.centipede.indexOf(segment);
		this.world.addEntity(new Mushroom(this.world, segment.getCenterPoint()));
		Centipede firstHalf = new Centipede(this.world, true);
		Centipede secondHalf = new Centipede(this.world, false);
		
		for (int k = 0; k < index; k++) {
			firstHalf.addHead(this.centipede.get(k).getCenterPoint());
			this.centipede.get(k).die();
		}
		for (int k = index + 1; k < this.centipede.size(); k++) {
			secondHalf.addHead(this.centipede.get(k).getCenterPoint());
			this.world.removeEntity(this.centipede.get(k));
		}
	}
	
	/**
	 * 
	 * Adds an additional head to the centipede.
	 *
	 * @param centerPoint
	 * @throws IOException
	 */
	public void addHead(Point2D centerPoint) throws IOException {
		CentipedeSegment segment = new CentipedeSegment(this.world, centerPoint, this);
		this.centipede.add(segment);
		this.centipede.get(this.centipede.size()-1).setDirection(this.moveRight);
		this.world.addEntity(segment);
	}
	
	public ArrayList<CentipedeSegment> getList() {
		return this.centipede;
	}
	
	
}