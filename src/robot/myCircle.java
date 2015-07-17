package robot;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;



public class myCircle {
	
	public final static int CENTER = 0;
	public final static int LEFT_TOP = 1;
	public final static int LEFT_BOTTOM = 2;
	public final static int RIGHT_TOP = 3;
	public final static int RIGHT_BOTTOM = 4;
	
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
	private Point customRotateCenter;
	public myCircle(Graphics2D g2, int x, int y, int width, int height, int rotateCenter, int rotation, Color c) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rotateCenter = rotateCenter;
		this.rotation = rotation;
		this.g2 = g2;
		color = c;
		transform = new AffineTransform();
	}
	
	public myCircle(Graphics2D g2, int x, int y, int width, int height, Point rotateCenter, int rotation, Color c) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rotateCenter = -1;
		
		//
		customRotateCenter = rotateCenter;
		//
		this.rotation = rotation;
		this.g2 = g2;
		color = c;
		transform = new AffineTransform();
	}
	
	public void draw() {
		Ellipse2D.Double obj = new Ellipse2D.Double(x, y, width, height);
		changeCenter(rotateCenter);
		transform.rotate(Math.toRadians(rotation), newX, newY);
		transformed = transform.createTransformedShape(obj);
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
		default:
			//custom rotateCenter
			newX = customRotateCenter.x;
			newY = customRotateCenter.y;
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

