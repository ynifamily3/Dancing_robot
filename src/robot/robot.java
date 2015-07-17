package robot;

import java.awt.Graphics2D;

abstract class robot {
	public int x; //로봇의 x위치
	public int y; //로봇의 y위치
	public int width; //로봇의 전체적인 width
	public int height; //로봇의 전체적인 height
	public int speed; //로봇의 춤추는 속도 (프레임 변화율)
	protected int currentFrame; //현재 로봇의 시간(애니메이션)
	
	public void draw(Graphics2D g2) {
		draw(g2, currentFrame); //현재 프레임을 기준으로 로봇을 그려준다.
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
