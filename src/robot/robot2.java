package robot;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class robot2 extends robot {
	
	public robot2() {
		currentFrame = 0;
		speed = 50;
		x = 0;
		y = 0;
	}
	
	public void draw(Graphics2D g2, int aFrame) {
		//Frame 에 따라 적절한 그림을 그린다.
		Color color = new Color(251,220,177);
		//머리그리기
		myCircle l_head = new myCircle(g2,x-30,y-10,80,80,myCircle.CENTER,0,Color.BLACK);
		l_head.draw();
		myCircle r_head = new myCircle(g2,x+150,y-10,80,80,myCircle.CENTER,0,Color.BLACK);
		r_head.draw();
		//얼굴과 볼
		myCircle face1 = new myCircle(g2,x,y+5,200,150,myCircle.CENTER,0,Color.BLACK);
		face1.draw();
		myCircle face2 = new myCircle(g2,x+15,y+30,170,120,myCircle.CENTER,0,color);
		face2.draw();
		myCircle l_cheek = new myCircle(g2,x+50,y+120,20,20,myCircle.CENTER,0,Color.RED);
		l_cheek.draw();
		myCircle r_cheek = new myCircle(g2,x+130,y+120,20,20,myCircle.CENTER,0,Color.RED);
		r_cheek.draw();
		//머리끈과 눈썹
		g2.setStroke(new BasicStroke(2f));
		g2.setColor(Color.RED);
		g2.drawLine(x+10, y+60, x+40, y+30);
		g2.drawLine(x+160, y+30, x+200, y+60);
		g2.setColor(Color.BLACK);
		g2.drawArc(x+20,y+50,40,10,0,180);
		g2.drawArc(x+140,y+50,40,10,0,180);
		if(aFrame % 40 < 20){
			//눈과 입과 다리, 움직이는 팔
			g2.setStroke(new BasicStroke(5f));
			g2.setColor(Color.BLACK);
			g2.drawLine(x+30, y+90, x+80, y+100);
			g2.drawLine(x+120, y+100, x+170, y+90);
			g2.drawArc(x+90, y+110, 20, 30, 180, 180);
			
			myCircle l_leg = new myCircle(g2,x+60,y+210,30,70,myCircle.LEFT_BOTTOM,0,Color.BLACK);
			l_leg.draw();
			myCircle r_leg = new myCircle(g2,x+110,y+210,30,70,myCircle.LEFT_BOTTOM,0,Color.BLACK);
			r_leg.draw();
			
			myCircle l_arm_b = new myCircle(g2,x+18,y+138,35,75,myCircle.CENTER,arm1Animate(aFrame),Color.BLACK);
			l_arm_b.draw();
			myCircle l_arm = new myCircle(g2,x+20,y+140,30,70,myCircle.CENTER,arm1Animate(aFrame),Color.RED);
			l_arm.draw();
			myCircle r_arm_b = new myCircle(g2,x+148,y+138,35,75,myCircle.CENTER,arm2Animate(aFrame),Color.BLACK);
			r_arm_b.draw();
			myCircle r_arm = new myCircle(g2,x+150,y+140,30,70,myCircle.CENTER,arm2Animate(aFrame),Color.RED);
			r_arm.draw();
		}
		else{
			//X X표정과 변화된 팔과 다리
			g2.setStroke(new BasicStroke(5f));
			g2.setColor(Color.BLACK);
			g2.drawLine(x+40, y+100, x+60, y+70);
			g2.drawLine(x+60, y+100, x+40, y+70);
			g2.drawLine(x+140, y+100, x+160, y+70);
			g2.drawLine(x+160, y+100, x+140, y+70);
			g2.drawLine(x+80, y+130, x+120, y+130);
			
			myCircle l_leg_c = new myCircle(g2,x+20,y+210,70,30,myCircle.LEFT_BOTTOM,0,Color.BLACK);
			l_leg_c.draw();
			myCircle r_leg_c = new myCircle(g2,x+120,y+210,70,30,myCircle.LEFT_BOTTOM,0,Color.BLACK);
			r_leg_c.draw();
			
			myCircle l_arm_b = new myCircle(g2,x+18,y+118,35,75,myCircle.CENTER,0,Color.BLACK);
			l_arm_b.draw();
			myCircle l_arm = new myCircle(g2,x+20,y+120,30,70,myCircle.CENTER,0,Color.RED);
			l_arm.draw();
			myCircle r_arm_b = new myCircle(g2,x+148,y+118,35,75,myCircle.CENTER,0,Color.BLACK);
			r_arm_b.draw();
			myCircle r_arm = new myCircle(g2,x+150,y+120,30,70,myCircle.CENTER,0,Color.RED);
			r_arm.draw();
			
		}
		//몸통
		myRectangle body_b = new myRectangle(g2,x+47,y+147,105,85,myRectangle.CENTER,0,new Color(0,0,0));
		body_b.draw();
		myRectangle body = new myRectangle(g2,x+50,y+150,100,80,myRectangle.CENTER,0,new Color(255,0,0));
		body.draw();
		
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("Gulim", Font.BOLD, 30));
		g2.drawString("KANG", x + 55, y + 200);
		
		width = 210;
		height = 323;
	}
	
	private int arm1Animate(final int aFrame) {
		int rot = 50;
		if(aFrame % 50 < 10) {
			rot += (-30 + 0) * aFrame%40;
		} 
		else if(aFrame%50<20){
			rot=-50;
			rot += (-30 + 0) * aFrame%40;
		}
		else if(aFrame%50<30){
			rot += (-30 + 0) * aFrame%40;
		}
		else if(aFrame%50<40){
			rot+=(-30+50)*aFrame%40;
		}
		else if(aFrame%60<50){
			rot=-50;
			rot+=(-30+50)*aFrame%40;
		}
	
		return rot;
	}
	
	private int arm2Animate(final int aFrame) {
		int rot=-50;
		if(aFrame % 50 < 10) {
			rot += (-30 + 0) * aFrame%40;
		} 
		else if(aFrame % 50 < 20){
			rot=-50;
			rot += (-30 + 0) * aFrame%40;
		}
		else if(aFrame % 50 < 30){
			rot=-50;
			rot += (-30 + 0) * aFrame%40;
		}
		else if(aFrame % 50 < 40) {
			rot=-50;
			rot += (-30 + 50) * aFrame%40;
		} 
		else if(aFrame%60<50){
			rot+=(-30+50)*aFrame%40;
		}
		return rot;
	}
}



