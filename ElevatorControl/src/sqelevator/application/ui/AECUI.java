package sqelevator.application.ui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import sqelevator.IElevator;
import sqelevator.application.model.ElevatorCommands;
import sqelevator.application.model.FloorCommands;
import sqelevator.application.model.IElevatorModel;
import sqelevator.application.model.IFloor;

public class AECUI extends JFrame implements Observer, ActionListener {

	private static final long serialVersionUID = -8986012443059217901L;
	private static final String ICON_WEIGHT = "res/balance-32.png";
	private static final String ICON_SPEED = "res/speedometer-32.png";
	private static final String ICON_DOORS_OPEN = "res/door-open-64.png";
	private static final String ICON_DOORS_CLOSED = "res/door-closed-64.png";
	private static final int FLOOR_COUNT = 5;

	private IElevatorModel model = null;
	private JLabel lWeight, lSpeed, lConnection;
	private JCheckBox cbManualControl;
	private ImageIcon iiWeight, iiSpeed, iiDoors;
	private JLabel lDoors;
	private DirectionsUIElement deCurrentDirection;
	private DirectionsUIElement[] deFloors;
	private JButton[] bFloors;
	private JRadioButton[] rbFloors;
	private boolean uiLoaded = false;

	public AECUI() {

		super("Elevator Control");
		// setup layout
		getContentPane().setLayout(
				new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// add weight and speed panel
		JPanel pWeightSpeed = new JPanel();
		iiWeight = new ImageIcon(getClass().getClassLoader().getResource(
				ICON_WEIGHT));
		iiSpeed = new ImageIcon(getClass().getClassLoader().getResource(
				ICON_SPEED));
		iiDoors = new ImageIcon(getClass().getClassLoader().getResource(
				ICON_DOORS_CLOSED));
		lWeight = new JLabel("0 kg");
		lSpeed = new JLabel("0 m/s");

		pWeightSpeed.add(new JLabel(iiWeight));
		pWeightSpeed.add(lWeight);
		pWeightSpeed.add(Box.createRigidArea(new Dimension(20, 0)));
		pWeightSpeed.add(new JLabel(iiSpeed));
		pWeightSpeed.add(lSpeed);

		add(pWeightSpeed);

		// add door and direction panel
		JPanel pDoorDirection = new JPanel();
		JPanel pDoorState = new JPanel();
		pDoorState.setLayout(new BoxLayout(pDoorState, BoxLayout.Y_AXIS));
		pDoorState.add(new JLabel("Doors"));
		lDoors = new JLabel(iiDoors);
		pDoorState.add(lDoors);
		pDoorDirection.add(pDoorState);
		pDoorDirection.add(Box.createRigidArea(new Dimension(20, 0)));
		JPanel pCurrentDirection = new JPanel();
		pCurrentDirection.setLayout(new BoxLayout(pCurrentDirection,
				BoxLayout.Y_AXIS));
		deCurrentDirection = new DirectionsUIElement();
		pCurrentDirection.add(new JLabel("Direction"));
		pCurrentDirection.add(deCurrentDirection);
		pDoorDirection.add(pCurrentDirection);

		add(pDoorDirection);

		// add floors
		JPanel pFloors = new JPanel();
		pFloors.setLayout(new BoxLayout(pFloors, BoxLayout.Y_AXIS));
		JPanel[] pFloor = new JPanel[FLOOR_COUNT];
		deFloors = new DirectionsUIElement[FLOOR_COUNT];
		bFloors = new JButton[FLOOR_COUNT];
		rbFloors = new JRadioButton[FLOOR_COUNT];

		for (int n = 0; n < FLOOR_COUNT; n++) {
			JPanel floor = new JPanel();
			floor.setLayout(new BoxLayout(floor, BoxLayout.X_AXIS));
			deFloors[n] = new DirectionsUIElement();
			bFloors[n] = new JButton("Floor " + n);
			bFloors[n].setActionCommand("" + n);
			bFloors[n].addActionListener(this);
			rbFloors[n] = new JRadioButton("");
			rbFloors[n].setSelected(false);
			floor.add(deFloors[n]);
			floor.add(Box.createRigidArea(new Dimension(20, 0)));
			floor.add(bFloors[n]);
			floor.add(Box.createRigidArea(new Dimension(20, 0)));
			floor.add(rbFloors[n]);

			pFloor[n] = floor;

			JPanel floor_bound = new JPanel();
			floor_bound.add(floor);

			pFloor[n] = floor_bound;

			pFloors.add(pFloor[n], 0);
		}

		add(pFloors);

		// add manual control
		cbManualControl = new JCheckBox("Manual Control");
		cbManualControl.setSelected(true);
		lConnection = new JLabel("Connection ?");
		JPanel pControl = new JPanel();
		pControl.add(cbManualControl);
		pControl.add(lConnection);
		add(pControl);

		// pack and show
		pack();
		setMinimumSize(getSize());
		setSize(getWidth() + 40, getHeight() + 30);
		setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		// synchronized (this) {

		ArrayList<IFloor> floors;
		if (arg != null && (arg instanceof IElevatorModel)) {

			model = (IElevatorModel) arg;

			if (model.isConnected()) {
				lConnection.setBackground(Color.WHITE);
				lConnection.setText("Connected!");
			} else {
				lConnection.setBackground(Color.RED);
				lConnection.setText("No Connection!");
			}
			if (!uiLoaded) {
				floors = model.getFloors();

				int i = 0;
				for (IFloor floor : floors) {
					floor = floors.get(i);
					i++;
				}

				uiLoaded = true;
			} else {
				model = (IElevatorModel) arg;
				floors = model.getFloors();
				int i = 0;

				lSpeed.setText(model.getSpeed() + " m/sec");
				lWeight.setText(model.getWeight() + " kg");

				// clock = model.getClockTick();
				ArrayList<IFloor> floorz = model.getFloors();
				for(int n=0;n<FLOOR_COUNT;n++)
					rbFloors[n].setSelected(floorz.get(n).isTarget());

				int doorStatus = model.getDoorStatus();
				System.out.println(doorStatus);
				if (doorStatus == 1) {
					lDoors.setIcon(new ImageIcon(getClass().getClassLoader()
							.getResource(ICON_DOORS_OPEN)));
				} else {
					lDoors.setIcon(new ImageIcon(getClass().getClassLoader()
							.getResource(ICON_DOORS_CLOSED)));
				}
				// up=0, down=1 and uncommitted=2
				switch (model.getCommittedDirection()) {
				case IElevator.ELEVATOR_DIRECTION_DOWN:
					deCurrentDirection.setUpDirection(true);
					deCurrentDirection.setDownDirection(false);
					break;
				case IElevator.ELEVATOR_DIRECTION_UP:
					deCurrentDirection.setDownDirection(true);
					deCurrentDirection.setUpDirection(false);
					break;
				case IElevator.ELEVATOR_DIRECTION_UNCOMMITTED:
					deCurrentDirection.setUpDirection(false);
					deCurrentDirection.setDownDirection(false);
					break;
				}

				for (IFloor floor : floors) {

					int floorNum = floor.getFloorNumber();
					boolean resultDown = floor.floorCommands(
							FloorCommands.GET_FLOOR_BUTTON_DOWN, floorNum);
					boolean resultUp = floor.floorCommands(
							FloorCommands.GET_FLOOR_BUTTON_UP, floorNum);

					deFloors[floorNum].setUpDirection(resultUp);
					deFloors[floorNum].setDownDirection(resultDown);

					rbFloors[floorNum].setSelected(floor.isTarget());

					if (floorNum == model.getNextTargetFloor()) {
						bFloors[floorNum].setBackground(Color.YELLOW);
					} else if (floorNum == model.getElevatorFloorNumber()) {
						bFloors[floorNum].setBackground(Color.MAGENTA);
					} else {
						bFloors[floorNum].setBackground(Color.WHITE);
					}

				}
			}
		}
	}

	public void setElevatorTarget(int[] param) {
		boolean cmdSent = model.elevatorCommands(ElevatorCommands.SET_TARGET,
				param);
		if (!cmdSent) {
			System.out.println("Error Occured while processing your request !");
			// JOptionPane.showMessageDialog(this,"Error Occured while processing your request !");
		} else {
			System.out.println("Succesful !" + param[0] + " " + param[1]);
			// JOptionPane.showMessageDialog(this, "Succesful !");
		}
	}
	public void setDirectionStatus(int[] param) {
		boolean cmdSent = model.elevatorCommands(ElevatorCommands.SET_COMMITED_DIR,
				param);
		if (!cmdSent) {
			System.out.println("Error Occured while processing your request !");
			// JOptionPane.showMessageDialog(this,"Error Occured while processing your request !");
		} else {
			System.out.println("Succesful !" + param[0] + " " + param[1]);
			// JOptionPane.showMessageDialog(this, "Succesful !");
		}
	}

	public void setFloorService(Object[] param) {
		boolean cmdSent = model.elevatorCommands(
				ElevatorCommands.SET_STOP_REQUEST, param);
		if (!cmdSent) {
			System.out.println("ERROR!");
			// JOptionPane.showMessageDialog(this,"Error Occured while processing your request !");
		} else {
			System.out.println("SERVICES !" + param[0] + " " + param[1] + " "
					+ param[2]);
			// JOptionPane.showMessageDialog(this, "Succesful !");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int n = Integer.valueOf(e.getActionCommand());
		setElevatorTarget(new int[] { 0, n });
		setDirectionStatus(new int[] { 0, n });
	}

}
