package robot;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Window1 extends JFrame implements ActionListener, ChangeListener {

	private static final long serialVersionUID = 1L;
	private robotStage stage;
	private JPanel controller; //2 * 4
	private JSlider speed1;
	private JSlider speed2;
	private JButton cr1;
	private JButton cr2;
	private JButton dance_start;
	private JButton dance_stop;
	private boolean robot1CreateMode = false;
	private boolean robot2CreateMode = false;
	
	public Window1(String titleName) {
		super(titleName);
		stage = new robotStage("image.jpg");
		controller = new JPanel(new GridLayout(2, 4));
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		 robot1CreateMode = false;
		 robot2CreateMode = false;
		 speed1 = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		 speed2 = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		 speed1.setMinorTickSpacing(5);
		 speed1.setMajorTickSpacing(20);
		 speed1.setPaintTicks(true);
		 speed1.setPaintLabels(true);
		 speed1.setLabelTable(speed1.createStandardLabels(25));
		 speed2.setMinorTickSpacing(5);
		 speed2.setMajorTickSpacing(20);
		 speed2.setPaintTicks(true);
		 speed2.setPaintLabels(true);
		 speed2.setLabelTable(speed1.createStandardLabels(25));
		 
		 cr1 = new JButton("로봇 1 생성");
		 cr1.setName("cr1");
		 cr2 = new JButton("로봇 2 생성");
		 cr2.setName("cr2");
		 dance_start = new JButton("춤추기!");
		 dance_start.setName("ds");
		 dance_stop = new JButton("멈추기!");
		 dance_stop.setName("de");
		 
		 controller.add(new JLabel("로봇 1 속도"));
		 controller.add(speed1);
		 controller.add(new JLabel("로봇 2 속도"));
		 controller.add(speed2);
		 controller.add(cr1);
		 controller.add(cr2);
		 controller.add(dance_start);
		 controller.add(dance_stop);

		this.getContentPane().add(stage, BorderLayout.CENTER);
		this.getContentPane().add(controller, BorderLayout.SOUTH);
		mouseMotionListener(); //마우스 모션 관련
		
		//버튼 누르면 반응 관련
		 dance_start.addActionListener(this);
		 cr1.addActionListener(this);
		 cr2.addActionListener(this);
		 dance_stop.addActionListener(this);
		 speed1.addChangeListener(this);
		 speed2.addChangeListener(this);
		 speed1.setName("robot1");
		 speed2.setName("robot2");
		 speed1.setVisible(false);
		 speed2.setVisible(false);
		//END
		 
		 //스테이지 클릭 관련
		 MouseListener listener = new MyMouseListener();
		 stage.addMouseListener(listener);
		 //END
	}
	
	private void mouseMotionListener() {
		MouseMotionListener Listener = new MouseMotionListener() {
				
			@Override
			public void mouseMoved(MouseEvent e) {

			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				
				if(robot1CreateMode) {
					speed1.setVisible(true);
					stage.createRobot(1);
					stage.moveRobot(1, e.getX(), e.getY());
				}else if (robot2CreateMode) {
					speed2.setVisible(true);
					stage.createRobot(2);
					stage.moveRobot(2, e.getX(), e.getY());
				}
				robot1CreateMode = false;
				robot2CreateMode = false;
			
				
				if(stage.is_exists(1) && stage.hitTest(1, e.getX(), e.getY())) {
					stage.moveRobot(1, e.getX(), e.getY());
				} else if (stage.is_exists(2) && stage.hitTest(2, e.getX(), e.getY())) {
					stage.moveRobot(2, e.getX(), e.getY());
				}
			}
		};
		stage.addMouseMotionListener(Listener);
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		 JSlider source = (JSlider) arg0.getSource();
		 if(!source.getValueIsAdjusting()) {
			 System.out.println(source.getName());
			 if(source.getName().equals("robot1")) {
				 System.out.println("로봇1 스피드 변경" +  + source.getValue());
				 stage.changeSpeed(1, source.getValue());
			 } else {
				 System.out.println("로봇2 스피드 변경" +  + source.getValue());
				 stage.changeSpeed(2, source.getValue());
			 }
		 }
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton s = (JButton) arg0.getSource();
		switch (s.getName())
		{
		case "ds":
			System.out.println("춤을 추기 시작합니다..");
			stage.godance();
		break;
		case "cr1":
			System.out.println("로봇 1 만듦");
			stage.changeAlert(1);
			robot1CreateMode = true;
			robot2CreateMode = false;
		break;
		case "cr2":
			System.out.println("로봇 2 만듦");
			stage.changeAlert(2);
			robot1CreateMode = false;
			robot2CreateMode = true;
		break;
		case "de":
			System.out.println("춤을 정지합니다..");
			stage.nodance();
		break;
		default:
			System.out.println("Error!");
		break;
		}
	}
	
	class MyMouseListener implements MouseListener {

		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			if(robot1CreateMode) {
				speed1.setVisible(true);
				stage.createRobot(1);
				stage.moveRobot(1, e.getX(), e.getY());
			}else if (robot2CreateMode) {
				speed2.setVisible(true);
				stage.createRobot(2);
				stage.moveRobot(2, e.getX(), e.getY());
			}
			robot1CreateMode = false;
			robot2CreateMode = false;
		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {

		}
		 
	 }
}

