package robot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;


public class robotStage extends JComponent implements dancingrobots{

	private static final long serialVersionUID = 1L;

	//����̹��� ����
	private ImageIcon imgIcon;
	private Image img;
	private int width;
	private int height;
	//����̹��� ���� END
	
	//�κ� ���� ����
	private boolean robot1_exists; //�κ�1�� �����ϴ°�
	private boolean robot2_exists;
	private robot r1; //�κ� 1 ������Ʈ
	private robot r2; //�κ� 2 ������Ʈ
	//�κ� ���� ���� END
	
	//���߱� �����
	private boolean isdancing; 
	private DanceThread t1;
	private DanceThread2 t2;
	//���߱� ����� END
	
	//�ٷ�
	private int alert_mode;
	//�ٷ� END
	
	//�뷡
	private Music bgm = new Music("dance.wav");
	//�뷡END
	
	public robotStage(String aimageName) {
		super();
		alert_mode = 0;
		//0 : �ƹ� �˸� ����, 1 : 1�� �κ� �����϶�� ���, 2 : 2�� �κ� �����϶�� ���
		//t1 = new DanceThread(1);
		//t2 = new DanceThread(2);
		isdancing = false;
		imgIcon = new ImageIcon(aimageName);
		img = imgIcon.getImage();
		width = imgIcon.getIconWidth();
		height = imgIcon.getIconHeight();
		this.setBounds(0, 0, width, height);
		robot1_exists = false;
		robot2_exists = false;
	}
	
	public void createRobot(int robotNumber) {
		System.out.println("�κ���ȣ " + robotNumber + " �����õ�");
		switch(robotNumber) {
		case 1:
			if(robot1_exists) {
				changeAlert(-1);
				return;
			}
			r1 = new robot1();
			r1.width = 100; //�� �κ��� �������� �ʿ�
			r1.height = 100; //���⵵
			r1.speed = 50;
			robot1_exists = true;
			changeAlert(0);
			t1 = new DanceThread();
			t1.start();
			break;
		case 2:
			if(robot2_exists) {
				changeAlert(-1);
				return;
			}
			r2 = new robot2();
			r2.width = 100;
			r2.height = 100;
			r2.speed = 50;
			robot2_exists = true;
			changeAlert(0);
			t2 = new DanceThread2();
			t2.start();
			break;
			default:
				System.out.println("Error!");
		}
	}
	
	public void changeAlert(int aAlert) {
		alert_mode = aAlert;
		repaint();
	}
	
	public boolean is_exists(int what) {
		boolean eval = false;
		switch(what) {
		case 1:
			eval = robot1_exists;
			break;
		case 2:
			eval = robot2_exists;
			break;
		default:
			break;
		}
		return eval;
	}
	
	public void moveRobot(int what, int x, int y) {
		//���콺 �巡�׷� ���� �κ��� ��ǥ ����
		switch(what) {
		case 1:
			r1.x = x - r1.width / 2;
			r1.y = y - r1.height / 2;
			break;
		case 2:
			r2.x = x - r2.width / 2;
			r2.y = y - r2.height / 2;
			break;
		default:
			System.out.println("����� �߸� ���õǾ����ϴ�.");
			break;
		}
		repaint();
	}
	
	public boolean hitTest(int what, int x, int y) {
		boolean testResult = false;
		switch(what) {
		case 1:
			testResult = (r1.x <= x && (r1.x+r1.width) >= x && r1.y <= y && (r1.y+r1.height) >= y);
			break;
		case 2:
			testResult = (r2.x <= x && (r2.x+r2.width) >= x && r2.y <= y && (r2.y+r2.height) >= y);
			break;
		default:
			System.out.println("����� �߸� ���õǾ����ϴ�.");
			break;
		}
		return testResult;
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, width, height, this);
		
		Graphics2D g2 = (Graphics2D)g;
		
		if(alert_mode == 1) {
			myRectangle msgBox = new myRectangle(g2, 0, 0, 800, 20, myRectangle.LEFT_TOP, 0, Color.WHITE);
			msgBox.draw();
			g2.setColor(Color.BLACK);
			g2.drawString("Click the stage where you want to create a robot", 245, 15);
		} else if (alert_mode == 2) {
			myRectangle msgBox = new myRectangle(g2, 0, 0, 800, 20, myRectangle.LEFT_TOP, 0, Color.WHITE);
			msgBox.draw();
			g2.setColor(Color.BLACK);
			g2.drawString("Click the stage where you want to create a robot", 245, 15);
		}
		if(robot1_exists) {
			r1.draw(g2);
		}
		if(robot2_exists) {
			r2.draw(g2);
		}
	}
	
	public void changeSpeed(int what, int speed) {
		System.out.println("���ǵ� ������");
		switch(what) {
		case 1:
			r1.speed = speed;
			break;
		case 2:
			r2.speed = speed;
			break;
		default:
			System.out.println("���ǵ� ������ �����Ͽ����ϴ�.");
			break;
		}
	}
	
	public void godance() {
		bgm.playSound();
		startdancing();
	}
	public void nodance() {
		bgm.stopSound();
		stopdancing();
	}
	
	@Override
	public void startdancing() {
		// TODO Auto-generated method stub
		if(isdancing)
			return;
		if(robot1_exists) {
			t1 = new DanceThread();
			t1.start();
		}
		if(robot2_exists) {
			t2 = new DanceThread2();
			t2.start();
		}
		isdancing = true;
	}

	@Override
	public void stopdancing() {
		// TODO Auto-generated method stub
		isdancing = false;
	}
	
	class DanceThread extends Thread {
		
		private int delay;
		public DanceThread() {
				delay = 110 - r1.speed;
		}
		public void run() {
			try {
					while(isdancing) {
						delay = 110 - r1.speed;
						r1.nextFrame();
						repaint();
						Thread.sleep(delay);
				}
			}catch (InterruptedException ex) {
				System.out.println("���� �߻�" + ex.getMessage());
			}
		}
	}
	
	class DanceThread2 extends Thread {
		
		private int delay;
		public DanceThread2() {
			delay = 110 - r2.speed;
		}
		public void run() {
			try {
					while(isdancing) {
						delay = 110 - r2.speed;
						r2.nextFrame();
						repaint();
						
						Thread.sleep(delay);
					}
			}catch (InterruptedException ex) {
				System.out.println("���� �߻�" + ex.getMessage());
			}
		}
	}
}
