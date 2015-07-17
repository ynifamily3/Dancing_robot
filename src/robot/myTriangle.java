package robot;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;



public class myTriangle {
	
	public final static int CENTER = 0;
	public final static int LEFT_TOP = 1;
	public final static int LEFT_BOTTOM = 2;
	public final static int RIGHT_TOP = 3;
	public final static int RIGHT_BOTTOM = 4;
	public final static int CENTER_BOTTOM = 5;
	
	public final static int TOP_LEFT = -1;
	public final static int TOP_CENTER = -2;
	public final static int TOP_RIGHT = -3;
	
	private int x;
	private int y;
	private int newX;
	private int newY;
	private int width;
	private int height;
	private int rotateCenter;
	private int rotation;
	private Color color;
	private Graphics2D g2;
	private AffineTransform transform;
	private Shape transformed;
	private int topCenter;
	
	/**
	 * 
	 * @param g2 g2
	 * @param x x
	 * @param y y
	 * @param width width
	 * @param height height
	 * @param rotateCenter rotateCenter
	 * @param topCenter topCenter
	 * @param rotation rotation
	 * @param c Color
	 */
	public myTriangle(Graphics2D g2, int x, int y, int width, int height, int rotateCenter, int topCenter, int rotation, Color c) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rotateCenter = rotateCenter;
		this.rotation = rotation;
		this.g2 = g2;
		this.topCenter = topCenter;
		if(this.topCenter > 0) {
			System.out.println("exceed");
			this.topCenter = width;
			}
		color = c;
		transform = new AffineTransform();
	}
	
	public void draw() {
		int newTopCenter = topCenter;
		if(newTopCenter < 0) {
			switch(newTopCenter) {
			case TOP_LEFT:
				newTopCenter = x;
				break;
			case TOP_CENTER:
				newTopCenter = x + width/2;
				break;
			case TOP_RIGHT:
				newTopCenter = x + width;
				break;
			}
		}
		Path2D.Double triangle = new Path2D.Double();
		triangle.moveTo(x, y + height);
		triangle.lineTo(x + width, y + height);
		triangle.lineTo(newTopCenter, y);
		triangle.closePath();
		changeCenter(rotateCenter);
		transform.rotate(Math.toRadians(rotation), newX, newY);
		transformed = transform.createTransformedShape(triangle);
		g2.setColor(color);	
		g2.fill(transformed);

	}
	
	public void changeCenter(int aCenter) {
		switch(aCenter) {
		case CENTER:
			newX = x + width/2;
			newY = y + height/2;
			break;
		case LEFT_TOP:
			newX = x;
			newY = y;
			break;
		case LEFT_BOTTOM:
			newX = x;
			newY = y + height;
			break;
		case RIGHT_TOP:
			newX = x + width;
			newY = y;
			break;
		case RIGHT_BOTTOM:
			newX = x + width;
			newY = y + height;
			break;
		case CENTER_BOTTOM:
			newX = x + width/2;
			newY = y;
			break;
		default:
			break;
		}
	}
	
	public void rotation(int r) {
		changeCenter(rotateCenter);
		rotation = r;
	}
	
	public void rotate(int dr) {
		changeCenter(rotateCenter);
		rotation += dr;
	}
	
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void translate(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}
	
	public void changeColor(Color c) {
		color = c;
	}
	
	public void grow(int dWidth, int dHeight) {
		this.width += dWidth;
		this.height += dHeight;
	}
	
	public void transForm(int aWidth, int aHeight) {
		this.width = aWidth;
		this.height = aHeight;
	}
}

