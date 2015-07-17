package robot;

import java.awt.Graphics2D;

abstract class robot {
	public int x; //�κ��� x��ġ
	public int y; //�κ��� y��ġ
	public int width; //�κ��� ��ü���� width
	public int height; //�κ��� ��ü���� height
	public int speed; //�κ��� ���ߴ� �ӵ� (������ ��ȭ��)
	protected int currentFrame; //���� �κ��� �ð�(�ִϸ��̼�)
	
	public void draw(Graphics2D g2) {
		draw(g2, currentFrame); //���� �������� �������� �κ��� �׷��ش�.
	}
	
	abstract void draw(Graphics2D g2, int aFrame);
	
	public int getCurrentFrame() {
		return currentFrame;
	}
	
	public void nextFrame() {
		if(currentFrame == Integer.MAX_VALUE)
			currentFrame = 0;
		currentFrame++;
	}
}
