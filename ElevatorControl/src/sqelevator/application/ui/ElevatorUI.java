//package sqelevator.application.ui;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Observable;
//import java.util.Observer;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import javax.annotation.Resource;
//import javax.swing.JFrame;
//
//import sqelevator.application.model.ElevatorCommands;
//import sqelevator.application.model.FloorCommands;
//import sqelevator.application.model.IElevatorModel;
//import sqelevator.application.model.IFloorModel;
//
//import javax.swing.Icon;
//import javax.swing.JCheckBox;
//import javax.swing.JPanel;
//import javax.swing.BoxLayout;
//import javax.swing.ButtonGroup;
//import javax.swing.JComboBox;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//
//import java.awt.GridLayout;
//
//import javax.swing.JTextField;
//import javax.swing.SwingUtilities;
//import javax.swing.UIManager;
//import javax.swing.border.TitledBorder;
//
//import java.awt.FlowLayout;
//
//import javax.swing.border.EtchedBorder;
//import javax.swing.SwingConstants;
//
//import java.awt.Font;
//
//import javax.swing.JRadioButton;
//
//import java.awt.Component;
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//
//import java.awt.Button;
//
//import javax.swing.event.ChangeListener;
//import javax.swing.event.ChangeEvent;
//import javax.swing.JToggleButton;
//
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//
//import javax.swing.JScrollPane;
//
///**
// * Elevator UI class containing the view
// * @author Philipp
// *
// */
//public class ElevatorUI extends JFrame implements Observer {
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	private IElevatorModel model = null;
//	private JTextField txtSpeed;
//	private JTextField txtWeight;
//	private JTextField txtCapacity;
//	private JLabel label11;
//	private JLabel lblTargetFloor;
//	private JComboBox<Integer> comboElevatorNumber;
//	private JPanel panel_7;
//	
//	private long clock;
// 
//	
//	private JLabel[] lblFloorPositions;
//	private JLabel[] lblStatusFloors;
//	private JLabel[] lblHeightFloors;
//	
//	
//	private ImageIcon downIcon;
//	private ImageIcon upIcon; 
//	private ImageIcon rectIcon;
//	private ImageIcon downRedIcon;
//	private ImageIcon upRedIcon; 
//	private ImageIcon doubleArrowRedIcon;
//	
//	private JLabel lblDoorStatus;
//	private JScrollPane scrollPane1;
//	private JScrollPane scrollPane2;
//	
//	int i = 0;
//	private JPanel panel_8;
//	 
//	
//	private JPanel panel_1;
//	private JCheckBox cbManual;
//	private JPanel panelElevatorTarget;
//	private JLabel lblSelectFloor;
//	private JComboBox<Integer> comboFloor;
//	private JButton btnSetTarget;
//	
//	private ButtonGroup btnGroupOperationMode;
//	
//	private int currentTargetFloor;
//	private int currentFloor;
//	public int target;
//	private boolean uiLoaded = false;
//	private boolean mRunWithSimulator = false;
//	private boolean mSimulorRunning = false;
//	private boolean switchBackToAutoMode = false;
//	
//  	
//	public ElevatorUI(boolean runWithSimulator){
//		
//		mRunWithSimulator = runWithSimulator;
//		
//		setTitle("AEC - Awesome Elevator Controller");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
//		
//		JPanel status_panel = new JPanel();
//		status_panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		getContentPane().add(status_panel);
//		status_panel.setLayout(new BoxLayout(status_panel, BoxLayout.Y_AXIS));
//		
//		JPanel panel_4 = new JPanel();//TODO
//		panel_4.setAlignmentX(Component.RIGHT_ALIGNMENT);
//		panel_4.setAlignmentY(Component.TOP_ALIGNMENT);
//		panel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Select Elevator", TitledBorder.CENTER, TitledBorder.TOP, null, null));
//		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
//		status_panel.add(panel_4);
//		
//		JLabel lblFloorNumber = new JLabel("Elevator Number");
//		panel_4.add(lblFloorNumber);
//		
////		comboElevatorNumber = new JComboBox<Integer>();
////		panel_4.add(comboElevatorNumber);
//		
//		JPanel panel_2 = new JPanel();
//		panel_2.setAlignmentX(Component.RIGHT_ALIGNMENT);
//		status_panel.add(panel_2);
//		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		panel_2.setLayout(new GridLayout(5, 2, 5, 5));
//		
//		JLabel lblSpeed = new JLabel("speed");
//		lblSpeed.setHorizontalAlignment(SwingConstants.RIGHT);
//		panel_2.add(lblSpeed);
//		
//		txtSpeed = new JTextField();
//		txtSpeed.setName("txtSpeed");
//		txtSpeed.setEditable(false);
//		panel_2.add(txtSpeed);
//		txtSpeed.setColumns(10);
//		
//		JLabel lblNewLabel_1 = new JLabel("Weight");
//		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
//		panel_2.add(lblNewLabel_1);
//		
//		txtWeight = new JTextField();
//		txtWeight.setEditable(false);
//		txtWeight.setName("txtWeight");
//		panel_2.add(txtWeight);
//		txtWeight.setColumns(10);
//		
//		JLabel lblNewLabel = new JLabel("Capacity");
//		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
//		panel_2.add(lblNewLabel);
//		
//		txtCapacity = new JTextField();
//		txtCapacity.setEditable(false);
//		txtCapacity.setName("txtCapacity");
//		panel_2.add(txtCapacity);
//		txtCapacity.setColumns(10);
//		
//		JLabel lblAcceleration = new JLabel("Acceleration");
//		lblAcceleration.setHorizontalAlignment(SwingConstants.RIGHT);
//		panel_2.add(lblAcceleration);
//		
//		JLabel lblNewLabel_2 = new JLabel("Clock");
//		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
//		panel_2.add(lblNewLabel_2);
//		
//		JPanel panel_6 = new JPanel();
//		panel_6.setAlignmentX(Component.RIGHT_ALIGNMENT);
//		status_panel.add(panel_6);
//		panel_6.setBorder(new TitledBorder(null, "Requests", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		panel_6.setLayout(new GridLayout(0, 2, 5, 0));
//		
//		JLabel label10 = new JLabel("Next Target Floor");
//		label10.setHorizontalAlignment(SwingConstants.RIGHT);
//		panel_6.add(label10);
//		
//		lblTargetFloor = new JLabel("xx");
//		panel_6.add(lblTargetFloor);
//		
//		label11 = new JLabel("Door Status");
//		label11.setHorizontalAlignment(SwingConstants.RIGHT);
//		panel_6.add(label11);
//		
//		lblDoorStatus = new JLabel("xx");
//		panel_6.add(lblDoorStatus);
//		
//		panel_1 = new JPanel();
//		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Operation Mode", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		panel_1.setAlignmentX(1.0f);
//		status_panel.add(panel_1);
//		panel_1.setLayout(new GridLayout(0, 3, 5, 0));
//		
//		cbManual = new JCheckBox("Manual Control");
//		
//		cbManual.addChangeListener(new ChangeListener() {
//			
//			@Override
//			public void stateChanged(ChangeEvent e) {
//				if(cbManual.isSelected()){
//					panelElevatorTarget.setEnabled(true);
//					comboFloor.setEnabled(true);
//					btnSetTarget.setEnabled(true);
//				}
//			}
//		}); 
////		.addActionListener(new ActionListener() {
////			public void actionPerformed(ActionEvent e) {
////				if(cbManual.isSelected()){
////					panelElevatorTarget.setEnabled(true);
////					comboFloor.setEnabled(true);
////					btnSetTarget.setEnabled(true);
////				}
////			}
////		});
//		panel_1.add(cbManual);
//		
//		panelElevatorTarget = new JPanel();
//		panelElevatorTarget.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Elevator Target", TitledBorder.CENTER, TitledBorder.TOP, null, null));
//		panelElevatorTarget.setAlignmentY(0.0f);
//		panelElevatorTarget.setAlignmentX(1.0f);
//		status_panel.add(panelElevatorTarget);
//		
//		
//		
//		lblSelectFloor = new JLabel("Select Floor");
//		panelElevatorTarget.add(lblSelectFloor);
//		
//		comboFloor = new JComboBox<Integer>();
//		comboFloor.setName("FloorSelect");
//		panelElevatorTarget.add(comboFloor);
//		
//		btnSetTarget = new JButton("Set Target");
//		btnSetTarget.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				if((mRunWithSimulator && mSimulorRunning) || !mRunWithSimulator){
//					int elevatorNum=Integer.parseInt(comboElevatorNumber.getSelectedItem().toString());
//					int targetNum=Integer.parseInt(comboFloor.getSelectedItem().toString());
//					setElevatorTarget(new int[]{elevatorNum-1,targetNum});
//					setFloorService(new Object[]{elevatorNum-1,targetNum, true});
//					currentTargetFloor = targetNum;
//				}else{
//					JOptionPane.showMessageDialog(ElevatorUI.this, "Simulator not Running");				
//				}				
//			}
//		});
//		panelElevatorTarget.add(btnSetTarget);
//		
//		if(cbManual.isSelected()){
//			panelElevatorTarget.setEnabled(false);
//			comboFloor.setEnabled(false);
//			btnSetTarget.setEnabled(false);
//		}
//		JPanel panel_5 = new JPanel();
//		panel_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		getContentPane().add(panel_5);
//		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
//		
//		
//		panel_7 = new JPanel();
//		panel_7.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Position", TitledBorder.LEFT, TitledBorder.TOP, null, null));
//		//panel_5.add(panel_7);
//		panel_7.setLayout(new GridLayout(0, 1, 0, 20));
//		
//		scrollPane1 = new JScrollPane(panel_7);
//		panel_5.add(scrollPane1);
//		
//		panel_8 = new JPanel();
//		panel_8.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Floor Status", TitledBorder.LEFT, TitledBorder.TOP, null, null));
//		//panel_5.add(panel_8);
//		panel_8.setLayout(new GridLayout(0, 2, 5, 20));
//		
//		scrollPane2 = new JScrollPane(panel_8);
//		panel_5.add(scrollPane2);
//		
//		if(mRunWithSimulator){
//			checkSimulatorStatus();
//		}
//		
//		btnGroupOperationMode = new ButtonGroup();
//		btnGroupOperationMode.add(cbManual);
//
//		loadImageResource();
//	}
//
//	
//	
//	public void checkSimulatorStatus(){
//		
//		Timer simulotStat = new Timer();
//		simulotStat.scheduleAtFixedRate(new TimerTask(){
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				synchronized (this) {
//					long curValue = clock;
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					long nextValue = clock;
//					
//					if(curValue==nextValue){
//						mSimulorRunning = false;
//					}else{
//						mSimulorRunning = true;
//					}
//				}
//			}
//				
//		},new Date(), 2000);
//					 
//		
//	}
//	
//	
//	@Override
//	public void update(Observable o, Object arg) {
//	 // synchronized (this) {
//		
// 		  ArrayList<IFloorModel> floors;
//			if(arg != null  && (arg instanceof IElevatorModel)){
//				
//				if(!uiLoaded ){
//					model = (IElevatorModel)arg;
//					int elevNums = model.getElevatorNumber();
//					comboElevatorNumber.removeAllItems();
//					for(int j = 0; j<elevNums; j++){
//						comboElevatorNumber.addItem(j+1);
//					}
//					
//					
//					txtCapacity.setText(model.getCapacity() + " persons");		
//					
//					floors = model.getFloors();
//					
//					comboFloor.removeAllItems();
//					i = 0;
//					for(IFloorModel floorModel : floors ){
//						floorModel = floors.get(i);
//						comboFloor.addItem(floorModel.getFloorNumber());
//					
//						i++;
//					}
//					
//					
//					lblFloorPositions = new JLabel[floors.size()];
//						lblFloorPositions = new JLabel[floors.size()];
//						lblStatusFloors = new JLabel[floors.size()];
//						lblHeightFloors = new JLabel[floors.size()];
//						for(int i = floors.size()-1; i >= 0; i--){
//							lblFloorPositions[i] = new JLabel(String.valueOf(i));
//							lblFloorPositions[i].setVerticalAlignment(SwingConstants.BOTTOM);
//							lblFloorPositions[i].setHorizontalAlignment(SwingConstants.CENTER);
//							lblFloorPositions[i].setFont(new Font("Tahoma", Font.BOLD, 15));
//							panel_7.add(lblFloorPositions[i]);
//							
//							
//							lblStatusFloors[i] = new JLabel(String.valueOf(i));
//							lblStatusFloors[i].setVerticalAlignment(SwingConstants.BOTTOM);
//							lblStatusFloors[i].setHorizontalAlignment(SwingConstants.RIGHT);
//							lblStatusFloors[i].setFont(new Font("Tahoma", Font.BOLD, 15));
//							panel_8.add(lblStatusFloors[i]);
//							
//		//					lblHeightFloors[i] = new JLabel(floors.get(i).getFeetsPosition() +  " feet");
//							lblHeightFloors[i].setVerticalAlignment(SwingConstants.BOTTOM);
//							lblHeightFloors[i].setHorizontalAlignment(SwingConstants.RIGHT);
//							lblHeightFloors[i].setFont(new Font("Tahoma", Font.PLAIN, 13));
//							panel_8.add(lblHeightFloors[i]);
//							
//							
//							panel_7.revalidate();
//							panel_8.revalidate();
//						}
//						panel_7.revalidate();
//						panel_8.revalidate();
//						validate();
//						
//					
//					 
//					uiLoaded = true;
//				}else{
//					model = (IElevatorModel)arg;
//					floors = model.getFloors();
//					int i = 0;
//				
//					txtSpeed.setText( model.getSpeed() + " ft/sec");
//					txtAcceleration.setText(model.getAcceleration() + " ft/sec-2");
//					txtWeight.setText(model.getWeight() + " lbs");
//					
//		            // #### To be done convert ticks to time stan
//					
//					clock = model.getClockTick();
//					lblTargetFloor.setText( String.valueOf(model.getNextTargetFloor()));
//					
//					
//					int doorStatus = model.getDoorStatus();
//					String dStatus = null;
//					if(doorStatus == 1){
//						dStatus = "Open";
//						
//					}else if(doorStatus == 2){
//						dStatus = "Close";
//					}else if(doorStatus == 3){
//						dStatus = "Opening";
//					}else if(doorStatus == 4){
//						dStatus = "Closing";
//					}
//					lblDoorStatus.setText(dStatus);
//					
//					
//					
//					for(IFloorModel floorModel : floors ){
//						
//						int floorNum = floorModel.getFloorNumber();
//						boolean resultDown = floorModel.floorCommands(FloorCommands.GET_FLOOR_BUTTON_DOWN, floorNum);
//						boolean resultUp = floorModel.floorCommands(FloorCommands.GET_FLOOR_BUTTON_UP, floorNum);
//						
//						boolean resultTargetDown = floorModel.floorCommands(FloorCommands.GET_FLOOR_BUTTON_DOWN, currentTargetFloor);
//						boolean resultTeargetUp = floorModel.floorCommands(FloorCommands.GET_FLOOR_BUTTON_UP, currentTargetFloor);
//						
//						if(resultDown == true && resultUp == true){
//							setFloorrButtonStatus(floorNum, 0);
//							/*if(rdbtnAuto.isSelected()){
//								int elevatorNum=Integer.parseInt(comboElevatorNumber.getSelectedItem().toString());
//								int targetNum=floorNum;
//								if(resultTargetDown==false && resultTeargetUp==false){
//									if(floorNum != model.getElevatorFloorNumber()){
//										setElevatorTarget(new int[]{elevatorNum-1,targetNum});
//										setFloorService(new Object[]{elevatorNum-1,targetNum, true});
//										currentTargetFloor = targetNum;
//									}
//								}
//							}*/
//						}else if(resultDown){
//							setFloorrButtonStatus(floorNum, -1);
//							/*if(rdbtnAuto.isSelected()){
//								int elevatorNum=Integer.parseInt(comboElevatorNumber.getSelectedItem().toString());
//								int targetNum=floorNum;
//								if(resultTargetDown==false && resultTeargetUp==false){
//									if(floorNum != model.getElevatorFloorNumber()){
//										setElevatorTarget(new int[]{elevatorNum-1,targetNum});
//										setFloorService(new Object[]{elevatorNum-1,targetNum, true});
//										currentTargetFloor = targetNum;
//									}
//								}
//							}*/
//						}else if(resultUp){
//							setFloorrButtonStatus(floorNum, 0);
//							/*if(rdbtnAuto.isSelected()){
//								int elevatorNum=Integer.parseInt(comboElevatorNumber.getSelectedItem().toString());
//								int targetNum=floorNum;
//								if(resultTargetDown==false && resultTeargetUp==false){
//									if(floorNum != model.getElevatorFloorNumber()){
//										setElevatorTarget(new int[]{elevatorNum-1,targetNum});
//										setFloorService(new Object[]{elevatorNum-1,targetNum, true});
//										currentTargetFloor = targetNum;
//									}
//								}
//							}*/
//						}else{
//							clearFloorButtonStatus(floorNum);
//						}
//						
//						
//						 
//						i++;
//						 
//					}
//				}
//				
//				if(cbManual.isSelected()){
//					currentFloor = model.getElevatorFloorNumber();
//					if(currentFloor == target || switchBackToAutoMode){
//						int elevatorNum=Integer.parseInt(comboElevatorNumber.getSelectedItem().toString());
//		//				target = AutomateMode.FindNextTarget(model.getTotalFloorsNumber(), model.getElevatorFloorNumber(), floors);
//						setElevatorTarget(new int[]{elevatorNum-1,target});
//						setFloorService(new Object[]{elevatorNum-1,target, true});
//						switchBackToAutoMode = false;
//					}
//				
//				}
//				
//					
//				setElevatorPosition(model.getElevatorFloorNumber(), model.getCommittedDirection());
//				//setFloorrButtonStatus(floorNumber, requestedDirection);
//				System.out.println("ElevatorUI: "+ model.getElevatorNumber()+
//						" direction:"+ model.getCommittedDirection()+
//						" floor:"+model.getElevatorFloorNumber());
//				
//				
//			}
//	//}
//		
//	}
//	
//	public Icon getLabelIcon(int floorNum){
//		return lblFloorPositions[floorNum].getIcon();
//	}
//	
//
//	
//	 
//	Timer t = new Timer();
//	boolean up = true;
////	public void demo(){
////		t.scheduleAtFixedRate(new TimerTask(){
////
////			@Override
////			public void run() {
////				// TODO Auto-generated method stub
////				if(up){
////					 setElevatorPosition(++i , 0);
////					 if(i == 5 ){
////						 up = false;
////					 }
////				}else{
////					setElevatorPosition(--i , 1);
////					 if(i == 0 ){
////						 up = true;
////					 }
////				}
////				
////				 
////			}
////			
////		},new Date(), 1000);
////		 
////	}
////	Timer t2 = new Timer();
////
////	public void demo2(){
////		t2.scheduleAtFixedRate(new TimerTask(){
////
////			@Override
////			public void run() {
////				// TODO Auto-generated method stub
////				if(up){
////					 setElevatorPosition(++i , 1);
////					 if(i == 5 ){
////						 up = false;
////					 }
////				}else{
////					setElevatorPosition(--i , 1);
////					 if(i == 0 ){
////						 up = true;
////					 }
////				}
////				
////				 
////			}
////			
////		},new Date(), 1000);
////	}
////	
//	public void loadImageResource(){
//		String path = "res/elevator.png";
//	    downIcon = new ImageIcon(getClass().getClassLoader().getResource(path));
//		path = "res/elevator.png";
//		upIcon = new ImageIcon(getClass().getClassLoader().getResource(path));
//		path = "res/elevator.png";
//		rectIcon = new ImageIcon(getClass().getClassLoader().getResource(path));
//		
//		path = "res/down-red-32.png";
//		downRedIcon = new ImageIcon(getClass().getClassLoader().getResource(path));
//		path = "res/up-red-32.png";
//		upRedIcon = new ImageIcon(getClass().getClassLoader().getResource(path));
//		path = "res/double-side-red-32.png";
//		doubleArrowRedIcon = new ImageIcon(getClass().getClassLoader().getResource(path));
//
//
//	}
//	
//	public  void setElevatorPosition(int floorNumber, int comittedDirection){
//				
//		for(int i= 0; i< lblFloorPositions.length; i++){
//			if(i != floorNumber){
// 				lblFloorPositions[i].setIcon(null); 
//			}
//		}
//		 
//		
//		ImageIcon icon ;
//		if(comittedDirection == 1){
//			icon = downIcon;
//		}else if(comittedDirection == 2){
//			icon = rectIcon;
//		}else if(comittedDirection == 0){
//			icon = upIcon;
//		}else{
//			return;
//		}
//		 
//		lblFloorPositions[floorNumber].setIcon(icon);;
//		lblFloorPositions[floorNumber].revalidate();
// 		validate();
//	 
//		
//	}
//	
//	public void clearFloorButtonStatus(int floorNumber){
//		lblStatusFloors[floorNumber].setIcon(null);	 
//	}
//	public void setFloorrButtonStatus(int floorNumber, int requestedDirection){
//			
//		ImageIcon icon ;
//		if(requestedDirection == -1){
//			icon = downRedIcon;
//		}else if(requestedDirection == 0){
//			icon = doubleArrowRedIcon;
//		}else if(requestedDirection == 1){
//			icon = upRedIcon;
//		}else{
//			return;
//		}
//		
//		lblStatusFloors[floorNumber].setIcon(icon);	 
//		
//	}
//	
//	public IElevatorModel getElevatorModel(){
//		return model;
//	}
//	
//	public void setElevatorMode(String mode){
//		if(mode.equalsIgnoreCase("Manual")){
//			cbManual.setSelected(true);
//		}
//		else{
//			cbManual.setSelected(false);
//		}
//	}
//	
//	public boolean isSetTargetButtonEnabled(){
//		return btnSetTarget.isEnabled();
//	}
//	public boolean isSetTargetComboEnabled(){
//		return comboFloor.isEnabled();
//	}
//	
//	public String getElevatorMode(){
//		if(cbManual.isSelected()){
//			return "Manual";
//		}
//		return "Automatic";
//	}
//	
//	public void setElevatorTarget(int[] param){
//	  boolean cmdSent=model.elevatorCommands(ElevatorCommands.SET_TARGET,param);
//	  if(!cmdSent){
//		  System.out.println("Error Occured while processing your request !");
//		  //JOptionPane.showMessageDialog(this,"Error Occured while processing your request !");
//	  }
//	  else
//	  {
//		  System.out.println("Succesful !" + param[0] + " " + param[1]);
//		  //JOptionPane.showMessageDialog(this, "Succesful !");
//	  }
//	}
//	
//	public void setFloorService(Object[] param){
//		boolean cmdSent=model.elevatorCommands(ElevatorCommands.SET_STOP_REQUEST,param);
//		if(!cmdSent){
//			System.out.println("ERROR!");
//			//JOptionPane.showMessageDialog(this,"Error Occured while processing your request !");
//		}
//		else
//		{
//			System.out.println("SERVICES !" + param[0] + " " + param[1] +" "+param[2]);
//			//JOptionPane.showMessageDialog(this, "Succesful !");
//		}
//	}
//}
