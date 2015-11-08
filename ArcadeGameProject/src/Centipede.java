import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.LinkedList;

public class Centipede {

	private Color color;
	private boolean right, down, up, lastVert, moveRight;
	private LinkedList<CentipedeSegment> centipede;
	private World world;

	public Centipede(World world, boolean moveRight) {
		this.centipede = new LinkedList<CentipedeSegment>();
		this.world = world;
		this.moveRight = moveRight;
	}

	public void split(CentipedeSegment segment) {
		int index = this.centipede.indexOf(segment);
		Centipede firstHalf = new Centipede(this.world, !this.moveRight);
		Centipede secondHalf = new Centipede(this.world, this.moveRight);
		for (int k = 0; k < index; k++) {
			firstHalf.addHead(this.centipede.get(k).getCenterPoint());
		}
		for (int k = index + 1; k < this.centipede.size(); k++) {
			secondHalf.addHead(this.centipede.get(k).getCenterPoint());
		}
		die();
	}

	public void addHead(Point2D center) {
		CentipedeSegment segment = new CentipedeSegment(this.world, center, this);
		this.centipede.add(segment);
		this.centipede.get(0).setDirection(this.moveRight);
		this.world.addEntity(segment);
	}

	public void die() {
		for (int k = 0; k < this.centipede.size(); k++) {
			CentipedeSegment head = this.centipede.get(k);
			this.world.removeEntity(head);
		}

	}
}