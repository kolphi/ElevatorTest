package sqelevator.application.ui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class AECUI extends JFrame {

	private static final long serialVersionUID = -8986012443059217901L;
	private static final String ICON_WEIGHT = "res/balance-32.png";
	private static final String ICON_SPEED = "res/speedometer-32.png";
	private static final String ICON_DOORS_OPEN = "res/door-open-64.png";
	private static final String ICON_DOORS_CLOSED = "res/door-closed-64.png";
	private static final int FLOOR_COUNT = 3;
	
	private JLabel lWeight, lSpeed;
	private JCheckBox cbManualControl;
	private ImageIcon iiWeight, iiSpeed, iiDoors;
	private DirectionsUIElement deCurrentDirection;
	private DirectionsUIElement[] deFloors;
	private JButton[] bFloors;
	private JRadioButton[] rbFloors;
	
	
	public AECUI() {
		super("Elevator Control");
		//setup layout
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//add weight and speed panel
		JPanel pWeightSpeed = new JPanel();
		iiWeight = new ImageIcon(getClass().getClassLoader().getResource(ICON_WEIGHT));
		iiSpeed = new ImageIcon(getClass().getClassLoader().getResource(ICON_SPEED));
		iiDoors = new ImageIcon(getClass().getClassLoader().getResource(ICON_DOORS_CLOSED));
		lWeight = new JLabel("0 kg");
		lSpeed = new JLabel("0 m/s");
		
		pWeightSpeed.add(new JLabel(iiWeight));
		pWeightSpeed.add(lWeight);
		pWeightSpeed.add(Box.createRigidArea(new Dimension(20,0)));
		pWeightSpeed.add(new JLabel(iiSpeed));
		pWeightSpeed.add(lSpeed);
		
		add(pWeightSpeed);
		
		//add door and direction panel
		JPanel pDoorDirection = new JPanel();
		JPanel pDoorState = new JPanel();
		pDoorState.setLayout(new BoxLayout(pDoorState, BoxLayout.Y_AXIS));
		pDoorState.add(new JLabel("Doors"));
		pDoorState.add(new JLabel(iiDoors));
		pDoorDirection.add(pDoorState);
		pDoorDirection.add(Box.createRigidArea(new Dimension(20,0)));
		
		JPanel pCurrentDirection = new JPanel();
		pCurrentDirection.setLayout(new BoxLayout(pCurrentDirection, BoxLayout.Y_AXIS));
		deCurrentDirection = new DirectionsUIElement();
		pCurrentDirection.add(new JLabel("Direction"));
		pCurrentDirection.add(deCurrentDirection);
		pDoorDirection.add(pCurrentDirection);
		
		add(pDoorDirection);
		
		//add floors
		JPanel pFloors = new JPanel();
		pFloors.setLayout(new BoxLayout(pFloors, BoxLayout.Y_AXIS));
		JPanel[] pFloor = new JPanel[FLOOR_COUNT];
		deFloors = new DirectionsUIElement[FLOOR_COUNT];
		bFloors = new JButton[FLOOR_COUNT];
		rbFloors = new JRadioButton[FLOOR_COUNT];
		
		for (int n=0;n<FLOOR_COUNT;n++) {
			JPanel floor = new JPanel();
			floor.setLayout(new BoxLayout(floor, BoxLayout.X_AXIS));
			deFloors[n] = new DirectionsUIElement();
			bFloors[n] = new JButton("Floor " + n);
			rbFloors[n] = new JRadioButton("");
			
			floor.add(deFloors[n]);
			floor.add(Box.createRigidArea(new Dimension(20,0)));
			floor.add(bFloors[n]);
			floor.add(Box.createRigidArea(new Dimension(20,0)));
			floor.add(rbFloors[n]);
			
			JPanel floor_bound = new JPanel();
			floor_bound.add(floor);
			
			pFloor[n] = floor_bound;
			
			pFloors.add(pFloor[n]);
		}
		
		add(pFloors);

		//add manual control
		cbManualControl = new JCheckBox("Manual Control");
		JPanel pControl = new JPanel();
		pControl.add(cbManualControl);
		add(pControl);
		
		
		//pack and show
		pack();
		setMinimumSize(getSize());
		setSize(getWidth()+40, getHeight()+30);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new AECUI();
	}
	
}
