package robot;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;


public class robot1 extends robot {
	
	public robot1() {
		currentFrame = 0;
		speed = 50;
		x = 0;
		y = 0;
	}
	
	public void draw(Graphics2D g2, int aFrame) {
		//Frame 에 따라 적절한 그림을 그린다.
		myTriangle earLeft = new myTriangle(g2, x, y+15, 30, 30, myTriangle.CENTER, myTriangle.TOP_CENTER, earAnimate1(aFrame), new Color(253, 225, 97));
		myTriangle earRight = new myTriangle(g2, x+100, y+15, 30, 30, myTriangle.CENTER, myTriangle.TOP_CENTER, earAnimate2(aFrame), new Color(253, 225, 97));
		myCircle face = new myCircle(g2, x+15, y+15, 100, 100, myCircle.CENTER, 0, new Color(248, 237, 211));
		Point[] s = new Point[4];
		s[0] = new Point(15, 0);
		s[1] = new Point(35, 0);
		s[2] = new Point(50, 20);
		s[3] = new Point(0, 20);
		myPolygon hat = new myPolygon(g2, x+30, y+hatAnimate(aFrame), s, myPolygon.LEFT_TOP, -10, new Color(204, 197, 84));
		face.draw();
		earLeft.draw();
		earRight.draw();
		hat.draw();
		if(aFrame % 40 < 20) {
			myCircle eye1 = new myCircle(g2, x+30, y+50, 20, 30, myCircle.CENTER, 0, Color.WHITE);
			myCircle eye1_in = new myCircle(g2, x+35, y+55, 15, 25, myCircle.CENTER, 0, new Color(185, 141, 228));
			myCircle eye2 = new myCircle(g2, x+80, y+50, 20, 30, myCircle.CENTER, 0, Color.WHITE);
			myCircle eye2_in = new myCircle(g2, x+80, y+55, 15, 25, myCircle.CENTER, 0, new Color(185, 141, 228));
			eye1.draw();
			eye1_in.draw();
			eye2.draw();
			eye2_in.draw();
		} else {
			//> < 표정 구현
			g2.setStroke(new BasicStroke(2f));
			g2.setColor(Color.BLACK);
			g2.drawLine(x+40, y+50, x+50, y+65);
			g2.drawLine(x+50, y+65, x+40, y+80);
			
			g2.drawLine(x+90, y+50, x+80, y+65);
			g2.drawLine(x+80, y+65, x+90, y+80);
			g2.setStroke(new BasicStroke(1f));
		}
		//왼팔 / 오른팔 구현
		//왼쪽팔 / 오른쪽팔 구현 (팔길이 / 굵기 : 120 / 20)
		myRectangle armLeft = new myRectangle(g2, x-90, y + 115, 120, 20, myRectangle.RIGHT_BOTTOM, arm1Animate(aFrame), new Color(204, 204, 255));
		armLeft.draw();
		myRectangle armRight = new myRectangle(g2, x+110, y + 115, 120, 20, myRectangle.LEFT_TOP, arm2Animate(aFrame), new Color(204, 204, 255));
		armRight.draw();
		//손(?) 구현 (30x30)
		myCircle handLeft = new myCircle(g2, x-95, y+110, 30,30, new Point(x-90+120, y+115+20), arm1Animate(aFrame), new Color(248, 237, 211));
		handLeft.draw();
		myCircle handRight = new myCircle(g2, x+210, y+110, 30, 30, new Point(x+110, y+115), arm2Animate(aFrame), new Color(248, 237, 211));
		handRight.draw();

		//신발 구현
		s = new Point[4];
		s[0] = new Point(0, 0);
		s[1] = new Point(25, 0);
		s[2] = new Point(30, 12);
		s[3] = new Point(0, 12);
		myPolygon shoe1 = new myPolygon(g2, x+25, y+230+77, s, myPolygon.LEFT_TOP, 0, Color.BLACK);
		shoe1.draw();
		s[0] = new Point(0, 0);
		s[1] = new Point(25, 0);
		s[2] = new Point(30, 12);
		s[3] = new Point(0, 12);
		myPolygon shoe2 = new myPolygon(g2, x+85, y+230+77, s, myPolygon.LEFT_TOP, 0, Color.BLACK);
		shoe2.draw();
		if(aFrame % 40 > 1 && aFrame % 40 < 20 &&(aFrame % 80 < 40)) {
			s = new Point[9];
			s[0] = new Point(0, 10);
			s[1] = new Point(-5, 16);
			s[2] = new Point(5, 13);
			s[3] = new Point(7, 17);
			s[4] = new Point(10, 15);
			s[5] = new Point(18, 20);
			s[6] = new Point(15, 15);
			s[7] = new Point(23, 15);
			s[8] = new Point(10, 0);
			myPolygon fireb = new myPolygon(g2, x+25, y+300-((int)(aFrame%20)*3), s, myPolygon.LEFT_TOP, 0, new Color(255, 0, 0));

			fireb.changeColor(new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
			fireb.draw();
			//불길을 그림
		} else if(aFrame % 40 > 1 && aFrame % 40 < 20 &&(aFrame % 80 > 40)) {
			s = new Point[9];
			s[0] = new Point(0, 10);
			s[1] = new Point(-5, 16);
			s[2] = new Point(5, 13);
			s[3] = new Point(7, 17);
			s[4] = new Point(10, 15);
			s[5] = new Point(18, 20);
			s[6] = new Point(15, 15);
			s[7] = new Point(23, 15);
			s[8] = new Point(10, 0);
			myPolygon fireb = new myPolygon(g2, x+85, y+300-((int)(aFrame%20)*3), s, myPolygon.LEFT_TOP, 0, new Color(255, 0, 0));

			fireb.changeColor(new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
			fireb.draw();
		}
		
		//다리 구현
		myRectangle leg1 = new myRectangle(g2, x+25, y+230, 20, legAnimate(aFrame), myRectangle.LEFT_TOP, 0, new Color(204, 204, 255));
		leg1.draw();
		myRectangle leg2 = new myRectangle(g2, x+85, y+230, 20, legAnimate2(aFrame), myRectangle.RIGHT_TOP, 0, new Color(204, 204, 255));
		leg2.draw();

		
		
		
		//몸통 구현
		s = new Point[6];
		s[0] = new Point(20, 0);
		s[1] = new Point(100, 0);
		s[2] = new Point(120, 20);
		s[3] = new Point(120, 120);
		s[4] = new Point(0, 120);
		s[5] = new Point(0, 20);
		myPolygon body = new myPolygon(g2, x+5, bodyAnimate(aFrame), s, myPolygon.CENTER, 0, new Color(196, 229, 255));
		body.draw();
		myRectangle kara1 = new myRectangle(g2, x+25, bodyAnimate(aFrame), 77, 20, myRectangle.LEFT_TOP, 45, new Color(255,244,251));
		myRectangle kara2 = new myRectangle(g2, x+25+80+15, bodyAnimate2(aFrame), 60, 20, myRectangle.LEFT_TOP, 135, new Color(255,244,251));
		kara1.draw();
		kara2.draw();
		
		g2.setColor(Color.BLUE);
		g2.setFont(new Font("Gulim", Font.BOLD, 20));
		//g2.drawString(robotName, x + 32, y + 205);
		
		width = 120;
		height = 323;
	}
	
	private int earAnimate1(final int aFrame) {
		//왼쪽 귀의 각도 조절 (좌우 흔들림)
		int rot = -30;
		if(aFrame % 20 < 10) {
			rot-=aFrame%20*2;
			//왼흔들림
		} else {
			rot+= -40 + (aFrame%20)*2;
			//오른흔들림
		}
		return rot;
	}
	
	private int earAnimate2(final int aFrame) {
		int rot = 40;
		if(aFrame % 20 < 10) {
			rot+=aFrame%20*2;
			//왼흔들림
		} else {
			rot-= -40 + (aFrame%20)*2;
			//오른흔들림
		}
		return rot;
	}
	
	private int hatAnimate(final int aFrame) {
		int posY = 5;
		if(aFrame % 10 < 5) {
			posY += aFrame % 10;
			//모자 위로 감
		} else {
			posY -= -10 + (aFrame%10);
			//모자 아래로 감
		}
		return posY;
	}
	
	private int arm1Animate(final int aFrame) {
		int rot = -45;
		if(aFrame % 40 < 10) {
			rot += aFrame%20;
		} else if (aFrame % 40 < 20){
			rot -= -20 + (aFrame % 20);
		}
		else if(aFrame % 40 >= 20 && aFrame % 40 < 30) {
			rot = 0;
		} else {
			rot = 35;
		}
		return rot;
	}
	
	private int arm2Animate(final int aFrame) {
		int rot = 45;
		if(aFrame % 40 < 10) {
			rot -= aFrame%20;
		} else if (aFrame % 40 < 20){
			rot += -20 + (aFrame % 20);
		}
		else if(aFrame % 40 >= 20 && aFrame % 40 < 30) {
			rot += (0 - 45) * aFrame%40;
			//현재위치 += (목표위치 - 현재위치) x n;
			//현재 : 45, 목표 0
		} else {
			rot += (-55 + 0) * aFrame%40;
		}
		return rot;
	}
	
	private int bodyAnimate(final int aFrame) {
		int posY = y+110;
		if(aFrame % 10 < 5) {
			posY += aFrame % 10;
		} else {
			posY -= -10 + (aFrame%10);
		}
		return posY;
	}
	
	private int bodyAnimate2(final int aFrame) {
		int posY = y+125;
		if(aFrame % 10 < 5) {
			posY += aFrame % 10;
		} else {
			posY -= -10 + (aFrame%10);
		}
		return posY;
	}
	
	private int legAnimate(final int aFrame) {
		//원래 다리길이 : 77
		//다리길이가 20으로 줄었다가 77로 복구됨
		int height = 77;
		if(aFrame % 80 > 40)
			return height;
		if(aFrame % 40 < 20) {
			//height = 77; // 77 to 20
			height -= (aFrame % 40) * 3;
		} else {
			height = 40; // 20 to 77
			height += (aFrame % 40) * 3 - 77;
		}
		return height;
	}
	
	private int legAnimate2(final int aFrame) {
		//원래 다리길이 : 77
		//한 박자 늦게 실행됨
		int height = 77;
		if(aFrame % 80 > 40) {
			if(aFrame % 40 < 20) {
				//height = 77; // 77 to 20
				height -= (aFrame % 40) * 3;
			} else {
				height = 40; // 20 to 77
				height += (aFrame % 40) * 3 - 77;
			}
			return height;
		} else {

		}
		return height;
	}
}
