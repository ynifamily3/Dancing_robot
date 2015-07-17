package robot;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;



public class myPolygon {
	
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
	
	private Point[] polygonList;
	
	public myPolygon(Graphics2D g2, int x, int y, Point[] polygonList, int rotateCenter, int rotation, Color c) {
		this.x = x;
		this.y = y;
		this.rotateCenter = rotateCenter;
		this.rotation = rotation;
		this.g2 = g2;
		color = c;
		transform = new AffineTransform();
		this.polygonList = polygonList;
		for(int i = 0 ; i < polygonList.length; i++) {
			int newX = (x+(int)(polygonList[i].x));
			int newY = (y+(int)(polygonList[i].y));
			this.width = Math.max(newX, this.width);
			this.height = Math.max(newY, this.height);
		}
	}
	
	public void draw() {
		Path2D.Double polygon = new Path2D.Double();
		for(int i = 0 ; i < polygonList.length; i++) {
			int newX = (x+(int)(polygonList[i].x));
			int newY = (y+(int)(polygonList[i].y));
			if(i == 0)
				polygon.moveTo(newX, newY);
			else
				polygon.lineTo(newX, newY);
		}
		polygon.closePath();
		changeCenter(rotateCenter);
		transform.rotate(Math.toRadians(rotation), newX, newY);
		transformed = transform.createTransformedShape(polygon);
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

