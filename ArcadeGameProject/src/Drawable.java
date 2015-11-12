
import java.awt.Color;
import java.awt.Shape;
import java.awt.image.BufferedImage;

/**
 * Represents objects that have a shape and a fill color.
 * 
 * @author Curt Clifton. Created Jan 22, 2011.
 */
public interface Drawable {

	/**
	 * Returns the fill color that should be drawn to represent this object.
	 * 
	 * @return the fill color
	 */
	public Color getColor();

	/**
	 * Returns the shape the should be drawn to represent this object.
	 * 
	 * @return the shape to draw
	 */
	public Shape getShape();
	
	/**
	 * Returns the image the should be drawn to represent this object.
	 * 
	 * @return the image to draw
	 */
	public BufferedImage getImage();
}